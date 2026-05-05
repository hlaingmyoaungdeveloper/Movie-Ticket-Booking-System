package com.soft.movie_ticket_booking_system.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SeatResponse {
    private Integer seatId;
    private String seatCode;
    private String status;

    private Integer seatTypeId;
    private String seatTypeName;
    private Double price;
}
