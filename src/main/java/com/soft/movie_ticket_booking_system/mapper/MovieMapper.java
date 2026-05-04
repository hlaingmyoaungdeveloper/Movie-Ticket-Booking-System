package com.soft.movie_ticket_booking_system.mapper;

import com.soft.movie_ticket_booking_system.dto.request.MovieRequest;
import com.soft.movie_ticket_booking_system.dto.response.MovieResponse;
import com.soft.movie_ticket_booking_system.model.Movie;

public class MovieMapper {

    public static Movie toMovie(MovieRequest movieDto) {
        Movie movie = new Movie();
        movie.setMovieName(movieDto.getMovieName());
        movie.setMovieDescription(movieDto.getMovieDescription());
        movie.setMovieDuration(movieDto.getMovieDuration());
        movie.setMovieType(movieDto.getMovieType());
        movie.setStatus(movieDto.getStatus());
        movie.setImageUrl(movieDto.getImageUrl());
        return movie;
    }

    public static MovieResponse toMovieDto(Movie movie) {
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setMovieId(movie.getMovieId());
        movieResponse.setMovieName(movie.getMovieName());
        movieResponse.setMovieDescription(movie.getMovieDescription());
        movieResponse.setMovieDuration(movie.getMovieDuration());
        movieResponse.setMovieType(movie.getMovieType());
        movieResponse.setStatus(movie.getStatus());
        movieResponse.setDeleteFlag(movie.getDeleteFlag());
        movieResponse.setImageUrl(movie.getImageUrl());
        return movieResponse;
    }
}
