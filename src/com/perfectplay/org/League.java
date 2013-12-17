package com.perfectplay.org;

import java.util.Date;

public class League extends CachedObject {
	private String name;
	private String queue;
	private String tier;
	private long timeStamp;
	private LeagueItem[] entries;
	
	League(Date time_cached, String name, String queue, String tier, long timeStamp, LeagueItem[] entries){
		this.time_cached = time_cached;
		this.name = name;
		this.queue = queue;
		this.tier = tier;
		this.timeStamp = timeStamp;
		this.entries = entries;
	}
	
	public String getName(){
		return name;
	}
	
	public String getQueue(){
		return queue;
	}
	
	public String getTier(){
		return tier;
	}
	
	public long getTimeStamp(){
		return timeStamp;
	}
	
	public LeagueItem[] getEntries(){
		return entries;
	}
	
	public String toString(){
		String temp = "";
		for(int i = 0; i < entries.length - 1; i++){
			temp += entries[i].toString() + ",";
		}
		if(entries.length > 0)
			temp += entries[entries.length - 1].toString();
		return "[Queue:" + queue + ", Tier:" + tier + ", Name:" + name + ", Time Stamp:" + timeStamp + ", Entries:" + temp + "]";
	}
}
