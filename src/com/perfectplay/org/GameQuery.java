package com.perfectplay.org;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.joda.time.DateTime;

class GameQuery extends Query{
	private static HashMap<Long, Game[]> gamesById = new HashMap<Long,Game[]>();
	static Long cache_refresh = 3600000l;
	
	/*
 	 * Queries the servers for a list of recently played games for a given summoner 
 	 * 
 	 * @throws IOException if querying the server fails
	 */
	private static void query(long id) throws InvalidQueryException{
		try {
			count++;
			URL url = new URL(Query.generateGameURL(id));
		    JsonReader reader = Json.createReader(url.openStream());
		    JsonObject gameList = reader.readObject();
		    long summonerId = gameList.getInt("summonerId");
		    JsonArray games = gameList.getJsonArray("games");
		    Game[] recentGames = new Game[games.size()];
		    for(int i = 0; i < games.size(); i++){
		    	
		    	JsonObject game = games.getJsonObject(i);
		    	long gameId = Long.parseLong(game.get("gameId").toString());
		    	boolean invalid = game.getBoolean("invalid");
		    	String gameMode = game.getString("gameMode");
		    	String gameType = game.getString("gameType");
		    	String subType = game.getString("subType");
		    	int mapId = game.getInt("mapId");
		    	int teamId = game.getInt("teamId");
		    	int championId = game.getInt("championId");
		    	int spell1 = game.getInt("spell1");
		    	int spell2 = game.getInt("spell2");
		    	int level = game.getInt("level");
		    	long createDate =  Long.parseLong(game.get("createDate").toString());
		    	
		    	String createDateStr = game.get("createDateStr").toString();
		    	JsonArray players = game.getJsonArray("fellowPlayers");
		    	GamePlayer[] playerArray;
		    	if(players != null){
			    	playerArray = new GamePlayer[players.size()];
			    	
			    	for(int j = 0; j < players.size(); j++){
			    		JsonObject player = players.getJsonObject(j);
			    		long playerId = Long.parseLong(player.get("summonerId").toString());
			    		int playerTeamId = player.getInt("teamId");
			    		int playerChampionId = player.getInt("championId");
			    		playerArray[j] = new GamePlayer(playerId, playerChampionId,playerTeamId);
			    	}
		    	}else{
		    		playerArray = new GamePlayer[0];
		    	}
		    	
		    	JsonArray stats = game.getJsonArray("statistics");
		    	GameStatistic[] statistics = new GameStatistic[stats.size()];
		    	
		    	for(int k = 0; k < stats.size(); k++){
		    		JsonObject stat = stats.getJsonObject(k);
		    		int statId = stat.getInt("id");
		    		String statName = stat.getString("name");
		    		int statValue = stat.getInt("value");
		    		statistics[k] = new GameStatistic(statId, statName, statValue);
			    	
			    	
		    	}
		    	recentGames[i] = new Game(new DateTime(), summonerId, championId, createDate, createDateStr, 
		    								playerArray, gameId, gameMode, gameType, invalid, level, mapId, 
		    								spell1, spell2, statistics, subType, teamId);
		    }
		    gamesById.put(summonerId,recentGames);
		} catch (IOException e) {
			throw InvalidQueryException.generateException(e.getMessage());
		}
	}
	
	/*
	 * Returns a champion with the given id or null
	 */
	static Game[] getGames(long id){
		Game[] games = gamesById.get(id);
		if(games == null){
			query(id);
			games = gamesById.get(id);
		}else if(games.length > 0){
			if(DateTime.now().isAfter(games[0].getTimeCached().plus(cache_refresh))){
				query(id);
				games = gamesById.get(id);
			}
		}
		return games;
	}
	
	static void clear() {
		gamesById.clear();
	}
}
