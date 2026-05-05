package com.soft.movie_ticket_booking_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SeatRequest {

    @NotBlank(message = "Seat code is required")
    private String seatCode;

    @NotNull(message = "Seat type ID is required")
    private Integer seatTypeId;

    @NotBlank(message = "Status is required")
    @Pattern(
            regexp = "AVAILABLE|BOOKED|RESERVED",
            message = "Invalid status"
    )
    private String status;
}
