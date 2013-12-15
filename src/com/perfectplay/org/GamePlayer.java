package com.perfectplay.org;

public class GamePlayer {
	private int championId;
	private long summonerId;
	private int teamId;
	
	GamePlayer(long summonerId,int championId, int teamId){
		this.championId = championId;
		this.summonerId = summonerId;
		this.teamId = teamId;
	}
	
	public int getChampionId(){
		return championId;
	}
	
	public long getSummonerId(){
		return summonerId;
	}
	
	public Champion getChampion(){
		return ChampionQuery.getChampion(championId);
	}
	
	public Summoner getSummoner(){
		return SummonerQuery.getSummoner(summonerId);
	}
	
	public int getTeamId(){
		return teamId;
	}
	
	public String toString(){
		return "[Team:" + teamId + ", Summoner ID:" + summonerId + ", Champion:" + championId + "]";
	}
	
	
}
