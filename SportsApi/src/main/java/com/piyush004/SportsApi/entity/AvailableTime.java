package com.piyush004.SportsApi.entity;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "available_time")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailableTime extends CommonFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long availableTimeId;

	private String name;

	private String startTiming;

	private String endTiming;

	@ManyToMany(mappedBy = "availableTimes", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "available_time" })
	private Set<Ground> grounds = new HashSet<>();
}
