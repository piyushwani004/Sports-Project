package com.piyush004.SportsApi.entity;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "grounds")
@Getter
@Setter
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

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "GROUND_USER_MAPPING", joinColumns = @JoinColumn(name = "ground_id", referencedColumnName = "groundId"), inverseJoinColumns = @JoinColumn(name = "id", referencedColumnName = "id"))
	@JsonIgnoreProperties({ "grounds" })
	private Set<User> users = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "GROUND_SPORT_MAPPING", joinColumns = @JoinColumn(name = "ground_id", referencedColumnName = "groundId"), inverseJoinColumns = @JoinColumn(name = "sport_id", referencedColumnName = "sportId"))
	@JsonIgnoreProperties({ "grounds" })
	private Set<Sport> availableSports = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "GROUND_AMENITY_MAPPING", joinColumns = @JoinColumn(name = "ground_id", referencedColumnName = "groundId"), inverseJoinColumns = @JoinColumn(name = "amenity_id", referencedColumnName = "amenityId"))
	@JsonIgnoreProperties({ "grounds" })
	private Set<Amenity> amenities = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "GROUND_AVAILABLETIME_MAPPING", joinColumns = @JoinColumn(name = "ground_id", referencedColumnName = "groundId"), inverseJoinColumns = @JoinColumn(name = "available_time_id", referencedColumnName = "availableTimeId"))
	@JsonIgnoreProperties({ "grounds" })
	private Set<AvailableTime> availableTimes = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "GROUND_GROUNDIMAGE_MAPPING", joinColumns = @JoinColumn(name = "ground_id", referencedColumnName = "groundId"), inverseJoinColumns = @JoinColumn(name = "ground_image_id", referencedColumnName = "groundImageId"))
	@JsonIgnoreProperties({ "grounds" })
	private Set<GroundImage> images = new HashSet<>();

}
