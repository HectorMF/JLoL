package com.perfectplay.org;

public class GameStatistic {
	private int id;
	private String name;
	private int value;
	
	GameStatistic(int id, String name, int value){
		this.id = id;
		this.name = name;
		this.value = value;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public int getValue(){
		return value;
	}
	
	public String toString(){
		return "[ID:" + id + ", Name:" + name + ", Value:" + value + "]";
	}
}
