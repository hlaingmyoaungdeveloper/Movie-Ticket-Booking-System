package com.soft.movie_ticket_booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.soft.movie_ticket_booking_system.model.Seat;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    List<Seat> findByStatus(String status);

    List<Seat> findBySeatType_SeatTypeId(Integer seatTypeId);

    List<Seat> findBySeatCodeContainingIgnoreCase(String keyword);

    boolean existsBySeatCode(String seatCode);

    List<Seat> findByStatusAndSeatType_SeatTypeId(String status, Integer seatTypeId);

    // Get available seats only
    @Query("SELECT s FROM Seat s WHERE s.status = 'AVAILABLE'")
    List<Seat> findAvailableSeats();

    // Count seats by status
    long countByStatus(String status);
}
