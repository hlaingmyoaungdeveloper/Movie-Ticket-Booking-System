package com.soft.movie_ticket_booking_system.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soft.movie_ticket_booking_system.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByMovieId(Integer movieId);

    List<Movie> findByMovieNameContainingIgnoreCase(String movieName);

    List<Movie> findByMovieDescriptionContainingIgnoreCase(String movieDescription);

    List<Movie> findByMovieDuration(Integer movieDuration);

    List<Movie> findByMovieTypeContainingIgnoreCase(String movieType);

    List<Movie> findByStatusContainingIgnoreCase(String status);

    List<Movie> findByDeleteFlag(Boolean deleteFlag);

    List<Movie> findByCreatedBy(Integer createdBy);

    List<Movie> findByCreatedTime(Instant createdTime);

    List<Movie> findByModifiedBy(Integer modifiedBy);

    List<Movie> findByModifiedTime(Instant modifiedTime);
}
