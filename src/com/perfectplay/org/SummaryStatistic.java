package com.perfectplay.org;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class SummaryStatistic extends CachedObject{
	private int losses;
	private long modifyDate;
	private String modifyDateStr;
	private String playerStatSummaryType;
	private long summonerId;
	private HashMap<String, Statistic> stats;
	private int wins;
	
	SummaryStatistic(long summonerId, Date time_cached, Statistic[] statArray, int wins, int losses, long modifyDate,
			String modifyDateStr, String playerStatSummaryType){
		stats = new HashMap<String, Statistic>();
		if(statArray != null){
			for(int i = 0; i < statArray.length; i++){
				stats.put(statArray[i].getName(), statArray[i]);
			}
		}
		this.wins = wins;
		this.summonerId = summonerId;
		this.time_cached = time_cached;
		this.losses = losses;
		this.modifyDate = modifyDate;
		this.modifyDateStr = modifyDateStr;
		this.playerStatSummaryType = playerStatSummaryType;
		this.summonerId = summonerId;
	}
	
	public int getLosses(){
		return losses;
	}
	
	public long getModifyDate(){
		return modifyDate;
	}
	
	public String getModifyDateString(){
		return modifyDateStr;
	}
	
	public String getType(){
		return playerStatSummaryType;
	}
	
	public long getSummonerId(){
		return summonerId;
	}
	
	public Statistic[] getStatistics(){
		return stats.values().toArray(new Statistic[stats.values().size()]);
	}
	
	public int getStatistic(String type){
		Statistic s = stats.get(type);
		if(s != null)
			return s.getCount();
		else
			return 0;
	}
	
	public Set<String> keySet(){
		return stats.keySet();
	}
	
	public Collection<Statistic> values(){
		return stats.values();
	}
	
	public int getWins(){
		return wins;
	}
	
	public Summoner getSummoner(){
		return SummonerQuery.getSummoner(summonerId);
	}
	
	public String toString(){
		String temp = "";
		Statistic[] stats = getStatistics();
		if(stats != null){
			temp +=", Stats:";
			for(int i = 0; i < stats.length - 1; i++){
				temp += stats[i].toString() + ",";
			}
			if(stats.length > 0)
				temp += stats[stats.length - 1].toString();
		}
		
		return "[Wins:" + wins + ", Losses:" + losses + ", Modify Date:" + modifyDate + ", Modify Date(String):" 
				+ modifyDateStr + ", Type:" + playerStatSummaryType + temp + "]";
	}
}
