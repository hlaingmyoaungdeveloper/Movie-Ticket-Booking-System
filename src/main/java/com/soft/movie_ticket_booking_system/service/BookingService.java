package com.soft.movie_ticket_booking_system.service;

import java.util.List;

import com.soft.movie_ticket_booking_system.dto.request.BookingRequest;
import com.soft.movie_ticket_booking_system.dto.response.BookingResponse;
import com.soft.movie_ticket_booking_system.dto.response.PageResponse;

public interface BookingService {
    BookingResponse createBooking(BookingRequest request);
    PageResponse<BookingResponse> getAllBookings(int page, int size, String sortBy, String direction);
    BookingResponse findByBookingId(Integer bookingId);
    List<BookingResponse> findByCustomerName(String customerName);
    List<BookingResponse> findByShowId(Integer showId);
    BookingResponse updateBooking(Integer bookingId, BookingRequest request);
    void deleteBooking(Integer bookingId);
}
