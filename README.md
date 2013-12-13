JLoL
====

Simple Java wrapper for the League of Legends API with built in data caching and cache timeout.

### Currently Supported:  
-Champion queries  
-Summoner queries  
-Rune page queries  
-Mastery queries  
-Custom Exception handling  
-Data caching  
-Cache timeout  

### To Do:  
-Team queries  
-Stats queries  
-Game queries  
-League queries  

### How to query:

	import com.perfectplay.org.League;

set up query parameters
	League.region = "na";
	League.api_key = "XXXXX-XXXXXX-XXXX-XXXXXXXXXXXX";
	
query champions
	Champion[] champions =  League.getChampions();
	Champion[] freeChampions = League.getFreeChampions();
	
query a single champion
	Champion vi = League.getChampion("vi");
	int attackRank = vi.getAttackRank();
	
query a summoner
	Summoner dyrus = League.getSummoner("Dyrus");
	
query masteries/runes from a summoner or do a seperate query 
	RunePage[] runes = dyrus.getRunes();
	MasteryPage[] masteries = League.getMasteries("Dyrus");
	
Handle invalid queries
	try{
		Talent talent = League.getMasteries("Random_Player")[0].getTalents()[0];
	}catch(InvalidQueryException e){
		if(e.Error == InvalidQueryException.NOT_FOUND){
			System.out.println("OH NO! Player not found!");
		}
		if(e.Error == InvalidQueryException.REQUEST_LIMIT){
			System.out.println("only 5 per 10 seconds and 50 per 10 minutes!");
		}
		etc....
	}
	
Check to see how many queries have been called
	League.getQueryCount();


