JLoL
====

JLoL is a Java wrapper for the League of Legends API with built in data caching and cache timeout to minimize API calls.
The library provides custom Exception handling as well as LRU replacement for efficient data storage. 

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

## Querying

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
	Game[] recentGames = dyrus.getRecentGames();
```

Querying a champion is similar to querying for a summoner.

```java
	//query champions, this code will only query the league servers once
	Champion[] champions =  JLOL.getChampions();
	Champion[] freeChampions = JLOL.getFreeChampions();
	
	//query a single champion
	Champion vi = JLOL.getChampion("vi");
	int attackRank = vi.getAttackRank();
```

The other queries follow this format and are also found under the JLOL class.

##### Currently Supported Queries:
-Champion queries  
-Summoner queries  
-Rune page queries  
-Mastery queries 
-Game queries 
-League queries  

##### To DO:
-Team queries  
-Stats queries  

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

## Utility

Getting the total number of queries made:

```java
	int queries = JLOL.getQueryCount();
```

Clearing all caches:

```java
	JLOL.clearCache();
```
