package com.soft.movie_ticket_booking_system.mapper;

import com.soft.movie_ticket_booking_system.dto.request.SeatRequest;
import com.soft.movie_ticket_booking_system.dto.response.SeatResponse;
import com.soft.movie_ticket_booking_system.model.Seat;
import com.soft.movie_ticket_booking_system.model.SeatType;

public class SeatMapper {

    public static Seat toSeat(SeatRequest request, SeatType seatType) {
        Seat seat = new Seat();
        seat.setSeatCode(request.getSeatCode());
        seat.setSeatType(seatType);
        seat.setStatus(request.getStatus());
        return seat;
    }

    public static SeatResponse toSeatDto(Seat seat) {
        SeatResponse response = new SeatResponse();
        response.setSeatId(seat.getSeatId());
        response.setSeatCode(seat.getSeatCode());
        if (seat.getSeatType() != null) {
            response.setSeatTypeId(seat.getSeatType().getSeatTypeId());
            response.setSeatTypeName(seat.getSeatType().getSeatTypeName());
        }
        response.setStatus(seat.getStatus());
        return response;
    }
}
