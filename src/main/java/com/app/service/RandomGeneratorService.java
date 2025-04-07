package com.app.service;

import org.springframework.stereotype.Service;

import com.app.common.RockPaperScissors;

@Service
public class RandomGeneratorService {
	public RockPaperScissors getRockPaperScissors() {
		return RockPaperScissors.randomRps();
	}
}
