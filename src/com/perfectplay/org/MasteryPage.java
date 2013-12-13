package com.perfectplay.org;

import org.joda.time.DateTime;


public class MasteryPage extends CachedObject{
	private long summoner;
	private Talent[] talents;
	private boolean active;
	private String name;
	
	MasteryPage(DateTime time_cached, long summoner, Talent[] talents, boolean active, String name){
		this.time_cached = time_cached;
		this.summoner = summoner;
		this.talents = talents;
		this.active = active;
		this.name = name;
	}
	
	public long getSummonerId(){
		return summoner;
	}
	
	public Talent[] getTalents(){
		return talents;
	}
	
	public boolean isActive(){
		return active;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		String temp = "";
		for(int i = 0; i < talents.length; i++){
			temp += talents[i].toString();
		}
		return "[Name:" + name + ", Active:" + active + ", Summoner ID:" + summoner + ", Date Cached:" + time_cached + ", Talents:" + temp + "]";
	}
}
