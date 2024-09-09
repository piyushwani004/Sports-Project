package com.piyush004.SportsApi.entity;

import java.util.*;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "available_time")
@Data
public class AvailableTime extends CommonFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long availableTimeId;

    private String name;

    private String startTiming;

    private String endTiming;

    @ManyToMany(mappedBy = "availableTimes")  
    private Set<Ground> grounds = new HashSet<>();  
}
