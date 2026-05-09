package com.soft.movie_ticket_booking_system.service;

import java.util.List;

import com.soft.movie_ticket_booking_system.dto.request.SeatTypeRequest;
import com.soft.movie_ticket_booking_system.dto.response.PageResponse;
import com.soft.movie_ticket_booking_system.dto.response.SeatTypeResponse;

public interface SeatTypeService {
    SeatTypeResponse createSeatType(SeatTypeRequest request);
    PageResponse<SeatTypeResponse> getAllSeatTypes(int page, int size, String sortBy, String direction);
    SeatTypeResponse findBySeatTypeId(Integer seatTypeId);
    List<SeatTypeResponse> findBySeatTypeName(String seatTypeName);
    SeatTypeResponse updateSeatType(Integer seatTypeId, SeatTypeRequest request);
    void deleteSeatType(Integer seatTypeId);
}
