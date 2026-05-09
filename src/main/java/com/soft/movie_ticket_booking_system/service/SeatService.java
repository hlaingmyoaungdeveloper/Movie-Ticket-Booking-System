package com.soft.movie_ticket_booking_system.service;

import java.util.List;

import com.soft.movie_ticket_booking_system.dto.request.SeatRequest;
import com.soft.movie_ticket_booking_system.dto.response.PageResponse;
import com.soft.movie_ticket_booking_system.dto.response.SeatResponse;

public interface SeatService {
    SeatResponse createSeat(SeatRequest request);
    PageResponse<SeatResponse> getAllSeats(int page, int size, String sortBy, String direction);
    SeatResponse findBySeatId(Integer seatId);
    List<SeatResponse> findBySeatCode(String seatCode);
    List<SeatResponse> findBySeatTypeId(Integer seatTypeId);
    SeatResponse updateSeat(Integer seatId, SeatRequest request);
    void deleteSeat(Integer seatId);
}
