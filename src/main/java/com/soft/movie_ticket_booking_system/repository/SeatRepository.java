package com.soft.movie_ticket_booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soft.movie_ticket_booking_system.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
