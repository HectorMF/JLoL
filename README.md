JLoL
====

## Overview

JLoL is a Java wrapper for the League of Legends API with built in data caching and cache timeout to minimize API calls.

## Setting Up

This code is used to setup the environment for querying.

```java
	import com.perfectplay.org.*;
	
	//set up query parameters
	JLOL.region = "na";
	JLOL.api_key = "XXXXX-XXXXXX-XXXX-XXXXXXXXXXXX";
	
	/* 
	 * These are optional parameters. The cache is defaulted to 
	 * 100 elements for each query type, and a time out period of 
	 * 1 hour. 
	 */
	JLOL.setCacheSize(100);
	JLOL.setCacheTimeOut(1);
```

Simply changing the api_key or region will alter the parameters for newly made queries. To turn off caching, simply set the cache size to 0. Timeout determines if a cached item is too old to be reliable. Cached elements that pass their age limit, are not removed from the cache.

## Exception Handling

JLoL uses a custom unchecked exception to handle invalid queries made to the League of Legends servers. 
As the exceptions are unchecked, handling them is not enforced by the compiler. Below is an example on how to handle InvalidQueryException:

```java
	try{
		//your code here
		
	}catch(InvalidQueryException e){
	
		if(e.error == InvalidQueryException.NOT_FOUND){
			System.out.println("OH NO! Player not found!");
		}
		
		if(e.error == InvalidQueryException.REQUEST_LIMIT){
			System.out.println("only 5 per 10 seconds and 50 per 10 minutes!");
		}
		//etc....
		
	}
```

## Querying a Summoner

The following code queries a summoner from the League servers.

```java
	Summoner dyrus = JLOL.getSummoner("Dyrus");
```

Note that if caching is enabled, multiple calls to retrieve this summoner will result in only 1 server query.
The summoner class provides access to all summoner related data, as well as other useful queries.

```java
	//standard summoner data
	int level = dyrus.getLevel();
	int profileIcon = dyrus.getIconId();
	
	//other queryable data.
	RunePage[] runes = dyrus.getRunes();
	MasteryPage[] masteries = dyrus.getMasteries();
	League[] leagues = dyrus.getLeagues();
```

## Querying a Champion

Querying a champion is similar to querying for a summoner


```java
	//query champions, this code will only query the league servers once
	Champion[] champions =  JLOL.getChampions();
	Champion[] freeChampions = JLOL.getFreeChampions();
	
	//query a single champion
	Champion vi = JLOL.getChampion("vi");
	int attackRank = vi.getAttackRank();
```
JLoL
====

Simple Java wrapper for the League of Legends API with built in data caching and cache timeout to minimize API calls.

### Currently Supported:  
-Champion queries  
-Summoner queries  
-Rune page queries  
-Mastery queries  
-Custom Exception handling  
-Data caching  
-Cache timeout  
-Game queries 

### To Do:  
-Team queries  
-Stats queries  
-League queries  

### How to query:

	import com.perfectplay.org.League;

	//set up query parameters
	League.region = "na";
	League.api_key = "XXXXX-XXXXXX-XXXX-XXXXXXXXXXXX";
	
	//query champions
	Champion[] champions =  League.getChampions();
	Champion[] freeChampions = League.getFreeChampions();
	
	//query a single champion
	Champion vi = League.getChampion("vi");
	int attackRank = vi.getAttackRank();
	
	//query a summoner
	Summoner dyrus = League.getSummoner("Dyrus");
	
	//query masteries/runes from a summoner or do a seperate query 
	RunePage[] runes = dyrus.getRunes();
	MasteryPage[] masteries = League.getMasteries("Dyrus");
	
	//Handle invalid queries
	try{
		Talent talent = League.getMasteries("Random_Player")[0].getTalents()[0];
	}catch(InvalidQueryException e){
		if(e.error == InvalidQueryException.NOT_FOUND){
			System.out.println("OH NO! Player not found!");
		}
		if(e.error == InvalidQueryException.REQUEST_LIMIT){
			System.out.println("only 5 per 10 seconds and 50 per 10 minutes!");
		}
		etc....
	}
	
	//Game Queries
	
	Game[] recentGames = League.getRecentGames("Flamespewer");
	
	//get the name of the champion that I just played
	System.out.println(recentGames[0].getChampion().getName());
	
	//Check to see how many queries have been called
	League.getQueryCount();


