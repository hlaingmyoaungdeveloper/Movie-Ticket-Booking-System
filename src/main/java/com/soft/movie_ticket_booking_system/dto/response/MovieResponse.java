package com.soft.movie_ticket_booking_system.dto.response;

import lombok.Data;

@Data
public class MovieResponse {
    private Integer movieId;
    private String movieName;
    private String movieDescription;
    private Integer movieDuration;
    private String movieType;
    private String status;
    private Boolean deleteFlag = false;
    private String imageUrl;
}
