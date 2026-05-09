package com.soft.movie_ticket_booking_system.dto.request;

import java.math.BigDecimal;
import java.util.Set;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingRequest {

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotNull(message = "Show ID is required")
    private Integer showId;

    @NotNull(message = "Total price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total price must be greater than 0")
    private BigDecimal totalPrice;

    @NotNull(message = "Seat IDs are required")
    private Set<Integer> seatIds;
}
