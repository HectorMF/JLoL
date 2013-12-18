package com.perfectplay.org;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class RankedStatistic extends CachedObject{
	private long modifyDate;
	private String modifyDateStr;
	private long summonerId;
	private HashMap<String, ChampionStatistic> stats;
	
	RankedStatistic(Date time_cached, long modifyDate, String modifyDateStr, long summonerId, ChampionStatistic[] championStats){
		this.time_cached = time_cached;
		this.modifyDate = modifyDate;
		this.modifyDateStr = modifyDateStr;
		this.summonerId = summonerId;
		stats = new HashMap<String, ChampionStatistic>();
		for(int i = 0; i < championStats.length; i++){
			stats.put(championStats[i].getChampionName(), championStats[i]);
		}
	}
	
	public long getModifyDate(){
		return modifyDate;
	}
	
	public Set<String> keySet(){
		return stats.keySet();
	}
	
	public Collection<ChampionStatistic> values(){
		return stats.values();
	}
	
	public String getModifyDateString(){
		return modifyDateStr;
	}
	
	public long getSummonerId(){
		return summonerId;
	}
	
	public Summoner getSummoner(){
		return SummonerQuery.getSummoner(summonerId);
	}
	
	public ChampionStatistic getChampionStatistic(String championName){
		return stats.get(championName);
	}
}
