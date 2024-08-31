package com.piyush004.SportsApi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PlayerStats", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "sport_id" }))
@Data
public class PlayerStats {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long statsId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sport_id", nullable = false)
	private Sport sport;

	private int matchesPlayed = 0;
	private int wins = 0;
	private int losses = 0;
	private int pointsScored = 0;

	@Column(nullable = false)
	private boolean isDisable = false;

	@Column(nullable = false)
	private LocalDateTime updatedAt = LocalDateTime.now();
}
