package com.soft.movie_ticket_booking_system.dto.response;

import java.time.Instant;

import lombok.Data;

@Data
public class ShowResponse {
    private Integer showId;
    private MovieResponse movie;
    private Instant startTime;
    private Instant endTime;
}
