package com.piyush004.SportsApi.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ground_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroundImage extends CommonFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long groundImageId;

	@ManyToMany(mappedBy = "images", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "ground_image" })
	private Set<Ground> grounds = new HashSet<>();

	private Boolean isUrl = false;

	@Column(name = "image_url")
	private String imageUrl;

	@Lob
	@Column(name = "image", nullable = true)
	private byte[] image;

	public boolean hasValidImage() {
		return (isUrl != null && isUrl && imageUrl != null && !imageUrl.isEmpty())
				|| (isUrl != null && !isUrl && image != null && image.length > 0);
	}
}
