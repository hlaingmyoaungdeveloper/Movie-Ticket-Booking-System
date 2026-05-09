package com.soft.movie_ticket_booking_system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.soft.movie_ticket_booking_system.dto.request.SeatRequest;
import com.soft.movie_ticket_booking_system.dto.response.ApiResponse;
import com.soft.movie_ticket_booking_system.dto.response.PageResponse;
import com.soft.movie_ticket_booking_system.dto.response.SeatResponse;
import com.soft.movie_ticket_booking_system.service.SeatService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @PostMapping
    public ResponseEntity<ApiResponse<SeatResponse>> createSeat(@Valid @RequestBody SeatRequest request) {
        SeatResponse response = seatService.createSeat(request);
        return new ResponseEntity<>(ApiResponse.success(response, "Seat created successfully"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<SeatResponse>>> getAllSeats(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "seatId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        PageResponse<SeatResponse> response = seatService.getAllSeats(page, size, sortBy, direction);
        return ResponseEntity.ok(ApiResponse.success(response, "Seats retrieved successfully"));
    }

    @GetMapping("/search/seat-id/{seatId}")
    public ResponseEntity<ApiResponse<SeatResponse>> findBySeatId(@PathVariable Integer seatId) {
        SeatResponse response = seatService.findBySeatId(seatId);
        return ResponseEntity.ok(ApiResponse.success(response, "Seat found successfully"));
    }

    @GetMapping("/search/seat-code/{seatCode}")
    public ResponseEntity<ApiResponse<List<SeatResponse>>> findBySeatCode(@PathVariable String seatCode) {
        List<SeatResponse> response = seatService.findBySeatCode(seatCode);
        return ResponseEntity.ok(ApiResponse.success(response, "Seats found successfully"));
    }

    @GetMapping("/search/seat-type-id/{seatTypeId}")
    public ResponseEntity<ApiResponse<List<SeatResponse>>> findBySeatTypeId(@PathVariable Integer seatTypeId) {
        List<SeatResponse> response = seatService.findBySeatTypeId(seatTypeId);
        return ResponseEntity.ok(ApiResponse.success(response, "Seats found successfully"));
    }

    @PutMapping("/{seatId}")
    public ResponseEntity<ApiResponse<SeatResponse>> updateSeat(@PathVariable Integer seatId,
                                                               @Valid @RequestBody SeatRequest request) {
        SeatResponse response = seatService.updateSeat(seatId, request);
        return ResponseEntity.ok(ApiResponse.success(response, "Seat updated successfully"));
    }

    @DeleteMapping("/{seatId}")
    public ResponseEntity<ApiResponse<Void>> deleteSeat(@PathVariable Integer seatId) {
        seatService.deleteSeat(seatId);
        return ResponseEntity.ok(ApiResponse.success(null, "Seat deleted successfully"));
    }
}
