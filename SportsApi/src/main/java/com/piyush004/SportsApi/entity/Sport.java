package com.piyush004.SportsApi.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sports")
@Data
public class Sport extends CommonFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sportId;

	@Column(nullable = false, length = 100)
	private String sportName;

	@Column(columnDefinition = "TEXT")
	private String description;

	@ManyToMany(mappedBy = "availableSports")
    private List<Ground> grounds;

//	@OneToMany(mappedBy = "sport", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<Enrollment> enrollments = new HashSet<>();
//
//	@OneToMany(mappedBy = "sport", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<PlayerStats> playerStats = new HashSet<>();
}
