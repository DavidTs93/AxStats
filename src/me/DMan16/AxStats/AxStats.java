package me.DMan16.AxStats;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.plugin.java.JavaPlugin;

import me.Aldreda.AxUtils.Utils.Utils;
import net.kyori.adventure.text.format.NamedTextColor;

public class AxStats extends JavaPlugin {
	private static AxStats instance = null;
	
	public void onEnable() {
		instance = this;
		registerMainStats();
		Utils.chatColorsLogPlugin("&fAxStats &aloaded!");
	}
	
	private void registerMainStats() {
		new AxStatType("defense",true,AxStatType.defaultAxStatFunctionSingle(NamedTextColor.AQUA,NamedTextColor.LIGHT_PURPLE,NamedTextColor.DARK_RED,null,null,null),
				false).register();
		new AxStatType("toughness",true,AxStatType.defaultAxStatFunctionSingle(NamedTextColor.DARK_GRAY,NamedTextColor.LIGHT_PURPLE,NamedTextColor.DARK_RED,null,null,null),
				false).register();
		new AxStatType("stamina",true,AxStatType.defaultAxStatFunctionSingle(NamedTextColor.YELLOW,NamedTextColor.LIGHT_PURPLE,NamedTextColor.DARK_RED,null,null,null),
				false,Attribute.GENERIC_MAX_HEALTH,1).register();
		new AxStatType("strength",true,AxStatType.defaultAxStatFunctionSingle(NamedTextColor.RED,NamedTextColor.LIGHT_PURPLE,NamedTextColor.DARK_RED,null,null,null),
				false,Attribute.GENERIC_ATTACK_DAMAGE,0.5f).register();
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
	
	public void onDisable() {
		Bukkit.getScheduler().cancelTasks(this);
		Utils.chatColorsLogPlugin("&fAxStats &adisabed");
	}
	
	public static AxStats getInstance() {
		return instance;
	}
}