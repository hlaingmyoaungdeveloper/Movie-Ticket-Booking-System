package com.soft.movie_ticket_booking_system.controller;

import com.soft.movie_ticket_booking_system.dto.request.SeatRequest;
import com.soft.movie_ticket_booking_system.dto.response.SeatResponse;
import com.soft.movie_ticket_booking_system.service.SeatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    // creating seats
    @PostMapping
    public ResponseEntity<SeatResponse> createSeat(
            @Valid @RequestBody SeatRequest requestDTO) {

        SeatResponse response = seatService.createSeat(requestDTO);
        return ResponseEntity.ok(response);
    }


    // getting all seats
    @GetMapping
    public ResponseEntity<List<SeatResponse>> getAllSeats() {

        List<SeatResponse> seats = seatService.getAllSeats();
        return ResponseEntity.ok(seats);
    }

    // getting seat by id
    @GetMapping("/{id}")
    public ResponseEntity<SeatResponse> getSeatById(
            @PathVariable Integer id) {

        SeatResponse response = seatService.getSeatById(id);
        return ResponseEntity.ok(response);
    }

    // updating seats
    @PutMapping("/{id}")
    public ResponseEntity<SeatResponse> updateSeat(
            @PathVariable Integer id,
            @Valid @RequestBody SeatRequest requestDTO) {

        SeatResponse response = seatService.updateSeat(id, requestDTO);
        return ResponseEntity.ok(response);
    }


    // deleting seats
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSeat(@PathVariable Integer id) {

        seatService.deleteSeat(id);
        return ResponseEntity.ok("Seat deleted successfully");
    }

    // getting avaiable seats

    @GetMapping("/available")
    public ResponseEntity<List<SeatResponse>> getAvailableSeats() {

        List<SeatResponse> seats = seatService.getAvailableSeats();
        return ResponseEntity.ok(seats);
    }

    // searching by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<SeatResponse>> getSeatsByStatus(
            @PathVariable String status) {

        List<SeatResponse> seats = seatService.getSeatsByStatus(status);
        return ResponseEntity.ok(seats);
    }
}
