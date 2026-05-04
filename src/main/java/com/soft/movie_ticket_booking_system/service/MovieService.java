package com.soft.movie_ticket_booking_system.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.soft.movie_ticket_booking_system.dto.request.MovieRequest;
import com.soft.movie_ticket_booking_system.dto.response.MovieResponse;
import com.soft.movie_ticket_booking_system.dto.response.PageResponse;

public interface MovieService {

    MovieResponse createMovie(MovieRequest request,MultipartFile file);

    PageResponse<MovieResponse> getAllMovies(int page, int size, String sortBy, String direction);

    List<MovieResponse> findByMovieId(Integer movieId);

    List<MovieResponse> findByMovieName(String movieName);

    List<MovieResponse> findByMovieDuration(Integer movieDuration);

    List<MovieResponse> findByMovieType(String movieType);

    List<MovieResponse> findByStatus(String status);

    List<MovieResponse> findByDeleteFlag(Boolean deleteFlag);

    MovieResponse getMovieById(Integer movieId);

    MovieResponse updateMovie(Integer movieId, MovieRequest request,MultipartFile file);

    void deleteMovie(Integer movieId);
}
