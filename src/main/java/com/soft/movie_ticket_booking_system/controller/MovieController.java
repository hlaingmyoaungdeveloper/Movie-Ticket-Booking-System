package com.soft.movie_ticket_booking_system.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.soft.movie_ticket_booking_system.dto.request.MovieRequest;
import com.soft.movie_ticket_booking_system.dto.response.MovieResponse;
import com.soft.movie_ticket_booking_system.dto.response.PageResponse;
import com.soft.movie_ticket_booking_system.service.MovieService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MovieResponse> createMovie(@Valid @RequestPart("request") MovieRequest request,
                                                     @RequestPart("file") MultipartFile file) {
        MovieResponse response = movieService.createMovie(request, file);
        return ResponseEntity.created(URI.create("/api/movies/" + response.getMovieId())).body(response);
    }

    @GetMapping
    public ResponseEntity<PageResponse<MovieResponse>> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "movieId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(movieService.getAllMovies(page, size, sortBy, direction));
    }

    @GetMapping("/search/movie-id")
    public ResponseEntity<List<MovieResponse>> findByMovieId(@RequestParam Integer movieId) {
        return ResponseEntity.ok(movieService.findByMovieId(movieId));
    }

    @GetMapping("/search/movie-name")
    public ResponseEntity<List<MovieResponse>> findByMovieName(@RequestParam String movieName) {
        return ResponseEntity.ok(movieService.findByMovieName(movieName));
    }

    @GetMapping("/search/movie-duration")
    public ResponseEntity<List<MovieResponse>> findByMovieDuration(@RequestParam Integer movieDuration) {
        return ResponseEntity.ok(movieService.findByMovieDuration(movieDuration));
    }

    @GetMapping("/search/movie-type")
    public ResponseEntity<List<MovieResponse>> findByMovieType(@RequestParam String movieType) {
        return ResponseEntity.ok(movieService.findByMovieType(movieType));
    }

    @GetMapping("/search/status")
    public ResponseEntity<List<MovieResponse>> findByStatus(@RequestParam String status) {
        return ResponseEntity.ok(movieService.findByStatus(status));
    }

    @GetMapping("/search/delete-flag")
    public ResponseEntity<List<MovieResponse>> findByDeleteFlag(@RequestParam Boolean deleteFlag) {
        return ResponseEntity.ok(movieService.findByDeleteFlag(deleteFlag));
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Integer movieId) {
        return ResponseEntity.ok(movieService.getMovieById(movieId));
    }

    @PutMapping(value = "/{movieId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable Integer movieId,
                                                     @Valid @RequestPart("request") MovieRequest request,
                                                     @RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(movieService.updateMovie(movieId, request, file));
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.noContent().build();
    }
}
