package com.perfectplay.org;

public class Rune {
	private int runeSlot;
	private String description;
	private int id;
	private String name;
	private int tier;
	
	Rune(int slot, String description, int id, String name, int tier){
		this.runeSlot = slot;
		this.description = description;
		this.id = id;
		this.name = name;
		this.tier = tier;
	}
	
	public int getId(){
		return id;
	}
	
	public int getTier(){
		return tier;		
	}
	
	public int getSlot(){
		return runeSlot;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		return "[Rune Slot:" + runeSlot + ", ID:" + id + ", Name:" + name + ", Description:" + description + ", Tier:" + tier + "]";
	}
	
}
