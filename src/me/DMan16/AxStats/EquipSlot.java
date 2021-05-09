package me.DMan16.AxStats;

import org.bukkit.inventory.EquipmentSlot;

public enum EquipSlot {
    HAND(EquipmentSlot.HAND),
    OFF_HAND(EquipmentSlot.OFF_HAND),
    FEET(EquipmentSlot.FEET),
    LEGS(EquipmentSlot.LEGS),
    CHEST(EquipmentSlot.CHEST),
    HEAD(EquipmentSlot.HEAD),
    RING,
    NECKLACE,
    ;
	
	private EquipmentSlot slot;

	EquipSlot() {
		this(null);
	}
	
	EquipSlot(EquipmentSlot slot) {
		this.slot = slot;
	}
	
	public EquipmentSlot slot() {
		return slot;
	}
	
	public static EquipSlot getByEquipmentSlot(EquipmentSlot slot) {
		if (slot == null) return null;
		for (EquipSlot val : values()) if (val.slot == slot) return val;
		return null;
	}
}