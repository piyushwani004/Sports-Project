package com.piyush004.SportsApi.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Sports")
@Data
public class Sport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sportId;

	@Column(nullable = false, length = 100)
	private String sportName;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(nullable = false)
	private boolean isDisable = false;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(nullable = false)
	private LocalDateTime updatedAt = LocalDateTime.now();

	@OneToMany(mappedBy = "sport", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Enrollment> enrollments = new HashSet<>();

	@OneToMany(mappedBy = "sport", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PlayerStats> playerStats = new HashSet<>();
}
