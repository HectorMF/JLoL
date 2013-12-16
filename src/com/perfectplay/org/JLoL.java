package com.perfectplay.org;


public class JLOL {
	public static String api_key = "";
	public static String region = "";

	private JLOL() { }
	
	/**
	 * This method returns the champion with the given name. If the champion doesn't exist,
	 * null is returned.
	 *
	 * @param  name the name of the champion for querying
	 * @return      the champion being queried
	 * @throws InvalidQueryException if the query does not complete for any reason.
	 */
	public static Champion getChampion(String name) throws InvalidQueryException{
		return ChampionQuery.getChampion(name);
	}
	
	/**
	 * This method queries the League of Legends servers for all currently 
	 * available champions. 
	 *
	 * @return      an array of all current champions
	 * @throws InvalidQueryException if the query does not complete for any reason
	 */
	public static Champion[] getChampions() throws InvalidQueryException{
		return ChampionQuery.getChampions();
	}
	
	/**
	 * This method returns an array of all free champions. If all champions have already
	 * been cached, this will simply filter out the free ones to minimize API calls.
	 *
	 * @return      an array of all free champions
	 * @throws InvalidQueryException if the query does not complete for any reason
	 */
	public static Champion[] getFreeChampions() throws InvalidQueryException{
		return ChampionQuery.getFreeChampions();
	}
	
	/**
	 * This method returns the summoner with the given name. 
	 * An invalid summoner name will throw an InvalidQueryException with
	 * the BAD_REQUEST error.  
	 *
	 * @param  name the name of the summoner for querying
	 * @return      the summoner being queried
	 * @throws InvalidQueryException if the query does not complete for any reason.
	 */
	public static Summoner getSummoner(String name) throws InvalidQueryException{
		return SummonerQuery.getSummoner(name);
	}
	
	/**
	 * This method returns the summoner with the given id. 
	 * An invalid summoner id will throw an InvalidQueryException with
	 * the BAD_REQUEST error.  
	 *
	 * @param  id the id of the summoner being queried
	 * @return      the summoner being queried
	 * @throws InvalidQueryException if the query does not complete for any reason.
	 */
	public static Summoner getSummoner(long id) throws InvalidQueryException{
		return SummonerQuery.getSummoner(id);
	}
	
	/**
	 * This method returns the mastery pages of a summoner given their id.
	 *
	 * @param  id the id of a summoner for querying
	 * @return      the mastery pages of the summoner
	 * @throws InvalidQueryException if the query does not complete for any reason.
	 */
	public static MasteryPage[] getSummonerMasteries(long id) throws InvalidQueryException{
		return SummonerQuery.getMasteries(id);
	}
	
	/**
	 * This method returns the rune pages of a summoner given their id.
	 *
	 * @param  id the id of a summoner for querying
	 * @return      the rune pages of the summoner
	 * @throws InvalidQueryException if the query does not complete for any reason.
	 */
	public static RunePage[] getSummonerRunes(long id) throws InvalidQueryException{
		return SummonerQuery.getRunes(id);
	}
	
	/**
	 * This method returns the mastery pages of a summoner given their name.
	 * Note that this query requires two calls to the League servers unless the
	 * summoner has been previously queried and is stored in cache.
	 *
	 * @param  name the name of the summoner to query
	 * @return      the mastery pages of the summoner
	 * @throws InvalidQueryException if the query does not complete for any reason.
	 */
	public static MasteryPage[] getSummonerMasteries(String name) throws InvalidQueryException{
		Summoner summoner = getSummoner(name);
		return SummonerQuery.getMasteries(summoner.getId());
	}
	
	/**
	 * This method returns the rune pages of a summoner given their name.
	 * Note that this query requires two calls to the League servers unless the
	 * summoner has been previously queried and is stored in cache.
	 *
	 * @param  name the name of the summoner to query
	 * @return      the rune pages of the summoner
	 * @throws InvalidQueryException if the query does not complete for any reason.
	 */
	public static RunePage[] getSummonerRunes(String name) throws InvalidQueryException{
		Summoner summoner = getSummoner(name);
		return SummonerQuery.getRunes(summoner.getId());
	}
	
	/**
	 * This method returns up to 10 games that the summoner has recently played.
	 * Note that this query requires two calls to the League servers unless the
	 * summoner has been previously queried and is stored in cache.
	 *
	 * @param  name the name of the summoner to query
	 * @return      games the summoner has recently played
	 * @throws InvalidQueryException if the query does not complete for any reason.
	 */
	public static Game[] getRecentGames(String name) throws InvalidQueryException{
		Summoner summoner = getSummoner(name);
		return GameQuery.getGames(summoner.getId());
	}
	
	/**
	 * This method returns up to 10 games that the summoner has recently played.
	 *
	 * @param  id the id of the summoner to query
	 * @return      games the summoner has recently played
	 * @throws InvalidQueryException if the query does not complete for any reason.
	 */
	public static Game[] getRecentGames(long id) throws InvalidQueryException{
		return GameQuery.getGames(id);
	}
	
	/**
	 * This method returns the leagues a summoner is in given their unique Id.
	 *
	 * @param  id the id of the summoner to query
	 * @return      leagues the summoner is in
	 * @throws InvalidQueryException if the query does not complete for any reason.
	 */
	public static League[] getLeagues(long id) throws InvalidQueryException{
		return LeagueQuery.getLeague(id);
	}
	
	/**
	 * This method returns the leagues a summoner is in given their name.
	 *
	 * @param  name the name of the summoner to query
	 * @return      leagues the summoner is in
	 * @throws InvalidQueryException if the query does not complete for any reason.
	 */
	public static League[] getLeagues(String name) throws InvalidQueryException{
		Summoner summoner = getSummoner(name);
		return LeagueQuery.getLeague(summoner.getId());
	}
	
	/**
	 * Returns the total number of queries made to the League of Legends servers.
	 */
	public static int getQueryCount(){
		return Query.count;
	}
	
	/**
	 * Clears all cached data.
	 */
	public static void clearCache(){
		ChampionQuery.clear();
		GameQuery.clear();
		SummonerQuery.clear();
		LeagueQuery.clear();
	}
}
