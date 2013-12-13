package com.perfectplay.org;

public class Talent {
	private int id;
	private String name;
	private int rank;
	
	Talent(int id, String name, int rank){
		this.id = id;
		this.name = name;
		this.rank = rank;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public int getRank(){
		return rank;
	}
	
	public String toString(){
		return "[ID:" + id + ", Name:" + name + ", Rank:" + rank +"]";
	}
}
