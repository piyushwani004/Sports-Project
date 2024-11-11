package com.piyush004.SportsApi.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
public class CommonFields {

    @Column(nullable = false)
    private boolean isDisable = false;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
