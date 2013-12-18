package com.perfectplay.org;

public class ChampionStatistic {
	private int championId;
	private String championName;
	private Statistic[] stats;
	
	ChampionStatistic(int championId, String championName, Statistic[] stats){
		this.championId = championId;
		this.championName = championName;
		this.stats = stats;
	}
	
	public int getChampionId(){
		return championId;
	}
	
	public Champion getChampion(){
		return ChampionQuery.getChampion(championId);
	}
	
	public String getChampionName(){
		return championName;
	}
	
	public Statistic[] getStatistics(){
		return stats;
	}
}
