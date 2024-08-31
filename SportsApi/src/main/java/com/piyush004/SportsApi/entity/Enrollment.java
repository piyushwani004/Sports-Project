package com.piyush004.SportsApi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Enrollments", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "sport_id" }))
@Data
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long enrollmentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sport_id", nullable = false)
	private Sport sport;

	@Column(nullable = false)
	private boolean isDisable = false;

	@Column(nullable = false)
	private LocalDateTime enrollmentDate = LocalDateTime.now();
}
