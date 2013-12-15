package com.perfectplay.org;

import org.joda.time.DateTime;

public class Game extends CachedObject{
	
	//summoner info for the game
	private long summonerId;
	private int championId;
	private int teamId;
	private int spell1;
	private int spell2;
	private GameStatistic[] statistics;
	
	//list of other players playing the game
	private GamePlayer[] players;
	
	//game information
	private long gameId;
	private String gameMode;
	private String gameType;
	private String subType;
	private boolean invalid;
	private int level;
	private int mapId;
	private long createDate;
	private String createDateStr;

	Game(DateTime time_cached, long summonerId, int championId, long createDate, String createDateStr, GamePlayer[] players, long gameId, String gameMode, String gameType,
			boolean invalid, int level, int mapId, int spell1, int spell2, GameStatistic[] statistics, String subType, int teamId){
		this.time_cached = time_cached;
		this.summonerId = summonerId;
		this.championId = championId;
		this.createDate = createDate;
		this.createDateStr = createDateStr;
		this.players = players;
		this.gameId = gameId;
		this.gameMode = gameMode;
		this.gameType = gameType;
		this.invalid = invalid;
		this.level = level;
		this.mapId = mapId;
		this.spell1 = spell1;
		this.spell2 = spell2;
		this.statistics = statistics;
		this.subType = subType;
		this.teamId = teamId;
	}

	public int getChampionId(){
		return championId;
	}
	
	public Champion getChampion(){
		return ChampionQuery.getChampion(championId);
	}
	
	public long getCreateDate(){
		return createDate;
	}
	
	public String getCreateDateString(){
		return createDateStr;
	}
	
	public GamePlayer[] getPlayers(){
		return players;
	}
	
	public long getGameId(){
		return gameId;
	}
	
	public String getGameMode(){
		return gameMode;
	}
	
	public String getGameType(){
		return gameType;
	}
	
	public boolean isInvalid(){
		return invalid;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getMapId(){
		return mapId;
	}
	
	public int getSpell1(){
		return spell1;
	}
	
	public int getSpell2(){
		return spell2;
	}
	
	public GameStatistic[] getStatistics(){
		return statistics;
	}
	
	public String getSubType(){
		return subType;
	}
	
	public int getTeamId(){
		return teamId;
	}
	
	public long getSummonerId(){
		return summonerId;
	}
	
	public Summoner getSummoner(){
		return SummonerQuery.getSummoner(summonerId);
	}
	
	public String toString(){
		//create a string of all players
		String playerString = "";
		for(int i = 0; i < players.length - 1; i++){
			playerString += players[i].toString() + ",";
		}
		if(players.length > 0)
			playerString += players[players.length - 1].toString();
		
		//create a string for all statistics
		String statString = "";
		for(int i = 0; i < statistics.length -1; i++){
			statString += statistics[i].toString();
		}
		if(statistics.length > 0)
			statString += statistics[statistics.length - 1].toString();
		
		return "[Game ID:" + gameId + ", Game Mode:" + gameMode + ", Game Type:" + gameType + ", Sub Type:" + subType + ", Invalid:" + invalid + ", Map Id:" + mapId + ", Create Date:" + createDate + ", Create Date(String):" + createDateStr +
				", Summoner Id:" + summonerId + ", Champion Id:" + championId + ", Team Id:" + teamId + ", Spell 1:" + spell1 + ", Spell 2:" + spell2 + ", Fellow Players:{"  + playerString + "}, Statistics:{" + statString + "}]";
	}
}
