package com.piyush004.SportsApi.entity;

import java.util.*;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "grounds")
@Data
public class Ground extends CommonFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groundId;

    private String name;

    private String description;

    private Double width;

    private Double length;

    private Double height;

    private Double price; 

    @Column(name = "location_url")
    private String locationUrl;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "GROUND_SPORT_MAPPING", 
        joinColumns = @JoinColumn(name = "groundId"), 
        inverseJoinColumns = @JoinColumn(name = "sportId"))
    private Set<Sport> availableSports = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "GROUND_AMENITY_MAPPING", 
        joinColumns = @JoinColumn(name = "groundId"), 
        inverseJoinColumns = @JoinColumn(name = "amenityId"))
    private Set<Amenity> amenities = new HashSet<>(); 

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "GROUND_AVAILABLETIME_MAPPING", 
        joinColumns = @JoinColumn(name = "groundId"), 
        inverseJoinColumns = @JoinColumn(name = "availableTimeId"))
    private Set<AvailableTime> availableTimes = new HashSet<>();

    @OneToMany(mappedBy = "ground", cascade = CascadeType.ALL)
    private List<GroundImage> images = new ArrayList<>(); 

}
