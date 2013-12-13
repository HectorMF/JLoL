package com.perfectplay.org;


public class League {
	public static String api_key = "";
	public static String region = "";

	private League() { }
	
	/*
	 * Returns a champion, given it's name. 
	 */
	public static Champion getChampion(String name) throws InvalidQueryException{
		return ChampionQuery.getChampion(name);
	}
	
	/**
	 * Returns an array of all champions
	 */
	public static Champion[] getChampions() throws InvalidQueryException{
		return ChampionQuery.getChampions();
	}
	
	/*
	 * Returns an Array of all champions in the free champion rotation
	 */
	public static Champion[] getFreeChampions() throws InvalidQueryException{
		return ChampionQuery.getFreeChampions();
	}
	
	/**
	 * Returns the summoner with the given name
	 * @throws InvalidQueryException
	 */
	public static Summoner getSummoner(String name) throws InvalidQueryException{
		return SummonerQuery.getSummoner(name);
	}
	
	/* 
	 * Returns the summoner with the given id
	 */
	public static Summoner getSummoner(long id) throws InvalidQueryException{
		return SummonerQuery.getSummoner(id);
	}
	
	public static MasteryPage[] getSummonerMasteries(long id) throws InvalidQueryException{
		return SummonerQuery.getMasteries(id);
	}
	
	public static RunePage[] getSummonerRunes(long id) throws InvalidQueryException{
		return SummonerQuery.getRunes(id);
	}
	
	public static MasteryPage[] getSummonerMasteries(String name) throws InvalidQueryException{
		Summoner summoner = getSummoner(name);
		return SummonerQuery.getMasteries(summoner.getId());
	}
	
	public static RunePage[] getSummonerRunes(String name) throws InvalidQueryException{
		Summoner summoner = getSummoner(name);
		return SummonerQuery.getRunes(summoner.getId());
	}
	
	public static int getQueryCount(){
		return Query.count;
	}
}
