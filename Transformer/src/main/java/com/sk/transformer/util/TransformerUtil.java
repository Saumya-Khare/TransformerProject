package com.sk.transformer.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sk.transformer.constants.TransformerConstants;
import com.sk.transformer.dto.Transformer;
import com.sk.transformer.dto.TransformerFightResult;

public class TransformerUtil {

	List<Transformer> decepticons = new ArrayList<Transformer>();
	List<Transformer> autobots = new ArrayList<Transformer>();
	List<Transformer> init_decepticons = new ArrayList<Transformer>();
	List<Transformer> init_autobots = new ArrayList<Transformer>();
	TransformerFightResult result = new TransformerFightResult();
	Map<String, Integer> resultMap = new HashMap<String, Integer>();
	String winnerTeam = "";
	boolean foundWinner;
	boolean isSpecialFightersFacedEachOther;
	int decepticonsCount = 0;
	int autobotsCount = 0;

	public TransformerFightResult getResult(List<Transformer> transformersList) {
		createTeams(transformersList);
		SortTeamsBasedOnRanks();
		int noOfBattale = getNumberOfBattles();
		startFight(noOfBattale);
		getWinner();
		return result;
	}

	private void SortTeamsBasedOnRanks() {
		Collections.sort(decepticons,
				(t1, t2) -> ((t1.getRank() < t2.getRank()) ? 1 : (t1.getRank() > t2.getRank()) ? -1 : 0));
		Collections.sort(autobots,
				(t1, t2) -> ((t1.getRank() < t2.getRank()) ? 1 : (t1.getRank() > t2.getRank()) ? -1 : 0));
		init_decepticons.addAll(decepticons);
		init_autobots.addAll(autobots);
	}

	private int getNumberOfBattles() {
		int battle = 0;
		if (autobots.size() < decepticons.size()) {
			battle = autobots.size();
		} else if (autobots.size() > decepticons.size()) {
			battle = decepticons.size();
		} else {
			battle = autobots.size();
		}
		result.setNoOfBattle(battle);
		return battle;
	}

	private void startFight(int noOfBattale) {
		for (int i = 0; i < noOfBattale; i++) {
			if (!isSpecialFightersFacedEachOther) {
				setFoundWinner(false);
				fight(init_decepticons.get(i), init_autobots.get(i));
			}
		}
	}

	private void fight(Transformer decepticon, Transformer autobot) {
		checkIfOptimusPtimeOrPredaking(decepticon, autobot);
		if (!isSpecialFightersFacedEachOther) {
			checkForFightersSkill(decepticon, autobot);
			if (getWinnerTeam().isEmpty()) {
				checkForOpponentsCourage(decepticon, autobot);
				if (!foundWinner) {
					checkForOpponetsStrength(decepticon, autobot);
				}
				if (!foundWinner) {
					checkForOverallRating(decepticon, autobot);
				}
			}
		}
	}

	private void checkForOpponetsStrength(Transformer decepticon, Transformer autobot) {
		if ((decepticon.getStrength() - autobot.getStrength()) >= TransformerConstants.STRENGTH) {
			setDecepticonAsWinner(decepticon, autobot);
		} else if ((autobot.getStrength() - decepticon.getStrength()) >= TransformerConstants.STRENGTH) {
			setAutobotFighterAsWinner(decepticon, autobot);
		}
	}

	private void checkForOpponentsCourage(Transformer decepticon, Transformer autobot) {
		if ((decepticon.getCourage() - autobot.getCourage()) >= TransformerConstants.COURAGE) {
			setDecepticonAsWinner(decepticon, autobot);
		} else if ((autobot.getCourage() - decepticon.getCourage()) >= TransformerConstants.COURAGE) {
			setAutobotFighterAsWinner(decepticon, autobot);
		}
	}

	private void checkForOverallRating(Transformer decepticon, Transformer autobot) {
		if (decepticon.getOverAllRating() > autobot.getOverAllRating()) {
			setDecepticonAsWinner(decepticon, autobot);
		} else if (decepticon.getOverAllRating() < autobot.getOverAllRating()) {
			setAutobotFighterAsWinner(decepticon, autobot);
		} else {
			decepticons.remove(decepticon);
			autobots.remove(autobot);
			setFoundWinner(true);
		}
	}

	private void checkForFightersSkill(Transformer decepticon, Transformer autobot) {
		if ((decepticon.getSkill() - autobot.getSkill()) >= TransformerConstants.SKILL) {
			setWinnerTeam(TransformerConstants.DECEPTICONS);
			autobots.remove(autobot);
			setFoundWinner(true);
		} else if ((autobot.getSkill() - decepticon.getSkill()) >= TransformerConstants.SKILL) {
			setWinnerTeam(TransformerConstants.AUTOBOTS);
			decepticons.remove(decepticon);
			setFoundWinner(true);
		}
	}

	private void checkIfOptimusPtimeOrPredaking(Transformer decepticon, Transformer autobot) {
		if (isSpecialFighter(decepticon)) {
			if (isSpecialFighter(autobot)) {
				setSpecialFightersFacedEachOther(true);
			} else {
				setDecepticonAsWinner(decepticon, autobot);
			}
		} else if (isSpecialFighter(autobot)) {
			if (isSpecialFighter(decepticon)) {
				setSpecialFightersFacedEachOther(true);
			} else {
				setAutobotFighterAsWinner(decepticon, autobot);
			}
		}
	}

	private void setDecepticonAsWinner(Transformer decepticon, Transformer autobot) {
		autobots.remove(autobot);
		decepticon.setWinner(true);
		setFoundWinner(true);
		resultMap.put(TransformerConstants.DECEPTICONS, ++decepticonsCount);
	}

	private void setAutobotFighterAsWinner(Transformer decepticon, Transformer autobot) {
		decepticons.remove(decepticon);
		autobot.setWinner(true);
		setFoundWinner(true);
		resultMap.put(TransformerConstants.AUTOBOTS, ++autobotsCount);
	}

	private boolean isSpecialFighter(Transformer fighter) {
		return TransformerConstants.OPTIMUS_PRIME.equalsIgnoreCase(fighter.getTeamName())
				|| TransformerConstants.PREDAKING.equalsIgnoreCase(fighter.getTeamName());
	}

	private void getWinner() {
		if (isSpecialFightersFacedEachOther) {
			result.setMessage("All fighters destroyed as special fighters faced each other");
		} else if (!getWinnerTeam().isEmpty()) {
			findWinnerFromWinningTeam();
		} else {
			processResultMapToFindWinner();
		}
	}

	private void processResultMapToFindWinner() {
		int decepticons_count = 0;
		int autobots_count = 0;
		if (resultMap.get(TransformerConstants.DECEPTICONS) != null) {
			decepticons_count = resultMap.get(TransformerConstants.DECEPTICONS);
		}
		if (resultMap.get(TransformerConstants.AUTOBOTS) != null) {
			autobots_count = resultMap.get(TransformerConstants.AUTOBOTS);
		}
		if (decepticons_count > autobots_count) {
			getWinner(decepticons);
			getSurvivors(autobots, TransformerConstants.TEAM_AUTOBOTS);
		} else if (autobots_count > decepticons_count) {
			getWinner(autobots);
			getSurvivors(decepticons, TransformerConstants.TEAM_DECEPTICONS);
		} else {
			getWinner(decepticons, autobots);
		}
	}

	private void findWinnerFromWinningTeam() {
		if (TransformerConstants.DECEPTICONS.equalsIgnoreCase(getWinnerTeam())) {
			setWinnerToResult(decepticons);
			getSurvivors(autobots, TransformerConstants.TEAM_AUTOBOTS);

		} else if (TransformerConstants.AUTOBOTS.equalsIgnoreCase(getWinnerTeam())) {
			setWinnerToResult(autobots);
			getSurvivors(decepticons, TransformerConstants.TEAM_DECEPTICONS);
		}
	}

	private void getWinner(List<Transformer> decepticons, List<Transformer> autobots) {
		sortTransformersBasedOnOverallRating(decepticons);
		sortTransformersBasedOnOverallRating(autobots);
		if (decepticons.get(0).getOverAllRating() > autobots.get(0).getOverAllRating()) {
			setWinnerToResult(decepticons);
			getSurvivors(autobots, TransformerConstants.TEAM_AUTOBOTS);
		} else if (autobots.get(0).getOverAllRating() > decepticons.get(0).getOverAllRating()) {
			setWinnerToResult(autobots);
			getSurvivors(decepticons, TransformerConstants.TEAM_DECEPTICONS);
		}
	}

	private void getWinner(List<Transformer> transformers) {
		List<Transformer> winnersList = getListOfWinners(transformers);
		setWinnerToResult(winnersList);
	}

	private List<Transformer> getListOfWinners(List<Transformer> transformers) {
		List<Transformer> winnersList = new ArrayList<Transformer>();
		for (Transformer transformer : transformers) {
			if (transformer.isWinner()) {
				winnersList.add(transformer);
			}
		}
		return winnersList;
	}

	private void setWinnerToResult(List<Transformer> transformers) {
		sortTransformersBasedOnOverallRating(transformers);
		result.setWinnerName(transformers.get(0).getTeamName());
		result.setWinningTeam(transformers.get(0).getTeamType());
		result.setMessage(TransformerConstants.SUCCESS);
	}

	private void sortTransformersBasedOnOverallRating(List<Transformer> transformers) {
		Collections.sort(transformers, (t1, t2) -> (t1.getOverAllRating() > t2.getOverAllRating()) ? -1
				: (t1.getOverAllRating() < t2.getOverAllRating()) ? 1 : 0);
	}

	private void getSurvivors(List<Transformer> transformers, String teamType) {
		List<String> survivors = new ArrayList<String>();
		for (Transformer survivor : transformers) {
			survivors.add(survivor.getTeamName());
		}
		result.setSurvivors(survivors);
		result.setLosingTeam(teamType);
	}

	private void createTeams(List<Transformer> transformers) {
		for (Transformer transformer : transformers) {
			if (TransformerConstants.DECEPTICONS.equalsIgnoreCase(transformer.getType())) {
				decepticons.add(transformer);
			} else if (TransformerConstants.AUTOBOTS.equalsIgnoreCase(transformer.getType())) {
				autobots.add(transformer);
			}
		}
	}

	public String getWinnerTeam() {
		return winnerTeam;
	}

	public void setWinnerTeam(String winnerTeam) {
		this.winnerTeam = winnerTeam;
	}

	public boolean isFoundWinner() {
		return foundWinner;
	}

	public void setFoundWinner(boolean foundWinner) {
		this.foundWinner = foundWinner;
	}

	public boolean isSpecialFightersFacedEachOther() {
		return isSpecialFightersFacedEachOther;
	}

	public void setSpecialFightersFacedEachOther(boolean isSpecialFightersFacedEachOther) {
		this.isSpecialFightersFacedEachOther = isSpecialFightersFacedEachOther;
	}

}
