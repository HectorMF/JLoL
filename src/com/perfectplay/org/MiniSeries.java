package com.perfectplay.org;

public class MiniSeries {
	private int losses;
	private char[] progress;
	private int target;
	private long timeLeftToPlayMillis;
	private int wins;
	
	MiniSeries(int losses, char[] progress, int target, long timeLeftToPlayMillis, int wins){
		this.losses = losses;
		this.progress = progress;
		this.target = target;
		this.timeLeftToPlayMillis = timeLeftToPlayMillis;
		this.wins = wins;
	}
	
	public int getLosses(){
		return losses;
	}
	
	public char[] getProgress(){
		return progress;
	}
	
	public int getTarget(){
		return target;
	}
	
	public long getTimeLeft(){
		return timeLeftToPlayMillis;
	}
	
	public int getWins(){
		return wins;
	}
	
	public String toString(){
		return "[Wins:" + wins + ", Losses:" + losses + ", Target:" + target 
				+ ", Progress:" + progress.toString() +  ", Time Left:" + timeLeftToPlayMillis + "]";
	}
}

