package com.soft.movie_ticket_booking_system.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MovieRequest {

    @NotBlank(message = "Movie name is required")
    @Size(max = 255, message = "Movie name must be at most 255 characters")
    private String movieName;

    @NotBlank(message = "Movie description is required")
    private String movieDescription;

    @Min(value = 1, message = "Movie duration must be at least 1 minute")
    @Max(value = 600, message = "Movie duration must be less than or equal to 600 minutes")
    private Integer movieDuration;

    @NotBlank(message = "Movie type is required")
    @Size(max = 100, message = "Movie type must be at most 100 characters")
    private String movieType;

    @NotBlank(message = "Status is required")
    @Size(max = 50, message = "Status must be at most 50 characters")
    private String status;

    private String imageUrl;
}

