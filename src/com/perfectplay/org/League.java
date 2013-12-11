package com.perfectplay.org;

public class League {
	public static String api_key = "";
	public static String region = "";
	public static Long cache_refresh = 100000l;
	
	static String host = "http://prod.api.pvp.net";
	
	
	
	private League() { }
	
	/*
	 * Returns a champion, given it's name. 
	 */
	public static Champion getChampion(String name){
		return ChampionQuery.getChampion(name);
	}
	
	/*
	 * Returns an array of all champions
	 */
	public static Champion[] getChampions(){
		return ChampionQuery.getChampions();
	}
	
	/*
	 * Returns an Array of all champions in the free champion rotation
	 */
	public static Champion[] getFreeChampions(){
		return ChampionQuery.getFreeChampions();
	}
}
