JLoL
====

Simple Java wrapper for the League of Legends API

Currently Supported:
Cached Champion queries with cache timeout

How to query:

import com.perfectplay.org.League;

	//set up query parameters
	League.region = "na";
	League.api_key = "XXXXX-XXXXXX-XXXX-XXXXXXXXXXXX";
	
	//query away
	Champion[] champions =  League.getChampions();
	Champion[] freeChampions = League.getFreeChampions();
	Champion vi = League.getChampion("vi");
	int attackRank = vi.getAttackRank();

	will finish this later today.(12/11/13)
	

