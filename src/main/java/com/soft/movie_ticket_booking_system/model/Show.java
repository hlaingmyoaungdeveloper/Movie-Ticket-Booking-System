package com.soft.movie_ticket_booking_system.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shows")
@Getter
@Setter
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "show_id")
    private Integer showId;

    @NotNull(message = "Movie is required")
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @NotNull(message = "Start time is required")
    @Column(name = "start_time")
    private Instant startTime;

    @NotNull(message = "End time is required")
    @Column(name = "end_time")
    private Instant endTime;
}
