package com.soft.movie_ticket_booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soft.movie_ticket_booking_system.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
