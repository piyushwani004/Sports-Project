package com.piyush004.SportsApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.piyush004.SportsApi.entity.Ground;

public interface GroundRepo extends JpaRepository<Ground, Integer> {

	Optional<Ground> findByNameAndLocationUrl(String name, String locationUrl);
	
	Optional<Ground> findByName(String name);
	
//	@Query("SELECT g FROM Ground g JOIN FETCH g.availableSports JOIN FETCH g.amenities WHERE g.groundId = :id")
//	Optional<Ground> findByIdWithDetails(@Param("id") Integer id);
	
	@EntityGraph(attributePaths = { "users" })
    Optional<Ground> findById(Integer id);
	
	@Query("SELECT g FROM Ground g JOIN FETCH g.users u WHERE g.id = :id")
	Optional<Ground> findGroundWithUsers(@Param("id") Integer id);

}
