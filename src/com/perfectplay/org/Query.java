package com.perfectplay.org;

abstract class Query {
	public static int count = 0;
	private static String host = "http://prod.api.pvp.net";
	
	/*
	 * Clears the Cache
	 */
	static void clear() {}
	
	/*
	 * Generates a URL to get a summoner's mastery pages
	 */
	protected static String generateMasteryURL(long summonerId){
		return host + "/api/lol/" + League.region + "/v1.1/summoner/" + summonerId+ "/masteries?api_key=" + League.api_key;
	}
	
	/*
	 * Generates a URL to get a summoner's rune pages
	 */
	protected static String generateRuneURL(long summonerId){
		return host + "/api/lol/" + League.region + "/v1.1/summoner/" + summonerId+ "/runes?api_key=" + League.api_key;
	}
	
	/*
	 * Generates a URL to get a summoner by ID
	 */
	protected static String generateSummonerIdURL(long summonerId){
		return host + "/api/lol/" + League.region + "/v1.1/summoner/" + summonerId+ "?api_key=" + League.api_key;
	}
	
	/*
	 * Generates a URL to get names of summoners by ID
	 */
	protected static String generateSummonerIdsURL(long[] summonerIds){
		String temp = "";
		for(int i = 0; i < summonerIds.length; i++){
			temp += summonerIds[i] +",";
		}
		temp = temp.substring(0, temp.length() - 1);
		return host + "/api/lol/" + League.region + "/v1.1/summoner/" + temp + "/name?api_key=" + League.api_key;
	}
	
	/*
	 * Generates a URL to get a summoner by name
	 */
	protected static String generateSummonerNameURL(String name){
		return host + "/api/lol/" + League.region + "/v1.1/summoner/by-name/" + name + "?api_key=" + League.api_key;
	}
	
	/*
	 * Generates a URL to get champions
	 */
	protected static String generateChampionURL(){
		return host + "/api/lol/" + League.region + "/v1.1/champion?api_key=" + League.api_key;
	}
	
	/*
	 * Generates a URL to get game info
	 */
	protected static String generateGameURL(long id){
		return host + "/api/lol/" + League.region + "/v1.1/game/by-summoner/" + id + "/recent?api_key=" + League.api_key;
	}
}
