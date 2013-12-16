package com.perfectplay.org.test;

import com.perfectplay.org.*;

public class Test {

	public Test(){
		JLoL.region = "na";
		JLoL.api_key = "";
		Game[] games = JLoL.getRecentGames("Galeren");
		
		for(Game game: games){
			System.out.println(game);
		}
		GamePlayer[] players = games[0].getPlayers();
		//System.out.println(players[1].getChampion().getName());
		
		League[] leagues = JLoL.getLeagues("KOR Kez");
		System.out.println(JLoL.getQueryCount());
		leagues = JLoL.getLeagues("Dyrus");

		leagues = JLoL.getLeagues("KOR Kez");
		System.out.println(JLoL.getQueryCount());
		for(League league : leagues){
			System.out.println(league);
		}
	}
	public static void main(String[] args){
		new Test();
	}
}
