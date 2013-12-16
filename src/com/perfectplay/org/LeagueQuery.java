package com.perfectplay.org;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.joda.time.DateTime;

class LeagueQuery extends Query{
	static long cache_refresh = 3600000l;
	static int cache_size = 100;
	private static Map<Long, League[]> leaguesById = Collections.synchronizedMap(new LruCache<Long, League[]>(cache_size));

	private static void query(long id){
		try {
			count++;
			URL url = new URL(Query.generateLeagueURL(id));
		    JsonReader reader = Json.createReader(url.openStream());
		    JsonObject leagueList = reader.readObject();
		    String[] keys = leagueList.keySet().toArray(new String[leagueList.keySet().size()]);
		    League[] leagues = new League[keys.length];
		    for(int i = 0; i < keys.length; i++){
		    	JsonObject league = leagueList.getJsonObject(keys[i]);
		    	long timestamp = Long.parseLong(league.get("timestamp").toString());
		    	String name = league.getString("name");
		    	String tier = league.getString("tier");
		    	String queue = league.getString("queue");
		    	JsonArray entries = league.getJsonArray("entries");
		    	LeagueItem[] leagueItems = new LeagueItem[entries.size()];
		    	for(int j = 0; j < entries.size(); j++){
		    		JsonObject leagueItem = entries.getJsonObject(j);
		    		String playerOrTeamId = leagueItem.getString("playerOrTeamId");
		    		String playerOrTeamName = leagueItem.getString("playerOrTeamName");
		    		String leagueName = leagueItem.getString("leagueName");
		    		String queueType = leagueItem.getString("queueType");
		    		String lTier = leagueItem.getString("tier");
		    		String rank = leagueItem.getString("rank");
		    		int leaguePoints = leagueItem.getInt("leaguePoints");
		    		int wins = leagueItem.getInt("wins");
		    		int losses = leagueItem.getInt("losses");
		    		boolean isHotStreak = leagueItem.getBoolean("isHotStreak");
		    		boolean isVeteran = leagueItem.getBoolean("isVeteran");
		    		boolean isFreshBlood = leagueItem.getBoolean("isFreshBlood");
		    		boolean isInactive = leagueItem.getBoolean("isInactive");
		    		long lastPlayed = Long.parseLong(leagueItem.get("lastPlayed").toString());
		    		long timeUntilDecay = Long.parseLong(leagueItem.get("timeUntilDecay").toString());
		    		
		    		// MINI SERIES aren't being populated in current api? wtf riot fix your shit.
		    		JsonObject miniSeriesObj = leagueItem.getJsonObject("miniSeries");
		    		MiniSeries miniSeries = null;
		    		if(miniSeriesObj != null){
		    			int msLosses = miniSeriesObj.getInt("losses");
		    			char[] progress = miniSeriesObj.getString("progress").toCharArray();
		    			int target = miniSeriesObj.getInt("target");
		    			long timeLeftToPlayMillis = Long.parseLong(miniSeriesObj.get("timeLeftToPlayMillis").toString());
		    			int msWins = miniSeriesObj.getInt("wins");
		    			miniSeries = new MiniSeries(msLosses, progress, target, timeLeftToPlayMillis, msWins);
		    		}
		    		
		    		leagueItems[j] = new LeagueItem(isFreshBlood, isHotStreak, isInactive, isVeteran,
		    				lastPlayed, leagueName,leaguePoints, losses, miniSeries,
		    				playerOrTeamId, playerOrTeamName, queueType, rank, lTier,
		    				timeUntilDecay, wins);
		    	}
		    	leagues[i] = new League(new DateTime(), name, queue, tier, timestamp, leagueItems);
		    }  
	    	leaguesById.put(id, leagues);
		} catch (IOException e) {
			throw InvalidQueryException.generateException(e.getMessage());
		}
	}
	
	static League[] getLeague(long id){
		League[] league = leaguesById.get(id);
		if(league == null){
			query(id);
			league = leaguesById.get(id);
		}else if(league.length > 0){
			if(DateTime.now().isAfter(league[0].getTimeCached().plus(cache_refresh))){
				query(id);
				league = leaguesById.get(id);
			}
		}
		return league;
	}
	
	static void clear(){
		leaguesById.clear();
	}
	
	static void resize(){
		leaguesById = Collections.synchronizedMap(new LruCache<Long, League[]>(cache_size));
	}
}
