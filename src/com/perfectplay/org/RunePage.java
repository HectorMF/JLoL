package com.perfectplay.org;

import java.util.Date;
import java.util.HashMap;

public class RunePage extends CachedObject {
	private long summoner;
	private HashMap<Integer,Rune> runesBySlot = new HashMap<Integer,Rune>();
	private boolean active;
	private String name;
	
	RunePage(Date time_cached, long summoner, Rune[] runes, boolean active, String name){
		this.time_cached = time_cached;
		this.summoner = summoner;
		for(int i = 0; i < runes.length; i ++){
			 runesBySlot.put(runes[i].getSlot(), runes[i]);
		}
		this.active = active;
		this.name = name;
	}
	
	public long getSummonerId(){
		return summoner;
	}
	
	public Rune[] getRunes(){
		return runesBySlot.values().toArray(new Rune[runesBySlot.values().size()]);
	}
	
	public Rune getRune(int slot){
		return runesBySlot.get(slot);
	}
	
	public boolean isActive(){
		return active;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		String temp = "";
		for(Rune rune : runesBySlot.values()){
			temp += rune.toString();
		}
		return "[Name:" + name + ", Active:" + active + ", Summoner ID:" + summoner + ", Date Cached:" + time_cached + ", Runes:" + temp + "]";
	}
}
