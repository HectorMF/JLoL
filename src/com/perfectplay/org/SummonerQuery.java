package com.perfectplay.org;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.joda.time.DateTime;

/*
* Used to query the League of Legends servers for a summoner related queries. 
* All data queried is cached for future retrieval, and expires given cache_refresh time.
* 
* @author Hector Medina-Fetterman
* @version 1.0 12/13/13
*/
class SummonerQuery extends Query{
	static long cache_refresh = 3600000l;
	static int cache_size = 100;
	private static Map<String, Summoner> summonersByName = Collections.synchronizedMap(new LruCache<String, Summoner>(cache_size));
	private static Map<Long, Summoner> summonersById = Collections.synchronizedMap(new LruCache<Long, Summoner>(cache_size));
	
	private static Map<Long, MasteryPage[]> masteriesById = Collections.synchronizedMap(new LruCache<Long, MasteryPage[]>(cache_size));
	private static Map<Long, RunePage[]> runesById = Collections.synchronizedMap(new LruCache<Long, RunePage[]>(cache_size));
	
	/*
 	 * Queries the servers for a summoner given their name. 
 	 * Caches the summoner by Id and Name.
 	 * 
 	 * @throws InvalidQueryException if querying the server fails
	 */	
	private static void queryByName(String name){
		try {
			count++;
			URL url = new URL(Query.generateSummonerNameURL(name));
		    JsonReader reader = Json.createReader(url.openStream());
		    JsonObject summoner = reader.readObject();
		    String sName = summoner.getString("name");

		    summonersByName.put(sName.toLowerCase(), new Summoner(
		    			new DateTime(),
		    			Long.parseLong(summoner.get("id").toString()), 
		    			sName, 
		    			Long.parseLong(summoner.get("summonerLevel").toString()), 
		    			summoner.getInt("profileIconId"), 
		    			Long.parseLong(summoner.get("revisionDate").toString()), 
		    			summoner.getString("revisionDateStr")));
		    
		    summonersById.put(Long.parseLong(summoner.get("id").toString()), new Summoner(
	    			new DateTime(),
	    			Long.parseLong(summoner.get("id").toString()), 
	    			sName, 
	    			Long.parseLong(summoner.get("summonerLevel").toString()), 
	    			summoner.getInt("profileIconId"), 
	    			Long.parseLong(summoner.get("revisionDate").toString()), 
	    			summoner.getString("revisionDateStr")));
	    
		} catch (IOException e) {
			throw InvalidQueryException.generateException(e.getMessage());
		}
	}
	
	/*
 	 * Queries the servers for a summoner given their id. 
 	 * Caches the summoner by Id and Name.
 	 * 
 	 * @throws InvalidQueryException if querying the server fails
	 */	
	private static void queryById(Long id){
		try {
			count++;
			URL url = new URL(Query.generateSummonerIdURL(id));
		    JsonReader reader = Json.createReader(url.openStream());
		    JsonObject summoner = reader.readObject();
		    String sName = summoner.getString("name");

		    summonersByName.put(sName.toLowerCase(), new Summoner(
		    			new DateTime(),
		    			Long.parseLong(summoner.get("id").toString()), 
		    			sName, 
		    			Long.parseLong(summoner.get("summonerLevel").toString()), 
		    			summoner.getInt("profileIconId"), 
		    			Long.parseLong(summoner.get("revisionDate").toString()), 
		    			summoner.getString("revisionDateStr")));
		    
		    summonersById.put(Long.parseLong(summoner.get("id").toString()), new Summoner(
	    			new DateTime(),
	    			Long.parseLong(summoner.get("id").toString()), 
	    			sName, 
	    			Long.parseLong(summoner.get("summonerLevel").toString()), 
	    			summoner.getInt("profileIconId"), 
	    			Long.parseLong(summoner.get("revisionDate").toString()), 
	    			summoner.getString("revisionDateStr")));
	    
		} catch (IOException e) {
			throw InvalidQueryException.generateException(e.getMessage());
		}
	}
	
	/*
 	 * Queries the servers for a summoner's mastery pages. 
 	 * Caches the mastery pages and stores them by summoner ID;
 	 * 
 	 * @throws InvalidQueryException if querying the server fails
	 */	
	private static void queryMasteries(long id){
		try {
			count++;
			URL url = new URL(Query.generateMasteryURL(id));
		    JsonReader reader = Json.createReader(url.openStream());
		    JsonObject query = reader.readObject();
		    JsonArray pages = (JsonArray) query.get("pages");
		    MasteryPage[] masteries = new MasteryPage[pages.size()];
		    for(int i = 0; i < pages.size(); i++){
		    	JsonObject page = (JsonObject) pages.get(i);
		    	String name = page.getString("name");
		    	boolean current = page.getBoolean("current");
		    	JsonArray talents = page.getJsonArray("talents");
		    	Talent[] t = new Talent[talents.size()];
		    	for(int j = 0; j < talents.size(); j ++){
		    		JsonObject talent = talents.getJsonObject(j);
		    		int tId = talent.getInt("id");
		    		String tName = talent.getString("name");
		    		int tRank = talent.getInt("rank");
		    		t[j] = new Talent(tId,tName,tRank);
		    	}
		    	masteries[i] = new MasteryPage(new DateTime(), id, t, current, name);
		    }
		    
		    masteriesById.put(id, masteries);
		  	    
		} catch (IOException e) {
			throw InvalidQueryException.generateException(e.getMessage());
		}
	}
	
	/*
 	 * Queries the servers for a summoner's rune pages. 
 	 * Caches the pages and stores them by summoner ID;
 	 * 
 	 * @throws InvalidQueryException if querying the server fails
	 */	
	private static void queryRunes(long id){
		try {
			count++;
			URL url = new URL(Query.generateRuneURL(id));
		    JsonReader reader = Json.createReader(url.openStream());
		    JsonObject query = reader.readObject();
		    JsonArray pages = (JsonArray) query.get("pages");
		    RunePage[] runes = new RunePage[pages.size()];
		    for(int i = 0; i < pages.size(); i++){
		    	JsonObject page = (JsonObject) pages.get(i);
		    	int pId = page.getInt("id");
		    	String name = page.getString("name");
		    	boolean current = page.getBoolean("current");
		    	
		    	JsonArray runeSlot = page.getJsonArray("slots");
		    	Rune[] rune = new Rune[runeSlot.size()];
		    	for(int j = 0; j < runeSlot.size(); j ++){
		    		JsonObject rSlot = runeSlot.getJsonObject(j);
		    		
		    		int slot = rSlot.getInt("runeSlotId");
		    		JsonObject r = rSlot.getJsonObject("rune");
		    		int rId = r.getInt("id");
		    		String rName = r.getString("name");
		    		String rDesc = r.getString("description");
		    		int rTier = r.getInt("tier");
		    		rune[j] = new Rune(slot, rDesc,rId,rName,rTier);
		    	}
		    	runes[i] = new RunePage(new DateTime(), pId, rune, current, name);
		    }
		    
		    runesById.put(id, runes);
		  	    
		} catch (IOException e) {
			throw InvalidQueryException.generateException(e.getMessage());
		}
	}
	
	/*
 	 * Returns a summoner by name
	 */	
	static Summoner getSummoner(String name){
		Summoner summoner = summonersByName.get(name.toLowerCase());
		if(summoner == null){
			queryByName(name);
			summoner = summonersByName.get(name.toLowerCase());
		}else if(DateTime.now().isAfter(summoner.getTimeCached().plus(cache_refresh))){
			queryByName(name);
			summoner = summonersByName.get(name.toLowerCase());
		}
		return summoner;
	}
	
	/*
 	 * Returns a summoner by id
	 */	
	static Summoner getSummoner(long id){
		Summoner summoner = summonersById.get(id);
		if(summoner == null){
			queryById(id);
			summoner = summonersById.get(id);
		}else if(DateTime.now().isAfter(summoner.getTimeCached().plus(cache_refresh))){
			queryById(id);
			summoner = summonersById.get(id);
		}
		return summoner;
	}

	/*
 	 * Returns a summoner's mastery pages
	 */	
	static MasteryPage[] getMasteries(long id){
		MasteryPage[] pages = masteriesById.get(id);
		if(pages == null){
			queryMasteries(id);
			pages = masteriesById.get(id);
		}else if(DateTime.now().isAfter(pages[0].getTimeCached().plus(cache_refresh))){
			queryMasteries(id);
			pages = masteriesById.get(id);
		}
		return pages;
	}
	
	/*
 	 * Returns a summoner's rune pages
	 */	
	static RunePage[] getRunes(long id){
		RunePage[] pages = runesById.get(id);
		if(pages == null){
			queryRunes(id);
			pages = runesById.get(id);
		}else if(DateTime.now().isAfter(pages[0].getTimeCached().plus(cache_refresh))){
			queryRunes(id);
			pages = runesById.get(id);
		}
		return pages;
	}
	
	/*
 	 * Given a list of summoner IDs, returns a Map of ids to names.
 	 * 
 	 * @throws InvalidQueryException if querying the server fails
	 */	
	static HashMap<Long,String> getSummonerNames(long[] ids){
		HashMap<Long,String> summonerNames = new HashMap<Long,String>();
		try {
			count++;
			URL url = new URL(SummonerQuery.generateSummonerIdsURL(ids));
		    JsonReader reader = Json.createReader(url.openStream());
		    JsonArray summoners = reader.readObject().getJsonArray("summoners");
		    for(int i = 0; i < summoners.size(); i++){
		    	JsonObject summoner = (JsonObject)summoners.get(i);
		    	String name = summoner.getString("name");
		    	summonerNames.put(Long.parseLong(summoner.get("id").toString()),name);
		    }
		} catch (IOException e) {
			throw InvalidQueryException.generateException(e.getMessage());
		}

		return summonerNames;
	}

	static void clear() {
		summonersById.clear();
		summonersByName.clear();
		masteriesById.clear();
		runesById.clear();	
	}
	
	
	
}
