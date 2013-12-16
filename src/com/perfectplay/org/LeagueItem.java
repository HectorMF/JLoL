package com.perfectplay.org;

public class LeagueItem {
	private boolean isFreshBlood;
	private boolean isHotStreak;
	private boolean isInactive;
	private boolean isVeteran;
	private long lastPlayed;
	private String leagueName;
	private int leaguePoints;
	private int losses;
	private MiniSeries miniSeries;
	private String playerOrTeamId;
	private String playerOrTeamName;
	private String queueType;
	private String rank;
	private String tier;
	private long timeUntilDecay;
	private int wins;
	
	LeagueItem(boolean isFreshBlood, boolean isHotStreak, boolean isInactive, boolean isVeteran,
			long lastPlayed, String leagueName, int leaguePoints, int losses, MiniSeries miniSeries,
			String playerOrTeamId, String playerOrTeamName, String queueType, String rank, String tier,
			long timeUntilDecay, int wins){
		this.isFreshBlood = isFreshBlood;
		this.isHotStreak = isHotStreak;
		this.isInactive = isInactive;
		this.isVeteran = isVeteran;
		this.lastPlayed = lastPlayed;
		this.leagueName = leagueName;
		this.leaguePoints = leaguePoints;
		this.losses = losses;
		this.miniSeries = miniSeries;
		this.playerOrTeamId = playerOrTeamId;
		this.playerOrTeamName = playerOrTeamName;
		this.queueType = queueType;
		this.rank = rank;
		this.tier = tier;
		this.timeUntilDecay = timeUntilDecay;
		this.wins = wins;
	}
	
	public boolean isFreshBlood(){
		return isFreshBlood;
	}
	
	public boolean isHotStreak(){
		return isHotStreak;
	}
	
	public boolean isInactive(){
		return isInactive;
	}
	
	public boolean isVeteran(){
		return isVeteran;
	}
	
	public String getPlayerOrTeamId(){
		return playerOrTeamId;
	}
	
	public String getPlayerOrTeamName(){
		return playerOrTeamName;
	}
	
	public long lastPlayed(){
		return lastPlayed;
	}
	
	public String getLeagueName(){
		return leagueName;
	}
	
	public int getLeaguePoints(){
		return leaguePoints;
	}
	
	public int getLosses(){
		return losses;
	}
	
	public MiniSeries getMiniSeries(){
		return miniSeries;
	}
	
	public boolean hasMiniSeries(){
		return miniSeries != null;
	}
	
	public String getQueueType(){
		return queueType;
	}
	
	public String getRank(){
		return rank;
	}
	
	public String getTier(){
		return tier;
	}
	
	public long getTimeUntilDecay(){
		return timeUntilDecay;
	}
	
	public int getWins(){
		return wins;
	}
	
	public String toString(){
		String temp = "";
		if(miniSeries != null){
			temp += ", Mini-Series:" + miniSeries;
		}
		return "[Player/Team ID:" + playerOrTeamId + ", Player/Team Name:" + playerOrTeamName + ", Queue:" + queueType 
				+ ", League Name:" + leagueName + ", Tier:" + tier + ", League Points:" + leaguePoints + ", Rank:" + rank + ", Wins:" 
				+ wins + ", Losses:" + losses + temp + ", Time Until Decay:" + timeUntilDecay + ", Last Played:" + lastPlayed + ", Fresh Blood:" 
				+ isFreshBlood + ", Hot Streak:" + isHotStreak + ", Veteran:" + isVeteran + "]";
	}
	
}
