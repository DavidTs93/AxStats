package me.DMan16.AxStats;

import me.Aldreda.AxUtils.Utils.Utils;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.plugin.java.JavaPlugin;

public class AxStats extends JavaPlugin {
	private static AxStats instance = null;
	
	public void onEnable() {
		instance = this;
		registerMainStats();
		Utils.chatColorsLogPlugin("&fAxStats &aloaded!");
	}
	
	private void registerMainStats() {
		new AxStatType("defense",true,AxStatType.defaultAxStatFunctionSingle(NamedTextColor.DARK_GREEN,NamedTextColor.LIGHT_PURPLE,NamedTextColor.DARK_RED,
				null,null,null),false).register();
		new AxStatType("toughness",true,AxStatType.defaultAxStatFunctionSingle(NamedTextColor.DARK_GRAY,NamedTextColor.LIGHT_PURPLE,NamedTextColor.DARK_RED,
				null,null,null),false).register();
		new AxStatType("stamina",true,AxStatType.defaultAxStatFunctionSingle(NamedTextColor.RED,NamedTextColor.LIGHT_PURPLE,NamedTextColor.DARK_RED,
				null,null,null),false,Attribute.GENERIC_MAX_HEALTH,1).register();
		new AxStatType("strength",true,AxStatType.defaultAxStatFunctionSingle(NamedTextColor.GOLD,NamedTextColor.LIGHT_PURPLE,NamedTextColor.DARK_RED,
				null,null,null),false,Attribute.GENERIC_ATTACK_DAMAGE,0.5f).register();
		new AxStatType("speed",true,AxStatType.defaultAxStatFunctionSingle(NamedTextColor.YELLOW,NamedTextColor.LIGHT_PURPLE,NamedTextColor.DARK_RED,
				null,null,null),false,Attribute.GENERIC_MOVEMENT_SPEED,1).register();
		new AxStatType("agility",true,AxStatType.defaultAxStatFunctionSingle(NamedTextColor.AQUA,NamedTextColor.LIGHT_PURPLE,NamedTextColor.DARK_RED,
				null,null,null),false,Attribute.GENERIC_ATTACK_SPEED,0.1f).register();
		new AxStatType("resistance",true,AxStatType.defaultAxStatFunctionSingle(TextColor.color(0x1e140a),NamedTextColor.LIGHT_PURPLE,NamedTextColor.DARK_RED,
				null,null,null),false,Attribute.GENERIC_KNOCKBACK_RESISTANCE,0.01f).register();
		new AxStatType("intelligence",true,AxStatType.defaultAxStatFunctionSingle(NamedTextColor.BLUE,NamedTextColor.LIGHT_PURPLE,NamedTextColor.DARK_RED,
				null,null,null),false).register();
	}
	
	public static AxStatType defense() {
		return AxStatType.getAxStatType("defense");
	}
	
	public static AxStatType toughness() {
		return AxStatType.getAxStatType("toughness");
	}
	
	public static AxStatType stamina() {
		return AxStatType.getAxStatType("stamina");
	}
	
	public static AxStatType strength() {
		return AxStatType.getAxStatType("strength");
	}
	
	public static AxStatType speed() {
		return AxStatType.getAxStatType("speed");
	}
	
	public static AxStatType agility() {
		return AxStatType.getAxStatType("agility");
	}
	
	public static AxStatType resistance() {
		return AxStatType.getAxStatType("resistance");
	}
	
	public static AxStatType intelligence() {
		return AxStatType.getAxStatType("intelligence");
	}
	
	public void onDisable() {
		Bukkit.getScheduler().cancelTasks(this);
		Utils.chatColorsLogPlugin("&fAxStats &adisabed");
	}
	
	public static AxStats getInstance() {
		return instance;
	}
}