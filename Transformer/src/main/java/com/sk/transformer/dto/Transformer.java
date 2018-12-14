package com.sk.transformer.dto;

public class Transformer {
	String teamName;
	String type;
	String teamType;
	int strength;
	int intelligence;
	int speed;
	int endurance;
	int rank;
	int courage;
	int firepower;
	int skill;
	boolean winner;
	int overAllRating;

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTeamType() {
		if (type.equalsIgnoreCase("D")) {
			return "Decepticons";
		} else {
			return "Autobots";
		}
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getEndurance() {
		return endurance;
	}

	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}

	public int getCourage() {
		return courage;
	}

	public void setCourage(int courage) {
		this.courage = courage;
	}

	public int getFirepower() {
		return firepower;
	}

	public void setFirepower(int firepower) {
		this.firepower = firepower;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public int getOverAllRating() {
		return this.getStrength() + this.getIntelligence() + this.getSpeed() + this.getEndurance()
		+ this.getFirepower();
	}

	@Override
	public String toString() {
		return "Transformer [Team Name="+teamName+", Type=" + type + ", Strength=" + strength + ", Intelligence="
				+ intelligence + ", Speed=" + speed + ", Endurance=" + endurance + ", rank=" + rank + ", Courage="
				+ courage + ", Firepower=" + firepower + ", Skill=" + skill + ", Overall Rating=" + getOverAllRating() + "]";
	}
}
