package com.soft.movie_ticket_booking_system.mapper;


import com.soft.movie_ticket_booking_system.dto.response.SeatResponse;
import com.soft.movie_ticket_booking_system.model.Seat;
import org.springframework.stereotype.Component;

@Component
public class SeatMapper {

    // =========================
    // ENTITY → DTO
    // =========================
    public SeatResponse toResponseDTO(Seat seat) {

        if (seat == null) {
            return null;
        }

        SeatResponse dto = new SeatResponse();

        dto.setSeatId(seat.getSeatId());
        dto.setSeatCode(seat.getSeatCode());
        dto.setStatus(seat.getStatus());

        if (seat.getSeatType() != null) {
            dto.setSeatTypeId(seat.getSeatType().getSeatTypeId());
            dto.setSeatTypeName(seat.getSeatType().getSeatTypeName());
            dto.setPrice(seat.getSeatType().getPrice().doubleValue());
        }

        return dto;
    }
}
