package com.piyush004.SportsApi.entity;

import java.util.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "amenities")
public class Amenity extends CommonFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long amenityId;

    private String name;

    @ManyToMany(mappedBy = "amenities")
    private Set<Ground> grounds = new HashSet<>();
}
