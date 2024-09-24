//package com.piyush004.SportsApi.entity;
//
//import java.util.*;
//
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "available_time")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class AvailableTime extends CommonFields {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long availableTimeId;
//
//	private String name;
//
//	private String startTiming;
//
//	private String endTiming;
//
//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
//			CascadeType.REFRESH })
//	@JoinTable(name = "GROUND_AVAILABLETIME_MAPPING", joinColumns = @JoinColumn(name = "available_time_id"), inverseJoinColumns = @JoinColumn(name = "ground_id"))
//	@JsonIgnore
//	private Set<Ground> grounds = new HashSet<>();
//}
