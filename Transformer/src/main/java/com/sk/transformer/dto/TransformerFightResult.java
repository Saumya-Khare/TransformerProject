package com.sk.transformer.dto;

import java.util.List;

public class TransformerFightResult {
	int noOfBattle;
	String WinnerName;
	List<String> Survivors;
	String message;
	String winningTeam;
	String losingTeam;

	public String getWinningTeam() {
		return winningTeam;
	}

	public void setWinningTeam(String winningTeam) {
		this.winningTeam = winningTeam;
	}

	public String getLosingTeam() {
		return losingTeam;
	}
	
	public void setLosingTeam(String losingTeam) {
		this.losingTeam = losingTeam;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getSurvivors() {
		return Survivors;
	}

	public void setSurvivors(List<String> survivors) {
		Survivors = survivors;
	}

	public int getNoOfBattle() {
		return noOfBattle;
	}

	public void setNoOfBattle(int noOfBattle) {
		this.noOfBattle = noOfBattle;
	}

	public String getWinnerName() {
		return WinnerName;
	}

	public void setWinnerName(String winnerName) {
		WinnerName = winnerName;
	}

	@Override
	public String toString() {
		return "TransformerResult [noOfBattle=" + noOfBattle + ", WinnerName=" + WinnerName + ", Survivors=" + Survivors
				+ ", message=" + message + ", winningTeam=" + winningTeam + ", losingTeam=" + losingTeam + "]";
	}
	

	
}
