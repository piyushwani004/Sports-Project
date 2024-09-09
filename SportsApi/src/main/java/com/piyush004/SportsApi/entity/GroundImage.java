package com.piyush004.SportsApi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ground_image")
@Data
public class GroundImage extends CommonFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groundImageId;

    @ManyToOne
    @JoinColumn(name = "groundId")
    private Ground ground;
    
    private Boolean isUrl = false;
    
    @Column(name = "image_url")
    private String imageUrl;

    @Lob
    @Column(name = "image", nullable = true)
    private byte[] image;
    
    
    public boolean hasValidImage() {
        return (isUrl != null && isUrl && imageUrl != null && !imageUrl.isEmpty()) ||
               (isUrl != null && !isUrl && image != null && image.length > 0);
    }
}
