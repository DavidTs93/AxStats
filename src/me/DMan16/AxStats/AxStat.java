package me.DMan16.AxStats;

import me.Aldreda.AxUtils.Classes.Pair;
import net.kyori.adventure.text.Component;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AxStat {
	private final AxStatType type;
	private final float val1;
	private final Float val2;
	private final boolean percent;
	private final EquipSlot slot;
	private final Component line;

	public AxStat(@NotNull AxStatType type, float val1, Float val2, boolean percent, EquipmentSlot slot) {
		this(type,val1,val2,percent,EquipSlot.getByEquipmentSlot(slot));
	}
	
	public AxStat(@NotNull AxStatType type, float val1, Float val2, boolean percent, EquipSlot slot) {
		this.type = Objects.requireNonNull(Objects.requireNonNull(type,"Stat type cannot be null!").isRegistered() ? type : null,"Stat type must be registered!");
		this.val1 = val1;
		this.val2 = this.type.requires2Values() ? val2 : null;
		this.percent = percent;
		this.slot = slot;
		this.line = this.type.line(this.val1,this.val2,this.percent);
	}
	
	public AxStatType type() {
		return type;
	}
	
	public float val1() {
		return val1;
	}
	
	public Float val2() {
		return val2;
	}
	
	public boolean percent() {
		return percent;
	}
	
	public EquipSlot slot() {
		return slot;
	}
	
	public Component line() {
		return line;
	}
	
	public Pair<Attribute,AttributeModifier> attribute() {
		return type.attribute(val1,percent,slot);
	}
	
	public AxStat join(AxStat stat) {
		if (stat.type.equals(type) && stat.percent == percent && stat.slot.equals(slot))
			return new AxStat(type,val1 + stat.val1,val2 == null ? null : val2 + stat.val2,percent,slot);
		return null;
	}
}