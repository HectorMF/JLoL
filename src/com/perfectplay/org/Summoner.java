package com.perfectplay.org;

import org.joda.time.DateTime;


public class Summoner extends CachedObject{
	private long id;
	
	private String name;
	private long level;
	
	private int profileIconId;
	
	private long revisionDate;
	private String revisionDateStr;

	Summoner(DateTime time_cached, long id, String name, long level, int profileIconId, long revisionDate, String revisionDateStr){
		this.time_cached = time_cached;
		this.id = id;
		this.name = name;
		this.level = level;
		this.profileIconId = profileIconId;
		this.revisionDate = revisionDate;
		this.revisionDateStr = revisionDateStr;
	}
	
	public MasteryPage[] getMasteries(){
		return SummonerQuery.getMasteries(id);
	}
	
	public RunePage[] getRunes(){
		return SummonerQuery.getRunes(id);
	}
	
	public League[] getLeagues(){
		return LeagueQuery.getLeague(id);
	}
	
	public long getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public long getLevel(){
		return level;
	}
	
	public int getIconId(){
		return profileIconId;
	}
	
	public long getRevisionDate(){
		return revisionDate;
	}
	
	public String getRevisionDateString(){
		return revisionDateStr;
	}
	
	public String toString(){
		return "[ID:" + id + ", Name:" + name + ", Level:" + level + ", Profile Icon:" 
				+ profileIconId + ", Revision Date:" + revisionDate + ", Revision Date(string):" + revisionDateStr
				+ ", Date Cached:" + time_cached + "]";
	}
}
