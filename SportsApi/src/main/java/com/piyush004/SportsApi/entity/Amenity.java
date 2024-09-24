//package com.piyush004.SportsApi.entity;
//
//import java.util.*;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "amenities")
//public class Amenity extends CommonFields {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long amenityId;
//
//    private String name;
//    
//    private String description;
//
//    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
//			CascadeType.REFRESH })
//	@JoinTable(name = "GROUND_AMENITY_MAPPING", joinColumns = @JoinColumn(name = "amenity_id"), inverseJoinColumns = @JoinColumn(name = "ground_id"))
//    @JsonIgnore
//    private Set<Ground> grounds = new HashSet<>();
//}
