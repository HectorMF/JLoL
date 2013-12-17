package com.perfectplay.org;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/*
* Used to query the League of Legends servers for a list of the
* currently available champions. Champions are then cached until 
* needed. The cache times out after the given refresh time.
* 
* @author Hector Medina-Fetterman
* @version 1.0 12/11/13
*/
class ChampionQuery extends Query{
	private static HashMap<String,Champion> champions = new HashMap<String,Champion>();
	static Long cache_refresh = 3600000l;
	
	/*
 	 * Queries the servers for all currently available champions. 
 	 * A HashMap<String,Champion> is created for easy Champion retrieval by name.
 	 * 
 	 * @throws IOException if querying the server fails
	 */
	private static void query() throws InvalidQueryException{
		try {
			count++;
			URL url = new URL(Query.generateChampionURL());
		    JsonReader reader = Json.createReader(url.openStream());
		    champions.clear();
		    JsonArray champs = reader.readObject().getJsonArray("champions");
		    
		    //Parse through each champion and add the champion to the HashMap
		    JsonObject champ;
		    for(int i = 0; i < champs.size(); i++){
		    	champ = (JsonObject)champs.get(i);
		    	String name = champ.getString("name");
		    	champions.put(name.toLowerCase(), new Champion(
		    			new Date(),
		    			Long.parseLong(champ.get("id").toString()), 
		    			name, 
		    			champ.getBoolean("active"), 
		    			champ.getInt("attackRank"), 
		    			champ.getInt("magicRank"), 
		    			champ.getInt("defenseRank"), 
		    			champ.getInt("difficultyRank"), 
		    			champ.getBoolean("botEnabled"), 
		    			champ.getBoolean("botMmEnabled"), 
		    			champ.getBoolean("rankedPlayEnabled"), 
		    			champ.getBoolean("freeToPlay")));
		    }
		} catch (IOException e) {
			throw InvalidQueryException.generateException(e.getMessage());
		}
	}
	
	/*
	 * Returns a champion with the given name, or null
	 */
	static Champion getChampion(String name){
		Champion champion = champions.get(name.toLowerCase());
		if(champion == null){
			query();
			champion = champions.get(name.toLowerCase());
		}else if(new Date().after(new Date(champion.getTimeCached().getTime() + cache_refresh))){
			query();
			champion = champions.get(name.toLowerCase());
		}
		
		return champion;
	}
	
	/*
	 * Returns a champion with the given id or null
	 */
	static Champion getChampion(long id){
		if(champions.size() == 0){
			query();
		}
		for(Champion champ : champions.values()){
			if(champ.getId() == id)
				return champ;
		}
		return null;
	}
	
	/*
	 * Returns an array of all champions
	 */
	static Champion[] getChampions(){
		Champion[] champs = ChampionQuery.champions.values().toArray(new Champion[ChampionQuery.champions.values().size()]);
		if(champs.length == 0){
			query();
			champs = ChampionQuery.champions.values().toArray(new Champion[ChampionQuery.champions.values().size()]);
		}else if(new Date().after(new Date(champs[0].getTimeCached().getTime() + cache_refresh))){
			query();
			champs = ChampionQuery.champions.values().toArray(new Champion[ChampionQuery.champions.values().size()]);
		}
		return champs;
	}
	
	/*
	 * Returns an Array of all champions in the free champion rotation.
	 */
	static Champion[] getFreeChampions(){
		ArrayList<Champion> freeChampions = new ArrayList<Champion>(ChampionQuery.champions.values());
		if(freeChampions.size() == 0){
			query();
			freeChampions = new ArrayList<Champion>(ChampionQuery.champions.values());
		}else if(new Date().after(new Date(freeChampions.get(0).getTimeCached().getTime() + cache_refresh))){
			query();
			freeChampions = new ArrayList<Champion>(ChampionQuery.champions.values());
		}
		for(int i = freeChampions.size() - 1; i >= 0; i--){
			if(!freeChampions.get(i).isFreeToPlay()){
				freeChampions.remove(i);
			}
		}
		return freeChampions.toArray(new Champion[freeChampions.size()]);
	}

	static void clear() {
		champions.clear();
	}
}
