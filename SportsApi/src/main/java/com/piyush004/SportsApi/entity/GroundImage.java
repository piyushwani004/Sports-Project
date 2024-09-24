//package com.piyush004.SportsApi.entity;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "ground_image")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class GroundImage extends CommonFields {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long groundImageId;
//
//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
//			CascadeType.REFRESH })
//	@JoinTable(name = "GROUND_GROUNDIMAGE_MAPPING", joinColumns = @JoinColumn(name = "ground_image_id"), inverseJoinColumns = @JoinColumn(name = "ground_id"))
//	@JsonIgnore
//	private Set<Ground> grounds = new HashSet<>();
//
//	private Boolean isUrl = false;
//
//	@Column(name = "image_url")
//	private String imageUrl;
//
//	@Lob
//	@Column(name = "image", nullable = true)
//	private byte[] image;
//
//	public boolean hasValidImage() {
//		return (isUrl != null && isUrl && imageUrl != null && !imageUrl.isEmpty())
//				|| (isUrl != null && !isUrl && image != null && image.length > 0);
//	}
//}
