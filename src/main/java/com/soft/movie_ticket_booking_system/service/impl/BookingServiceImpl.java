package com.soft.movie_ticket_booking_system.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soft.movie_ticket_booking_system.dto.request.BookingRequest;
import com.soft.movie_ticket_booking_system.dto.response.BookingResponse;
import com.soft.movie_ticket_booking_system.dto.response.PageResponse;
import com.soft.movie_ticket_booking_system.exception.ResourceNotFoundException;
import com.soft.movie_ticket_booking_system.mapper.BookingMapper;
import com.soft.movie_ticket_booking_system.model.Booking;
import com.soft.movie_ticket_booking_system.model.Seat;
import com.soft.movie_ticket_booking_system.model.Show;
import com.soft.movie_ticket_booking_system.repository.BookingRepository;
import com.soft.movie_ticket_booking_system.repository.SeatRepository;
import com.soft.movie_ticket_booking_system.repository.ShowRepository;
import com.soft.movie_ticket_booking_system.service.BookingService;
import com.soft.movie_ticket_booking_system.utils.PaginationValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private static final Set<String> BOOKING_SORT_FIELDS = Set.of("bookingId", "customerName", "totalPrice", "bookingTime");

    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;

    @Override
    @Transactional
    public BookingResponse createBooking(BookingRequest request) {
        Show show = showRepository.findById(request.getShowId())
                .orElseThrow(() -> new ResourceNotFoundException("Show not found with id: " + request.getShowId()));

        Set<Seat> seats = new HashSet<>(seatRepository.findAllById(request.getSeatIds()));
        if (seats.size() != request.getSeatIds().size()) {
            throw new ResourceNotFoundException("Some seats were not found");
        }

        Booking booking = BookingMapper.toBooking(request, show, seats);
        Booking savedBooking = bookingRepository.save(booking);
        return BookingMapper.toBookingDto(savedBooking);
    }

    @Override
    public PageResponse<BookingResponse> getAllBookings(int page, int size, String sortBy, String direction) {
        PaginationValidator.validate(page, size, sortBy, direction, BOOKING_SORT_FIELDS);

        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Booking> bookingPage = bookingRepository.findAll(pageable);
        List<BookingResponse> data = bookingPage.getContent().stream().map(BookingMapper::toBookingDto).toList();

        return new PageResponse<>(
                data,
                bookingPage.getNumber(),
                bookingPage.getSize(),
                bookingPage.getTotalElements(),
                bookingPage.getTotalPages());
    }

    @Override
    public BookingResponse findByBookingId(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));
        return BookingMapper.toBookingDto(booking);
    }

    @Override
    public List<BookingResponse> findByCustomerName(String customerName) {
        return bookingRepository.findByCustomerNameContainingIgnoreCase(customerName).stream()
                .map(BookingMapper::toBookingDto)
                .toList();
    }

    @Override
    public List<BookingResponse> findByShowId(Integer showId) {
        return bookingRepository.findByShow_ShowId(showId).stream()
                .map(BookingMapper::toBookingDto)
                .toList();
    }

    @Override
    @Transactional
    public BookingResponse updateBooking(Integer bookingId, BookingRequest request) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

        Show show = showRepository.findById(request.getShowId())
                .orElseThrow(() -> new ResourceNotFoundException("Show not found with id: " + request.getShowId()));

        Set<Seat> seats = new HashSet<>(seatRepository.findAllById(request.getSeatIds()));
        if (seats.size() != request.getSeatIds().size()) {
            throw new ResourceNotFoundException("Some seats were not found");
        }

        booking.setCustomerName(request.getCustomerName());
        booking.setShow(show);
        booking.setTotalPrice(request.getTotalPrice());
        booking.setSeats(seats);

        Booking updatedBooking = bookingRepository.save(booking);
        return BookingMapper.toBookingDto(updatedBooking);
    }

    @Override
    @Transactional
    public void deleteBooking(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));
        bookingRepository.delete(booking);
    }
}
