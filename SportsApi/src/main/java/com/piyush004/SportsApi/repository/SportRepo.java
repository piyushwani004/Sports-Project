package com.piyush004.SportsApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piyush004.SportsApi.entity.Sport;

public interface SportRepo extends JpaRepository<Sport, Long> {
	
	Optional<Sport> findBysportName(String sportName);
}
