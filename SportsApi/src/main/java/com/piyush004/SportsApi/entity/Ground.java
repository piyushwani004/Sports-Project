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

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinTable(name = "GROUND_USER_MAPPING", joinColumns = @JoinColumn(name = "ground_id"), inverseJoinColumns = @JoinColumn(name = "id"))
	@JsonManagedReference
	private Set<User> users = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinTable(name = "GROUND_SPORT_MAPPING", joinColumns = @JoinColumn(name = "ground_id"), inverseJoinColumns = @JoinColumn(name = "sport_id"))
	@JsonManagedReference
	private Set<Sport> availableSports = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinTable(name = "GROUND_AMENITY_MAPPING", joinColumns = @JoinColumn(name = "ground_id"), inverseJoinColumns = @JoinColumn(name = "amenity_id"))
	@JsonManagedReference
	private Set<Amenity> amenities = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinTable(name = "GROUND_AVAILABLETIME_MAPPING", joinColumns = @JoinColumn(name = "ground_id"), inverseJoinColumns = @JoinColumn(name = "available_time_id"))
	@JsonManagedReference
	private Set<AvailableTime> availableTimes = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinTable(name = "GROUND_GROUNDIMAGE_MAPPING", joinColumns = @JoinColumn(name = "ground_id"), inverseJoinColumns = @JoinColumn(name = "ground_image_id"))
	@JsonManagedReference
	private Set<GroundImage> images = new HashSet<>();

}
