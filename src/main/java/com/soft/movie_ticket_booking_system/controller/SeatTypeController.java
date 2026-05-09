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

import com.soft.movie_ticket_booking_system.dto.request.SeatTypeRequest;
import com.soft.movie_ticket_booking_system.dto.response.ApiResponse;
import com.soft.movie_ticket_booking_system.dto.response.PageResponse;
import com.soft.movie_ticket_booking_system.dto.response.SeatTypeResponse;
import com.soft.movie_ticket_booking_system.service.SeatTypeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/seat-types")
@RequiredArgsConstructor
public class SeatTypeController {

    private final SeatTypeService seatTypeService;

    @PostMapping
    public ResponseEntity<ApiResponse<SeatTypeResponse>> createSeatType(@Valid @RequestBody SeatTypeRequest request) {
        SeatTypeResponse response = seatTypeService.createSeatType(request);
        return new ResponseEntity<>(ApiResponse.success(response, "Seat type created successfully"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<SeatTypeResponse>>> getAllSeatTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "seatTypeId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        PageResponse<SeatTypeResponse> response = seatTypeService.getAllSeatTypes(page, size, sortBy, direction);
        return ResponseEntity.ok(ApiResponse.success(response, "Seat types retrieved successfully"));
    }

    @GetMapping("/search/seat-type-id/{seatTypeId}")
    public ResponseEntity<ApiResponse<SeatTypeResponse>> findBySeatTypeId(@PathVariable Integer seatTypeId) {
        SeatTypeResponse response = seatTypeService.findBySeatTypeId(seatTypeId);
        return ResponseEntity.ok(ApiResponse.success(response, "Seat type found successfully"));
    }

    @GetMapping("/search/seat-type-name/{seatTypeName}")
    public ResponseEntity<ApiResponse<List<SeatTypeResponse>>> findBySeatTypeName(@PathVariable String seatTypeName) {
        List<SeatTypeResponse> response = seatTypeService.findBySeatTypeName(seatTypeName);
        return ResponseEntity.ok(ApiResponse.success(response, "Seat types found successfully"));
    }

    @PutMapping("/{seatTypeId}")
    public ResponseEntity<ApiResponse<SeatTypeResponse>> updateSeatType(@PathVariable Integer seatTypeId,
                                                                       @Valid @RequestBody SeatTypeRequest request) {
        SeatTypeResponse response = seatTypeService.updateSeatType(seatTypeId, request);
        return ResponseEntity.ok(ApiResponse.success(response, "Seat type updated successfully"));
    }

    @DeleteMapping("/{seatTypeId}")
    public ResponseEntity<ApiResponse<Void>> deleteSeatType(@PathVariable Integer seatTypeId) {
        seatTypeService.deleteSeatType(seatTypeId);
        return ResponseEntity.ok(ApiResponse.success(null, "Seat type deleted successfully"));
    }
}
