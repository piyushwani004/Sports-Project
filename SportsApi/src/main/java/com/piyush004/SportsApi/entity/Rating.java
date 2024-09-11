package com.piyush004.SportsApi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ratings")
@Data
public class Rating extends CommonFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    @ManyToOne
    @JoinColumn(name = "groundId")
    @JsonBackReference
    private Ground ground;

    private Long userId;

    @Column(nullable = false)
    private int starsCount;

    private String review;
}
