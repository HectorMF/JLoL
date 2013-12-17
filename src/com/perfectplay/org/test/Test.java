package com.perfectplay.org.test;

import com.perfectplay.org.*;

public class Test {

	public Test(){
		JLOL.region = "na";
		JLOL.api_key = "a1afbf2e-9eba-40e0-9aeb-6a8143404dde";
		Game[] games = JLOL.getRecentGames("Galeren");
		System.out.println(JLOL.getSummoner("Galeren"));
		for(Game game: games){
			System.out.println(game);
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
