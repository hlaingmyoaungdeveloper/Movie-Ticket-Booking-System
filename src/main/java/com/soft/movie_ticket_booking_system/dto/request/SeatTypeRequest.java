package com.soft.movie_ticket_booking_system.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SeatTypeRequest {

    @NotBlank(message = "Seat type name is required")
    @Size(max = 100, message = "Seat type name must be at most 100 characters")
    private String seatTypeName;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;
}
