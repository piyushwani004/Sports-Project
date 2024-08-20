package com.piyush004.SportsApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piyush004.SportsApi.entity.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {

	Optional<Users> findByEmail(String email);
}
