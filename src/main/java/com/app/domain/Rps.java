package com.app.domain;

import com.app.common.RockPaperScissors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
public class Rps {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rsplay_id")
	private long id;
	
	private final RockPaperScissors challenge;
	
	Rps() {
		this(null);
	}
}
