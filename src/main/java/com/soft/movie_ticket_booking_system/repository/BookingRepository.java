package com.soft.movie_ticket_booking_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soft.movie_ticket_booking_system.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByCustomerNameContainingIgnoreCase(String customerName);
    List<Booking> findByShow_ShowId(Integer showId);
    Booking findByQrCode(String qrCode);
}
