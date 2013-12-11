package com.perfectplay.org;

public class Champion {
	private long id;
	
	private String name;
	
	private boolean active;
	
	private int attackRank;
	private int magicRank;
	private int defenseRank;
	private int difficultyRank;
	
	private boolean botEnabled;
	private boolean botMmEnabled;

	private boolean rankedPlayEnabled;
	
	private boolean freeToPlay;
	
	Champion(long id, String name, boolean active, int attackRank, 
			int magicRank, int defenseRank, int difficultyRank, boolean botEnabled, 
			boolean botMmEnabled, boolean rankedPlayEnabled, boolean freeToPlay){
		this.id = id;
		this.name = name;
		this.active = active;
		this.attackRank = attackRank;
		this.magicRank = magicRank;
		this.defenseRank = defenseRank;
		this.difficultyRank = difficultyRank;
		this.botEnabled = botEnabled;
		this.botMmEnabled = botMmEnabled;
		this.rankedPlayEnabled = rankedPlayEnabled;
		this.freeToPlay = freeToPlay;
	}
	
	public long getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isActive(){
		return active;
	}
	
	public boolean isBot(){
		return botEnabled;
	}
	
	public boolean isMatchMadeBot(){
		return botMmEnabled;
	}
	
	public boolean isFreeToPlay(){
		return freeToPlay;
	}
	
	public boolean isRankEnabled(){
		return rankedPlayEnabled;
	}
	
	public int getAttackRank(){
		return attackRank;
	}
	
	public int getMagicRank(){
		return magicRank;
	}
	
	public int getDefenseRank(){
		return defenseRank;
	}
	
	public int getDifficultyRank(){
		return difficultyRank;
	}
	
	public String toString(){
		return "[ID:" + id + ", Name:" + name + ", Active:" + active + ", Attack Rank:" 
				+ attackRank + ", Defense Rank:" + defenseRank + ", Magic Rank:" + magicRank 
				+ ", Difficulty Rank:" + difficultyRank + ", Bot Enabled:" + botEnabled + ", Bot Enabled(MM):" 
				+ botMmEnabled + ", Ranked Enabled:" + rankedPlayEnabled + "]";
	}
}
