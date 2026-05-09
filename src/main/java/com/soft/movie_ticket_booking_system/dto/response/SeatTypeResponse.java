package com.soft.movie_ticket_booking_system.dto.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SeatTypeResponse {
    private Integer seatTypeId;
    private String seatTypeName;
    private BigDecimal price;
}
