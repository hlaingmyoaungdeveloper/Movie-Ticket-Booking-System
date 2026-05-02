package com.soft.movie_ticket_booking_system.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_time", updatable = false)
    private Instant createdTime;

    @Column(name = "modified_by")
    private Integer modifiedBy;

    @Column(name = "modified_time")
    private Instant modifiedTime;

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        this.createdTime = now;
        this.modifiedTime = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedTime = Instant.now();
    }
}
