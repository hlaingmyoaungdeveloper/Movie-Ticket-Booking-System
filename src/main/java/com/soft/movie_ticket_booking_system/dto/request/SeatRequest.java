package com.soft.movie_ticket_booking_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SeatRequest {

    @NotBlank(message = "Seat code is required")
    @Size(max = 50, message = "Seat code must be at most 50 characters")
    private String seatCode;

    @NotNull(message = "Seat type ID is required")
    private Integer seatTypeId;

    @NotBlank(message = "Status is required")
    @Size(max = 50, message = "Status must be at most 50 characters")
    private String status;
}
