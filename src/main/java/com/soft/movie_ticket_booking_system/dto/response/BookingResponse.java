package com.soft.movie_ticket_booking_system.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import lombok.Data;

@Data
public class BookingResponse {
    private Integer bookingId;
    private String customerName;
    private Integer showId;
    private BigDecimal totalPrice;
    private Instant bookingTime;
    private Set<Integer> seatIds;
}
