package me.DMan16.AxStats;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

import javax.annotation.Nullable;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.inventory.EquipmentSlot;

import me.Aldreda.AxUtils.Classes.Pair;
import me.Aldreda.AxUtils.Classes.Trio;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class AxStatType {
	private static HashMap<String,AxStatType> stats = new HashMap<String,AxStatType>();
	public static final String translateStatBase = "attribute.name.aldreda.";
	
	private final String key;
	private final boolean beneficial;
	private final Function<Pair<AxStatType,Trio<Float,Float,Boolean>>,Component> LineMethod;
	private final boolean require2;
	private final Attribute attribute;
	private final float mult;
	
	public AxStatType(@Nullable String key, boolean beneficial, Function<Pair<AxStatType,Trio<Float,Float,Boolean>>,Component> LineMethod, boolean require2) {
		this(key,beneficial,LineMethod,require2,null,1);
	}
	
	public AxStatType(@Nullable String key, boolean beneficial, Function<Pair<AxStatType,Trio<Float,Float,Boolean>>,Component> LineMethod, boolean require2,
			@Nullable Attribute attribute, float mult) {
		if (require2 && attribute != null) throw new IllegalArgumentException("Attribute allowed only to single valued stats!");
		this.key = key == null ? null : key.toLowerCase();
		this.beneficial = beneficial;
		this.LineMethod = LineMethod;
		this.require2 = require2;
		this.attribute = attribute;
		this.mult = mult;
	}
	
	public AxStatType register() {
		stats.put(Objects.requireNonNull(stats.containsKey(Objects.requireNonNull(key(),"Stat type key cannot be null!")) ? null :
			Objects.requireNonNull(LineMethod == null ? null : key(),"LineMethod cannot be null!"),"The key: \"" + key() + "\" is already being used!"),this);
		return this;
	}
	
	public boolean isRegistered() {
		return key() != null && stats.containsKey(key()) && stats.get(key()).equals(this);
	}
	
	public String key() {
		return key;
	}
	
	public boolean beneficial() {
		return beneficial;
	}
	
	public Component line(float val1, Float val2, boolean percent) {
		if (requires2Values() && val2 == null) throw new IllegalArgumentException("This stat requires 2 values!");
		return LineMethod.apply(Pair.of(this,Trio.of(val1,val2,percent)));
	}
	
	public Attribute attribute() {
		return attribute;
	}
	
	public Pair<Attribute,AttributeModifier> attribute(float val, boolean percent, EquipmentSlot slot) {
		if (attribute() == null || slot == null) return null;
		float amount = val * mult;
		if (percent) amount /= 100f; 
		return Pair.of(attribute(), new AttributeModifier(UUID.randomUUID(),"axstat_" + key + "_" + slot.name().toLowerCase(),amount,
				percent ? Operation.MULTIPLY_SCALAR_1 : Operation.ADD_NUMBER,slot));
	}
	
	public float mult() {
		return mult;
	}
	
	public boolean requires2Values() {
		return require2;
	}
	
	public static AxStatType getAxStatType(String key) {
		if (key == null) return null;
		return stats.get(key.toLowerCase());
	}
	
	public static Function<Pair<AxStatType,Trio<Float,Float,Boolean>>,Component> defaultAxStatFunctionSingle(TextColor color, @Nullable TextColor colorPlus,
			@Nullable TextColor colorMinus, @Nullable TextColor colorValPlus, @Nullable TextColor colorValMinus, @Nullable TextColor colorPercent) {
		return (info) -> {
			float val = info.second().first;
			boolean percent = info.second().third;
			TextColor plusColor = colorPlus == null ? color : colorPlus;
			TextColor minusColor = colorMinus == null ? (colorPlus == null ? color : colorPlus) : colorMinus;
			TextColor valColor = val >= 0 ? (colorValPlus == null ? colorPlus : colorValPlus) : (colorValMinus == null ? colorMinus : colorValMinus);
			TextColor percentColor = colorPercent == null ? valColor : colorPercent;
			Component sign = Component.text(val >= 0 ? "+" : "-").color(val >= 0 ? plusColor : minusColor);
			Component arg = sign.append(Component.text(val).color(valColor));
			if (percent) arg = arg.append(Component.text("%").color(percentColor));
			return Component.translatable(AxStatType.translateStatBase + info.first().key(),color).args(arg).decoration(TextDecoration.ITALIC,false);
		};
	}
	
	public static Function<Pair<AxStatType,Trio<Float,Float,Boolean>>,Component> defaultAxStatFunctionDuo(TextColor color, @Nullable TextColor colorPlus,
			@Nullable TextColor colorMinus, @Nullable TextColor colorVal1Plus, @Nullable TextColor colorVal1Minus, @Nullable TextColor colorVal2Plus,
			@Nullable TextColor colorVal2Minus, @Nullable TextColor colorPercent, Component seperator, Component suffix, @Nullable TextColor colorSeperator,
			@Nullable TextColor colorSuffix) {
		return (info) -> {
			float val1 = info.second().first;
			float val2 = info.second().second;
			boolean percent = info.second().third;
			TextColor plusColor = colorPlus == null ? color : colorPlus;
			TextColor minusColor = colorMinus == null ? (colorPlus == null ? color : colorPlus) : colorMinus;
			TextColor val1Color = val1 >= 0 ? (colorVal1Plus == null ? colorPlus : colorVal1Plus) : (colorVal1Minus == null ? colorMinus : colorVal1Minus);;
			TextColor val2Color = val2 >= 0 ? (colorVal2Plus == null ? colorPlus : colorVal2Plus) : (colorVal2Minus == null ? colorMinus : colorVal2Minus);;
			TextColor percentColor = colorPercent == null ? val1Color : colorPercent;
			TextColor seperatorColor = colorSeperator == null ? val1Color : colorSeperator;
			TextColor suffixColor = colorSuffix == null ? val2Color : colorSuffix;
			Component sign = Component.text(val1 >= 0 ? "+" : "-").color(val1 >= 0 ? plusColor : minusColor);
			Component arg = sign.append(Component.text(val1).color(val1Color));
			if (percent) arg = arg.append(Component.text("%").color(percentColor));
			arg = arg.append(seperator.color(seperatorColor)).append(Component.text(val2).color(val2Color)).append(suffix.color(suffixColor));
			return Component.translatable(AxStatType.translateStatBase + info.first().key(),color).args(arg).decoration(TextDecoration.ITALIC,false);
		};
	}
}