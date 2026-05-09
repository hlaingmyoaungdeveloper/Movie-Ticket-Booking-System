package com.soft.movie_ticket_booking_system.dto.response;

import lombok.Data;

@Data
public class SeatResponse {
    private Integer seatId;
    private String seatCode;
    private Integer seatTypeId;
    private String seatTypeName;
    private String status;
}
