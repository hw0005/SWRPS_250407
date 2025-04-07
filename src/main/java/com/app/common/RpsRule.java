package com.app.common;

import java.util.HashMap;
import java.util.Map;

public class RpsRule {
	private static Map<RockPaperScissors, GameResult> userRock = new HashMap<>();
	private static Map<RockPaperScissors, GameResult> userpaper = new HashMap<>();
	private static Map<RockPaperScissors, GameResult> userScissors = new HashMap<>();
	public static Map<RockPaperScissors, Map<RockPaperScissors, GameResult>> checkMap = new HashMap<>();

	static {
		userRock.put(RockPaperScissors.PAPER, GameResult.LOST);
		userRock.put(RockPaperScissors.ROCK, GameResult.TIE);
		userRock.put(RockPaperScissors.SCISSORS, GameResult.WON);

		userpaper.put(RockPaperScissors.PAPER, GameResult.TIE);
		userpaper.put(RockPaperScissors.ROCK, GameResult.WON);
		userpaper.put(RockPaperScissors.SCISSORS, GameResult.LOST);

		userScissors.put(RockPaperScissors.PAPER, GameResult.WON);
		userScissors.put(RockPaperScissors.ROCK, GameResult.LOST);
		userScissors.put(RockPaperScissors.SCISSORS, GameResult.TIE);

		checkMap.put(RockPaperScissors.ROCK, userRock);
		checkMap.put(RockPaperScissors.PAPER, userpaper);
		checkMap.put(RockPaperScissors.SCISSORS, userScissors);
	}
}
