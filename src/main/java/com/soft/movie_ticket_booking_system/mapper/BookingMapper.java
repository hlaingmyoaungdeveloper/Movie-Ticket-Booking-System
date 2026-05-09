package com.soft.movie_ticket_booking_system.mapper;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.soft.movie_ticket_booking_system.dto.request.BookingRequest;
import com.soft.movie_ticket_booking_system.dto.response.BookingResponse;
import com.soft.movie_ticket_booking_system.model.Booking;
import com.soft.movie_ticket_booking_system.model.Seat;
import com.soft.movie_ticket_booking_system.model.Show;

public class BookingMapper {

    public static Booking toBooking(BookingRequest request, Show show, Set<Seat> seats) {
        Booking booking = new Booking();
        booking.setCustomerName(request.getCustomerName());
        booking.setShow(show);
        booking.setTotalPrice(request.getTotalPrice());
        booking.setBookingTime(Instant.now());
        booking.setSeats(seats);
        return booking;
    }

    public static BookingResponse toBookingDto(Booking booking) {
        BookingResponse response = new BookingResponse();
        response.setBookingId(booking.getBookingId());
        response.setCustomerName(booking.getCustomerName());
        if (booking.getShow() != null) {
            response.setShowId(booking.getShow().getShowId());
        }
        response.setTotalPrice(booking.getTotalPrice());
        response.setBookingTime(booking.getBookingTime());
        if (booking.getSeats() != null) {
            response.setSeatIds(booking.getSeats().stream()
                    .map(Seat::getSeatId)
                    .collect(Collectors.toSet()));
        }
        return response;
    }
}
