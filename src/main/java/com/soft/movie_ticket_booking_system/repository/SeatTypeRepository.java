package com.soft.movie_ticket_booking_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soft.movie_ticket_booking_system.model.SeatType;

public interface SeatTypeRepository extends JpaRepository<SeatType, Integer> {
    List<SeatType> findBySeatTypeNameContainingIgnoreCase(String seatTypeName);
}
