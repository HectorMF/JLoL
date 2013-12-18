package com.perfectplay.org;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class StatisticQuery extends Query{
	static long cache_refresh = 3600000l;
	static int cache_size = 100;
	private static Map<Long, HashMap<String,HashMap<String,SummaryStatistic>>> summariesById = Collections.synchronizedMap(new LruCache<Long, HashMap<String,HashMap<String,SummaryStatistic>>>(cache_size));

	private static void queryStatistic(long id, String season){
		try {
			count++;
			URL url = new URL(Query.generateStatisticURL(id, season));
		    JsonReader reader = Json.createReader(url.openStream());
		    JsonObject statsList = reader.readObject();
		    long summonerId = Long.parseLong(statsList.get("summonerId").toString());
		    JsonArray summaryStats = statsList.getJsonArray("playerStatSummaries");
		    SummaryStatistic[] playerStats = new SummaryStatistic[summaryStats.size()];
		    for(int i = 0; i < summaryStats.size(); i++){
		    	
		    	JsonObject stat = summaryStats.getJsonObject(i);
		    	String type = stat.getString("playerStatSummaryType");
		    	int wins = stat.getInt("wins");
		    	int losses = stat.getInt("losses");
		    	long modifyDate = Long.parseLong(stat.get("modifyDate").toString());
		    	String modifyDateStr = stat.get("modifyDateStr").toString();
		    	//System.out.println(stat);
		    	
		    	JsonArray aggregateStats = stat.getJsonArray("aggregatedStats");
		    	Statistic[] aggStats = null;
		    	if(aggregateStats != null){
			    	aggStats = new Statistic[aggregateStats.size()];
			    	for(int j = 0; j < aggStats.length; j++){
			    		JsonObject aStat = aggregateStats.getJsonObject(j);
			    		int aId = aStat.getInt("id");
			    		String aName = aStat.getString("name");
			    		int aCount = aStat.getInt("count");
			    		aggStats[j] = new Statistic(aId, aCount, aName);
			    	}
		    	}
		    	playerStats[i] = new SummaryStatistic(summonerId, new Date(), aggStats, wins, losses, modifyDate, modifyDateStr, type);
		    }
		    HashMap<String, HashMap<String,SummaryStatistic>> map = null;
		    if(summariesById.containsKey(summonerId)){
		    	map = summariesById.get(summonerId);
		    }else{
		    	map = new HashMap<String, HashMap<String,SummaryStatistic>>();
		    }
		    HashMap<String,SummaryStatistic> statsMap = new HashMap<String,SummaryStatistic>();
		    for(int k = 0; k < playerStats.length; k++){
		    	statsMap.put(playerStats[k].getType(), playerStats[k]);
		    }
		    map.put(season, statsMap);
		    summariesById.put(summonerId,map);
		} catch (IOException e) {
			throw InvalidQueryException.generateException(e.getMessage());
		}
	}
	
	static HashMap<String, SummaryStatistic> getSummaryStatistics(long id, String season){
		HashMap<String,HashMap<String, SummaryStatistic>> map = summariesById.get(id);
		if(map == null)
			queryStatistic(id,season);
		
		HashMap<String, SummaryStatistic> summary = summariesById.get(id).get(season);
		if(summary == null){
			queryStatistic(id,season);
			summary = summariesById.get(id).get(season);
		}else if(summary.values().size() > 0){
			SummaryStatistic temp = summary.values().toArray(new SummaryStatistic[summary.values().size()])[0];
			if(new Date().after(new Date(temp.getTimeCached().getTime() + cache_refresh))){
				queryStatistic(id,season);
				summary = summariesById.get(id).get(season);
			}
		}
		return summary;
	}
	
	static SummaryStatistic getSummaryStatisticType(long id, String season, String type){
		HashMap<String, SummaryStatistic> summary = getSummaryStatistics(id, season);
		return summary.get(type);
	}
	
	static void clear(){
		summariesById.clear();
	}
	
}
