package com.piyush004.SportsApi.entity;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "amenities")
public class Amenity extends CommonFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long amenityId;

    private String name;
    
    private String description;

    @ManyToMany(mappedBy = "amenities", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "amenities" })
    private Set<Ground> grounds = new HashSet<>();
}
