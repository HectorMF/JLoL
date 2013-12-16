package com.perfectplay.org.test;

import com.perfectplay.org.Game;
import com.perfectplay.org.GamePlayer;
import com.perfectplay.org.JLoL;
import com.perfectplay.org.League;
import com.perfectplay.org.LeagueQuery;
public class Test {

	public Test(){
		JLoL.region = "na";
		JLoL.api_key = "";
		Game[] games = JLoL.getRecentGames("Galeren");
		
		for(Game game: games){
			System.out.println(game);
		}
		GamePlayer[] players = games[0].getPlayers();
		System.out.println(players[1].getChampion().getName());
		League[] leagues = LeagueQuery.getLeague(JLoL.getSummoner("KOR Kez").getId());
		
		for(League league : leagues){
			System.out.println(league);
		}
	}
	public static void main(String[] args){
		new Test();
	}
}
