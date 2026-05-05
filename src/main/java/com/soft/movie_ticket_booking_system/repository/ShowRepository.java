package com.soft.movie_ticket_booking_system.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soft.movie_ticket_booking_system.model.Show;

public interface ShowRepository extends JpaRepository<Show, Integer> {
    List<Show> findByMovie_MovieId(Integer movieId);

    List<Show> findByStartTime(Instant startTime);

    List<Show> findByEndTime(Instant endTime);
}
