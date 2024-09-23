package com.piyush004.SportsApi.entity;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grounds")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ground extends CommonFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer groundId;

	private String name;

	private String description;

	private Double width;

	private Double length;

	private Double height;

	private String location;

	@Column(name = "location_url")
	private String locationUrl;

	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

	@ManyToMany
    @JoinTable(
        name = "ground_sport",
        joinColumns = @JoinColumn(name = "ground_id"),
        inverseJoinColumns = @JoinColumn(name = "sport_id")
    )
	private Set<Sport> sports = new HashSet<>();

	@ManyToMany
    @JoinTable(
        name = "ground_amenity",
        joinColumns = @JoinColumn(name = "ground_id"),
        inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
	private Set<Amenity> amenities = new HashSet<>();

	@ManyToMany
    @JoinTable(
        name = "ground_available_time",
        joinColumns = @JoinColumn(name = "ground_id"),
        inverseJoinColumns = @JoinColumn(name = "available_time_id")
    )
	private Set<AvailableTime> availableTimes = new HashSet<>();

	@OneToMany(mappedBy = "ground", cascade = CascadeType.ALL)
	private Set<GroundImage> images = new HashSet<>();

}
