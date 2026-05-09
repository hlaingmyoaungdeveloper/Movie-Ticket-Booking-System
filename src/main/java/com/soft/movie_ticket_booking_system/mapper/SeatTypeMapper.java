package com.soft.movie_ticket_booking_system.mapper;

import com.soft.movie_ticket_booking_system.dto.request.SeatTypeRequest;
import com.soft.movie_ticket_booking_system.dto.response.SeatTypeResponse;
import com.soft.movie_ticket_booking_system.model.SeatType;

public class SeatTypeMapper {

    public static SeatType toSeatType(SeatTypeRequest request) {
        SeatType seatType = new SeatType();
        seatType.setSeatTypeName(request.getSeatTypeName());
        seatType.setPrice(request.getPrice());
        return seatType;
    }

    public static SeatTypeResponse toSeatTypeDto(SeatType seatType) {
        SeatTypeResponse response = new SeatTypeResponse();
        response.setSeatTypeId(seatType.getSeatTypeId());
        response.setSeatTypeName(seatType.getSeatTypeName());
        response.setPrice(seatType.getPrice());
        return response;
    }
}
