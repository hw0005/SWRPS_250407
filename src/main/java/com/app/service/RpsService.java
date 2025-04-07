package com.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.app.common.GameResult;
import com.app.common.RockPaperScissors;
import com.app.common.RpsRule;
import com.app.domain.RpsChallenge;
import com.app.domain.User;
import com.app.event.EventDispatcher;
import com.app.event.RpsSolvedEvent;
import com.app.repository.RpsChallengeRepository;
import com.app.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RpsService {

    private final RpsChallengeRepository rpsChallengeRepository;
	private final RandomGeneratorService randomGeneratorService;
	private final UserRepository userRepository;
	private final EventDispatcher eventDispatcher;

	private RockPaperScissors createRandomRps() {
		return randomGeneratorService.getRockPaperScissors();
	}
	
	private GameResult checkScore(RockPaperScissors userRps, RockPaperScissors computerRps) {
		return RpsRule.checkMap.get(userRps).get(computerRps);
	}
	
	@Transactional
	public Map<String, String> checkChallenge(RpsChallenge rpsChallenge) {
		Map<String, String> map = new HashMap<String, String>();
		Optional<User> user = userRepository.findByAlias(rpsChallenge.getUser().getAlias());
				
		Assert.isNull(rpsChallenge.getGameResult(), "완료된 상태를 보낼 수 없습니다!!!");
		RockPaperScissors computerChoice = createRandomRps();
		GameResult gameResult = checkScore(rpsChallenge.getRps().getChallenge(), computerChoice);
		
		RpsChallenge checkedChallenge = new RpsChallenge(user.orElse(rpsChallenge.getUser()), rpsChallenge.getRps(), computerChoice, gameResult);
		
		rpsChallengeRepository.save(checkedChallenge);
		
		eventDispatcher.send(new RpsSolvedEvent(checkedChallenge.getId(), checkedChallenge.getUser().getId(), checkedChallenge.getUser().getAlias()
				, checkedChallenge.getGameResult().getCommentary()));
		
		
		map.put("opponent", computerChoice.getCommentary());
		map.put("outcome", checkedChallenge.getGameResult().getCommentary());
		map.put("userId", "" + checkedChallenge.getUser().getId());
		return map;
	}
	
	public List<RpsChallenge> getStatsForUser(String userAlias) {
		return rpsChallengeRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
	}
}
