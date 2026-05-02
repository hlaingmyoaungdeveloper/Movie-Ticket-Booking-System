package com.soft.movie_ticket_booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soft.movie_ticket_booking_system.model.SeatType;

public interface SeatTypeRepository extends JpaRepository<SeatType, Integer> {
}
