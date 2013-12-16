package com.perfectplay.org.test;

import com.perfectplay.org.*;

public class Test {

	public Test(){
		JLOL.region = "na";
		JLOL.api_key = "";
		Game[] games = JLOL.getRecentGames("Galeren");
		System.out.println(JLOL.getChampion("test"));
		for(Game game: games){
			
		}
		GamePlayer[] players = games[0].getPlayers();
		//System.out.println(players[1].getChampion().getName());
		
		League[] leagues = JLOL.getLeagues("KOR Kez");
		System.out.println(JLOL.getQueryCount());
		leagues = JLOL.getLeagues("Dyrus");

		leagues = JLOL.getLeagues("KOR Kez");
		System.out.println(JLOL.getQueryCount());
		for(League league : leagues){
			System.out.println(league);
		}
		
	}
	public static void main(String[] args){
		new Test();
	}
}
