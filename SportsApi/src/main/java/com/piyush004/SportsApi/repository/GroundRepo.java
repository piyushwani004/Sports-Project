package com.piyush004.SportsApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piyush004.SportsApi.entity.Ground;
import com.piyush004.SportsApi.entity.User;

public interface GroundRepo extends JpaRepository<Ground, Integer> {

	Optional<Ground> findByNameAndLocationUrl(String name, String locationUrl);

}
