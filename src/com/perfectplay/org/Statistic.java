package com.perfectplay.org;

public class Statistic {
	private int count;
	private int id;
	private String name;
	
	Statistic(int id, int count, String name){
		this.id = id;
		this.count = count;
		this.name = name;
	}
	
	public int getId(){
		return id;
	}
	
	public int getCount(){
		return count;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		return "[ID:" + id + ", Name:" + name + ", Count:" + count + "]";
	}
}
