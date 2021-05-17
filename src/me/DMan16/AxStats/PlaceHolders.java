package me.DMan16.AxStats;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceHolders extends PlaceholderExpansion {
	
	public boolean persist() {
		return true;
	}
	
	public boolean canRegister() {
		return true;
	}
	
	public String getAuthor() {
		return AxStats.getInstance().getDescription().getAuthors().toString();
	}
	
	public String getIdentifier() {
		return "AxStats";
	}
	
	public String getVersion() {
		return AxStats.getInstance().getDescription().getVersion();
	}
	
	public String onPlaceholderRequest(Player player, String identifier) {
		return onRequest(player,identifier);
	}
	
	public String onRequest(@NotNull OfflinePlayer player, String identifier) {
		if (player == null) return null;
		AxStatType type = AxStatType.getAxStatType(identifier);
		if (type != null) return "";
		return null;
	}
}