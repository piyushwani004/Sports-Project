package com.piyush004.SportsApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piyush004.SportsApi.entity.Sport;
import com.piyush004.SportsApi.entity.User;

public interface SportRepo extends JpaRepository<Sport, Integer> {
	
	Optional<Sport> findBysportName(String sportName);
}
