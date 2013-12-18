package com.perfectplay.org.test;

import java.util.HashMap;
import java.util.Map;

import com.perfectplay.org.JLOL;
import com.perfectplay.org.SummaryStatistic;
import com.perfectplay.org.constants.Season;
import com.perfectplay.org.constants.Stats;

public class Test {

	public Test(){
		JLOL.region = "na";
		JLOL.api_key = "";
		System.out.println(JLOL.getChampion("vi"));
		JLOL.getSummaryStatistics("Flamespewer").get("Unranked");
		for(int i = 0; i < 10; i ++){
			
			System.out.println(JLOL.getSummaryStatistics("Flamespewer").get(Stats.Unranked).getStatistic("TOTAL_ASSISTS"));
		}
		
		System.out.println(JLOL.getQueryCount());
		
		
	}
	
	public static void main(String[] args){
		new Test();
	}
}
