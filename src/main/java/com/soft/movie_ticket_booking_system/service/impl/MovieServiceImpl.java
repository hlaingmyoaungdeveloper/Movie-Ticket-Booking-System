package com.soft.movie_ticket_booking_system.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.soft.movie_ticket_booking_system.dto.request.MovieRequest;
import com.soft.movie_ticket_booking_system.dto.response.MovieResponse;
import com.soft.movie_ticket_booking_system.dto.response.PageResponse;
import com.soft.movie_ticket_booking_system.exception.ResourceNotFoundException;
import com.soft.movie_ticket_booking_system.mapper.MovieMapper;
import com.soft.movie_ticket_booking_system.model.Movie;
import com.soft.movie_ticket_booking_system.repository.MovieRepository;
import com.soft.movie_ticket_booking_system.service.MovieService;
import com.soft.movie_ticket_booking_system.utils.PaginationValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private static final Set<String> MOVIE_SORT_FIELDS = Set.of(
            "movieId", "movieName", "movieDescription", "movieDuration", "movieType", "status", "deleteFlag",
            "createdBy", "createdTime", "modifiedBy", "modifiedTime");

    private final MovieRepository movieRepository;

    @Override
    public MovieResponse createMovie(MovieRequest request) {
        Movie movie = MovieMapper.toMovie(request);
        Movie savedMovie = movieRepository.save(movie);
        return MovieMapper.toMovieDto(savedMovie);
    }

    @Override
    public PageResponse<MovieResponse> getAllMovies(int page, int size, String sortBy, String direction) {
        PaginationValidator.validate(page, size, sortBy, direction, MOVIE_SORT_FIELDS);

        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Movie> moviePage = movieRepository.findAll(pageable);
        List<MovieResponse> data = moviePage.getContent().stream().map(MovieMapper::toMovieDto).toList();

        return new PageResponse<>(
                data,
                moviePage.getNumber(),
                moviePage.getSize(),
                moviePage.getTotalElements(),
                moviePage.getTotalPages());
    }

    @Override
    public List<MovieResponse> findByMovieId(Integer movieId) {
        return movieRepository.findByMovieId(movieId).stream().map(MovieMapper::toMovieDto).toList();
    }

    @Override
    public List<MovieResponse> findByMovieName(String movieName) {
        return movieRepository.findByMovieNameContainingIgnoreCase(movieName).stream().map(MovieMapper::toMovieDto).toList();
    }

    @Override
    public List<MovieResponse> findByMovieDuration(Integer movieDuration) {
        return movieRepository.findByMovieDuration(movieDuration).stream().map(MovieMapper::toMovieDto).toList();
    }

    @Override
    public List<MovieResponse> findByMovieType(String movieType) {
        return movieRepository.findByMovieTypeContainingIgnoreCase(movieType).stream().map(MovieMapper::toMovieDto).toList();
    }

    @Override
    public List<MovieResponse> findByStatus(String status) {
        return movieRepository.findByStatusContainingIgnoreCase(status).stream().map(MovieMapper::toMovieDto).toList();
    }

    @Override
    public List<MovieResponse> findByDeleteFlag(Boolean deleteFlag) {
        return movieRepository.findByDeleteFlag(deleteFlag).stream().map(MovieMapper::toMovieDto).toList();
    }


    @Override
    public MovieResponse getMovieById(Integer movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + movieId));
        return MovieMapper.toMovieDto(movie);
    }

    @Override
    public MovieResponse updateMovie(Integer movieId, MovieRequest request) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + movieId));

        movie.setMovieName(request.getMovieName());
        movie.setMovieDescription(request.getMovieDescription());
        movie.setMovieDuration(request.getMovieDuration());
        movie.setMovieType(request.getMovieType());
        movie.setStatus(request.getStatus());

        Movie updatedMovie = movieRepository.save(movie);
        return MovieMapper.toMovieDto(updatedMovie);
    }

    @Override
    public void deleteMovie(Integer movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + movieId));
        movieRepository.delete(movie);
    }
}
