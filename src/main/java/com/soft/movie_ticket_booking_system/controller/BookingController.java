package com.soft.movie_ticket_booking_system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soft.movie_ticket_booking_system.dto.request.BookingRequest;
import com.soft.movie_ticket_booking_system.dto.request.QrDTO;
import com.soft.movie_ticket_booking_system.dto.response.ApiResponse;
import com.soft.movie_ticket_booking_system.dto.response.BookingResponse;
import com.soft.movie_ticket_booking_system.dto.response.PageResponse;
import com.soft.movie_ticket_booking_system.service.BookingService;
import com.soft.movie_ticket_booking_system.utils.QRGenerater;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<ApiResponse<BookingResponse>> createBooking(@Valid @RequestBody BookingRequest request) {
        BookingResponse response = bookingService.createBooking(request);
        return new ResponseEntity<>(ApiResponse.success(response, "Booking created successfully"), HttpStatus.CREATED);
    }

    @GetMapping(value = "/qr/{bookingId}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getQRCode(@PathVariable Integer bookingId) {

        BookingResponse booking = bookingService.findByBookingId(bookingId);

        if (booking.getQrCode() == null) {
            return ResponseEntity.badRequest().build();
        }

        byte[] qrImage = QRGenerater.generateQRCodeImage(booking.getQrCode(), 200, 200);

        return ResponseEntity.ok(qrImage);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<BookingResponse>>> getAllBookings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "bookingId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        PageResponse<BookingResponse> response = bookingService.getAllBookings(page, size, sortBy, direction);
        return ResponseEntity.ok(ApiResponse.success(response, "Bookings retrieved successfully"));
    }

    @GetMapping("/search/booking-id/{bookingId}")
    public ResponseEntity<ApiResponse<BookingResponse>> findByBookingId(@PathVariable Integer bookingId) {
        BookingResponse response = bookingService.findByBookingId(bookingId);
        return ResponseEntity.ok(ApiResponse.success(response, "Booking found successfully"));
    }

    @GetMapping("/search/customer-name/{customerName}")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> findByCustomerName(@PathVariable String customerName) {
        List<BookingResponse> response = bookingService.findByCustomerName(customerName);
        return ResponseEntity.ok(ApiResponse.success(response, "Bookings found successfully"));
    }

    @GetMapping("/search/show-id/{showId}")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> findByShowId(@PathVariable Integer showId) {
        List<BookingResponse> response = bookingService.findByShowId(showId);
        return ResponseEntity.ok(ApiResponse.success(response, "Bookings found successfully"));
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<ApiResponse<BookingResponse>> updateBooking(@PathVariable Integer bookingId,
            @Valid @RequestBody BookingRequest request) {
        BookingResponse response = bookingService.updateBooking(bookingId, request);
        return ResponseEntity.ok(ApiResponse.success(response, "Booking updated successfully"));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<ApiResponse<Void>> deleteBooking(@PathVariable Integer bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.ok(ApiResponse.success(null, "Booking deleted successfully"));
    }

    @PostMapping("/check")
    public ResponseEntity<ApiResponse<Void>> checkBooking(@Valid @RequestBody QrDTO request) {
        bookingService.checkBooking(request);
        return ResponseEntity.ok(ApiResponse.success(null, "Booking checked successfully"));
    }
}
