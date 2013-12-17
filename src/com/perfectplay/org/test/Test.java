package com.perfectplay.org.test;

import com.perfectplay.org.JLOL;

public class Test {

	public Test(){
		JLOL.region = "na";
		JLOL.api_key = "a1afbf2e-9eba-40e0-9aeb-6a8143404dde";
		System.out.println(JLOL.getChampion("vi"));
	}
	
	public static void main(String[] args){
		new Test();
	}
}
