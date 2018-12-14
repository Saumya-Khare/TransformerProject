package com.sk.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sk.transformer.dto.Transformer;
import com.sk.transformer.dto.TransformerFightResult;
import com.sk.transformer.dto.TransformerResponse;
import com.sk.transformer.util.TransformerService;

public class TranformerTest {
	TransformerService service = new TransformerService();
	List<Transformer> expectedListOfTransformers = new ArrayList<Transformer>();
	Transformer inputTransformer1;
	Transformer inputTransformer2;

	@Before
	public void setup() {
		inputTransformer1 = createMockTransformer1();
		inputTransformer2 = createMockTransformer2();
	}

	@Test
	public void testCreateTransformerService1() {
		TransformerResponse expectedResponse=new TransformerResponse();
		TransformerResponse actualResponse = service.create(inputTransformer1);
		expectedResponse.setMessage("Success");
		Assert.assertEquals(expectedResponse.toString(), actualResponse.toString());
	}

	@Test
	public void testCreateTransformerService2() {
		TransformerResponse expectedResponse=new TransformerResponse();
		TransformerResponse actualResponse = service.create(inputTransformer2);
		expectedResponse.setMessage("Success");
		Assert.assertEquals(expectedResponse.toString(), actualResponse.toString());
	}

	@Test()
	public void testListAllTransformerService() {
		service.create(inputTransformer1);
		service.create(inputTransformer2);
		List<Transformer> actualListOfTRansformers = service.listAll();
		Assert.assertEquals(expectedListOfTransformers, actualListOfTRansformers);
	}

	@Test()
	public void testUpdateTransformerService() {
		TransformerResponse expectedResponse=new TransformerResponse();
		service.create(inputTransformer1);
		Transformer inputTransformerToUpdate = createMockTransformerToUpdate();
		TransformerResponse actualResponse = service.update(inputTransformerToUpdate);
		expectedResponse.setMessage("Success");
		Assert.assertEquals(expectedResponse.toString(), actualResponse.toString());
	}

	@Test()
	public void testDeleteTransformerService() {
		TransformerResponse expectedResponse=new TransformerResponse();
		service.create(inputTransformer1);
		service.create(inputTransformer2);
		TransformerResponse actualResponse = service.delete("Soundwave");
		expectedResponse.setMessage("Success");
		Assert.assertEquals(expectedResponse.toString(), actualResponse.toString());
	}

	@Test()
	public void testGetResultService_OneFighterHasSkillEqualOrMoreThan3() {
		List<Transformer> fighters = getListOfTransformerForSkillTest();
		TransformerFightResult actualResult = service.getWinner(fighters);
		TransformerFightResult expectedResult = getExpecteResponseForFightBySkill();
		Assert.assertEquals(expectedResult.toString(), actualResult.toString());
	}

	@Test()
	public void testGetResultService_OneFighterHasCourageEqualOrMoreThan4() {
		List<Transformer> fighters = getListOfTransformerForCourageTest();
		TransformerFightResult actualResult = service.getWinner(fighters);
		TransformerFightResult expectedResult = getExpecteResponseForFightByCourage();
		Assert.assertEquals(expectedResult.toString(), actualResult.toString());
	}

	@Test()
	public void testGetResultService_OneFighterHasStrengthEqualOrMoreThan3() {
		List<Transformer> fighters = getListOfTransformerForStrengthTest();
		TransformerFightResult actualResult = service.getWinner(fighters);
		TransformerFightResult expectedResult = getExpecteResponseForFightByStrength();
		Assert.assertEquals(expectedResult.toString(), actualResult.toString());
	}

	@Test()
	public void testGetResultService_ByOverallRating() {
		List<Transformer> fighters = getListOfTransformerByOverallRating();
		TransformerFightResult actualResult = service.getWinner(fighters);
		TransformerFightResult expectedResult = getExpecteResponseForFightByOverallRating();
		Assert.assertEquals(expectedResult.toString(), actualResult.toString());
	}

	@Test()
	public void testGetResultService_ByOverallRating_MoreThanOneBattle() {
		List<Transformer> fighters = getListOfTransformerByOverallRating_MoreThanOneBattle();
		TransformerFightResult actualResult = service.getWinner(fighters);
		TransformerFightResult expectedResult = getExpecteResponseForFightByOverallRating_MoreThanOneBattle();
		Assert.assertEquals(expectedResult.toString(), actualResult.toString());
	}

	@Test()
	public void testGetResultService_BySpecialFighter() {
		List<Transformer> fighters = getListOfTransformerBySpecialFighter();
		TransformerFightResult actualResult = service.getWinner(fighters);
		TransformerFightResult expectedResult = getExpecteResponseForFightBySpecialFighter();
		Assert.assertEquals(expectedResult.toString(), actualResult.toString());
	}

	@Test()
	public void testGetResultService_BySpecialFighterFacingEachOther() {
		List<Transformer> fighters = getListOfTransformerBySpecialFighterFacingEachOther();
		TransformerFightResult actualResult = service.getWinner(fighters);
		TransformerFightResult expectedResult = getExpecteResponseForFightBySpecialFighterFacingEachOther();
		Assert.assertEquals(expectedResult.toString(), actualResult.toString());
	}

	private Transformer createMockTransformer2() {
		Transformer transformer = createMockTransformer("Soundwave", "D", 3, 2, 3, 5, 4, 6, 7, 6);
		expectedListOfTransformers.add(transformer);
		return transformer;
	}

	private Transformer createMockTransformer1() {
		Transformer transformer = createMockTransformer("Bluestreak", "A", 3, 2, 3, 5, 5, 7, 4, 4);
		expectedListOfTransformers.add(transformer);
		return transformer;
	}

	private Transformer createMockTransformer3() {
		Transformer transformer = createMockTransformer("Hubcap", "A", 3, 2, 3, 5, 3, 6, 5, 5);
		expectedListOfTransformers.add(transformer);
		return transformer;
	}

	private Transformer createMockTransformer5() {
		Transformer transformer = createMockTransformer("Boltron", "D", 1, 4, 5, 4, 3, 6, 5, 5);
		expectedListOfTransformers.add(transformer);
		return transformer;
	}

	private Transformer createMockTransformer4() {
		Transformer transformer = createMockTransformer("Supernova", "D", 3, 2, 3, 5, 2, 5, 4, 4);
		expectedListOfTransformers.add(transformer);
		return transformer;
	}

	private Transformer createMockTransformerToUpdate() {
		Transformer transformer = createMockTransformer("Bluestreak", "D", 4, 4, 4, 4, 5, 5, 6, 6);
		expectedListOfTransformers.add(transformer);
		return transformer;
	}
	
	private Transformer createMockTransformer(String teamName, String teamType, int strength, int intel, int speed,
			int end, int rank, int courage, int fire, int skill) {
		Transformer transformer = new Transformer();
		transformer.setTeamName(teamName);
		transformer.setType(teamType);
		transformer.setStrength(strength);
		transformer.setIntelligence(intel);
		transformer.setSpeed(speed);
		transformer.setEndurance(end);
		transformer.setRank(rank);
		transformer.setCourage(courage);
		transformer.setFirepower(fire);
		transformer.setSkill(skill);
		return transformer;

	}

	private List<Transformer> getListOfTransformerForSkillTest() {
		List<Transformer> listOfFighters = new ArrayList<Transformer>();
		Transformer fighter1 = createMockTransformer1();
		Transformer fighter2 = createMockTransformer2();
		fighter2.setSkill(8);
		Transformer fighter3 = createMockTransformer3();
		listOfFighters.add(fighter1);
		listOfFighters.add(fighter2);
		listOfFighters.add(fighter3);
		return listOfFighters;
	}

	private List<Transformer> getListOfTransformerForCourageTest() {
		List<Transformer> listOfFighters = new ArrayList<Transformer>();
		Transformer fighter1 = createMockTransformer1();
		fighter1.setSkill(7);
		fighter1.setCourage(10);
		Transformer fighter2 = createMockTransformer2();
		Transformer fighter3 = createMockTransformer3();
		listOfFighters.add(fighter1);
		listOfFighters.add(fighter2);
		listOfFighters.add(fighter3);
		return listOfFighters;
	}

	private List<Transformer> getListOfTransformerForStrengthTest() {
		List<Transformer> listOfFighters = new ArrayList<Transformer>();
		Transformer fighter1 = createMockTransformer1();
		fighter1.setSkill(7);
		Transformer fighter2 = createMockTransformer2();
		Transformer fighter3 = createMockTransformer3();
		fighter3.setRank(7);
		fighter3.setSkill(6);
		fighter3.setStrength(6);
		listOfFighters.add(fighter1);
		listOfFighters.add(fighter2);
		listOfFighters.add(fighter3);
		return listOfFighters;
	}

	private List<Transformer> getListOfTransformerByOverallRating() {
		List<Transformer> listOfFighters = new ArrayList<Transformer>();
		Transformer fighter1 = createMockTransformer1();
		Transformer fighter2 = createMockTransformer2();
		Transformer fighter3 = createMockTransformer3();
		listOfFighters.add(fighter1);
		listOfFighters.add(fighter2);
		listOfFighters.add(fighter3);
		return listOfFighters;
	}

	private List<Transformer> getListOfTransformerByOverallRating_MoreThanOneBattle() {
		List<Transformer> listOfFighters = new ArrayList<Transformer>();
		Transformer fighter1 = createMockTransformer1();
		Transformer fighter2 = createMockTransformer2();
		Transformer fighter3 = createMockTransformer3();
		Transformer fighter4 = createMockTransformer4();
		Transformer fighter5 = createMockTransformer5();
		listOfFighters.add(fighter1);
		listOfFighters.add(fighter2);
		listOfFighters.add(fighter3);
		listOfFighters.add(fighter4);
		listOfFighters.add(fighter5);
		return listOfFighters;
	}

	private List<Transformer> getListOfTransformerBySpecialFighter() {
		List<Transformer> listOfFighters = new ArrayList<Transformer>();
		Transformer fighter1 = createMockTransformer1();
		Transformer fighter2 = createMockTransformer2();
		Transformer specialFighter = createMockTransformer("Optimus Prime", "D", 3, 4, 3, 5, 6, 6, 7, 6);
		listOfFighters.add(fighter1);
		listOfFighters.add(fighter2);
		listOfFighters.add(specialFighter);
		return listOfFighters;

	}
	

	private List<Transformer> getListOfTransformerBySpecialFighterFacingEachOther() {
		List<Transformer> listOfFighters = new ArrayList<Transformer>();
		Transformer fighter1 = createMockTransformer1();
		Transformer fighter2 = createMockTransformer2();
		Transformer specialFighterD = createMockTransformer("Optimus Prime", "D", 3, 4, 3, 5, 6, 6, 7, 6);
		Transformer specialFighterA = createMockTransformer("Predaking", "A", 3, 4, 3, 5, 7, 6, 7, 6);
		listOfFighters.add(fighter1);
		listOfFighters.add(fighter2);
		listOfFighters.add(specialFighterD);
		listOfFighters.add(specialFighterA);
		return listOfFighters;
	}

	private TransformerFightResult getExpecteResponseForFightBySkill() {
		TransformerFightResult result = getExpectedResponse(1, "Soundwave", "Decepticons", Arrays.asList("Hubcap"),
				"Autobots", "Fight Completed");
		return result;
	}

	private TransformerFightResult getExpecteResponseForFightByCourage() {
		TransformerFightResult result = getExpectedResponse(1, "Bluestreak", "Autobots", Arrays.asList(),
				"Decepticons", "Fight Completed");
		return result;
	}

	private TransformerFightResult getExpecteResponseForFightByStrength() {
		TransformerFightResult result = getExpectedResponse(1, "Hubcap", "Autobots", Arrays.asList(),
				"Decepticons", "Fight Completed");
		return result;
	}

	private TransformerFightResult getExpecteResponseForFightByOverallRating() {
		TransformerFightResult result = getExpectedResponse(1, "Soundwave", "Decepticons", Arrays.asList("Hubcap"),
				"Autobots", "Fight Completed");
		return result;
	}

	private TransformerFightResult getExpecteResponseForFightByOverallRating_MoreThanOneBattle() {
		TransformerFightResult result = getExpectedResponse(2, "Soundwave", "Decepticons", Arrays.asList(), "Autobots",
				"Fight Completed");
		return result;
	}


	private TransformerFightResult getExpecteResponseForFightBySpecialFighter() {
		TransformerFightResult result = getExpectedResponse(1, "Optimus Prime", "Decepticons", Arrays.asList(), "Autobots",
				"Fight Completed");
		return result;
	}
	

	private TransformerFightResult getExpecteResponseForFightBySpecialFighterFacingEachOther() {
		TransformerFightResult result = getExpectedResponse(2, null, null, null, null,
				"All fighters destroyed as special fighters faced each other");
		return result;
	}

	private TransformerFightResult getExpectedResponse(int battle, String winnerName, String winnerTeam,
			List<String> survivors, String losingTeam, String msg) {
		TransformerFightResult result = new TransformerFightResult();
		result.setNoOfBattle(battle);
		result.setWinnerName(winnerName);
		result.setWinningTeam(winnerTeam);
		result.setSurvivors(survivors);
		result.setLosingTeam(losingTeam);
		result.setMessage(msg);
		return result;
	}


}
