package com.piyush004.SportsApi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.piyush004.SportsApi.entity.Role;
import com.piyush004.SportsApi.entity.User;

public interface PlayerRepo extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.role = :role")
	Optional<List<User>> getAllPlayers(@Param("role") Role role);
}
