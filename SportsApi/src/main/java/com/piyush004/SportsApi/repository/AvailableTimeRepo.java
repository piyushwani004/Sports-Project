package com.piyush004.SportsApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piyush004.SportsApi.entity.AvailableTime;

public interface AvailableTimeRepo extends JpaRepository<AvailableTime, Long> {

}
