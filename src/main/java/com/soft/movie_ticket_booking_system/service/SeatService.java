package com.soft.movie_ticket_booking_system.service;

import com.soft.movie_ticket_booking_system.dto.request.SeatRequest;
import com.soft.movie_ticket_booking_system.dto.response.SeatResponse;

import java.util.List;

public interface SeatService {

    SeatResponse createSeat(SeatRequest requestDTO);

    List<SeatResponse> getAllSeats();

    SeatResponse getSeatById(Integer id);

    SeatResponse updateSeat(Integer id, SeatRequest requestDTO);

    void deleteSeat(Integer id);

    List<SeatResponse> getAvailableSeats();

    List<SeatResponse> getSeatsByStatus(String status);
}

