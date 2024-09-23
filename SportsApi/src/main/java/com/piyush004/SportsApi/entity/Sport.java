package com.piyush004.SportsApi.entity;

import java.math.BigDecimal;
import java.util.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sport extends CommonFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sportId;

	@Column(nullable = false, length = 100)
	private String sportName;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal price;

	@ManyToMany(mappedBy = "sports")
	private Set<Ground> grounds = new HashSet<>();

//	@OneToMany(mappedBy = "sport", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<Enrollment> enrollments = new HashSet<>();
//
//	@OneToMany(mappedBy = "sport", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<PlayerStats> playerStats = new HashSet<>();
}
