package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.domain.Rps;

public interface RpsRepository extends JpaRepository<Rps, Long>{
	
}
