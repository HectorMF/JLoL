package com.perfectplay.org;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.joda.time.DateTime;


/*
* Used to query the League of Legends servers for a list of the
* currently available champions. Champions are then cached until 
* needed. The cache times out after the given refresh time.
* 
* @author Hector Medina-Fetterman
* @version 1.0 12/11/13
*/
class ChampionQuery{
	static HashMap<String,Champion> champions = null;
	static DateTime time_cached;

	/*
 	 * Queries the servers for all currently available champions. 
 	 * A HashMap<String,Champion> is created for easy Champion retrieval by name.
 	 * 
 	 * @throws IOException if querying the server fails
	 */
	private static void Query(){
		try {
			time_cached = new DateTime();
			URL url = new URL(ChampionQuery.generateURL());
		    JsonReader reader = Json.createReader(url.openStream());
		    champions = new HashMap<String,Champion>();
		    JsonArray champs = reader.readObject().getJsonArray("champions");
		    
		    //Parse through each champion and add the champion to the HashMap
		    JsonObject champ;
		    for(int i = 0; i < champs.size(); i++){
		    	champ = (JsonObject)champs.get(i);
		    	String name = champ.get("name").toString().substring(1,champ.get("name").toString().length()-1);
		    	champions.put(name.toLowerCase(), new Champion(
		    			Long.parseLong(champ.get("id").toString()), 
		    			name, 
		    			Boolean.parseBoolean(champ.get("active").toString()), 
		    			Integer.parseInt(champ.get("attackRank").toString()), 
		    			Integer.parseInt(champ.get("magicRank").toString()), 
		    			Integer.parseInt(champ.get("defenseRank").toString()), 
		    			Integer.parseInt(champ.get("difficultyRank").toString()), 
		    			Boolean.parseBoolean(champ.get("botEnabled").toString()), 
		    			Boolean.parseBoolean(champ.get("botMmEnabled").toString()), 
		    			Boolean.parseBoolean(champ.get("rankedPlayEnabled").toString()), 
		    			Boolean.parseBoolean(champ.get("freeToPlay").toString())));
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Generates a URL string to be used to query the League of Legends servers.
	 */
	private static String generateURL(){
		return League.host + "/api/lol/" + League.region + "/v1.1/champion?api_key=" + League.api_key;
	}
	
	static Champion getChampion(String name){
		if(champions == null || DateTime.now().isAfter(time_cached.plus(League.cache_refresh))){
			ChampionQuery.Query();
		}
		return ChampionQuery.champions.get(name.toLowerCase());
	}
	
	/*
	 * Returns an array of all champions
	 */
	static Champion[] getChampions(){
		if(champions == null || DateTime.now().isAfter(time_cached.plus(League.cache_refresh))){
			ChampionQuery.Query();
		}
		return ChampionQuery.champions.values().toArray(new Champion[ChampionQuery.champions.values().size()]);
	}
	
	/*
	 * Returns an Array of all champions in the free champion rotation.
	 */
	static Champion[] getFreeChampions(){
		if(champions == null || DateTime.now().isAfter(time_cached.plus(League.cache_refresh))){
			ChampionQuery.Query();
		}
		ArrayList<Champion> freeChampions = new ArrayList<Champion>(ChampionQuery.champions.values());
		for(int i = freeChampions.size() - 1; i >= 0; i--){
			if(!freeChampions.get(i).isFreeToPlay()){
				freeChampions.remove(i);
			}
		}
		return freeChampions.toArray(new Champion[freeChampions.size()]);
	}
}
