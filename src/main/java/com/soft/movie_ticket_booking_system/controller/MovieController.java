package com.soft.movie_ticket_booking_system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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
import com.soft.movie_ticket_booking_system.dto.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<MovieResponse>> createMovie(@Valid @RequestPart("request") MovieRequest request,
                                                                 @RequestPart("file") MultipartFile file) {
        MovieResponse response = movieService.createMovie(request, file);
        return new ResponseEntity<>(ApiResponse.success(response, "Movie created successfully"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<MovieResponse>>> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "movieId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        PageResponse<MovieResponse> response = movieService.getAllMovies(page, size, sortBy, direction);
        return ResponseEntity.ok(ApiResponse.success(response, "Movies retrieved successfully"));
    }

    @GetMapping("/search/movie-id/{movieId}")
    public ResponseEntity<ApiResponse<MovieResponse>> findByMovieId(@PathVariable Integer movieId) {
        MovieResponse response = movieService.findByMovieId(movieId);
        return ResponseEntity.ok(ApiResponse.success(response, "Movie found successfully"));
    }

    @GetMapping("/search/movie-name/{movieName}")
    public ResponseEntity<ApiResponse<List<MovieResponse>>> findByMovieName(@PathVariable String movieName) {
        List<MovieResponse> response = movieService.findByMovieName(movieName);
        return ResponseEntity.ok(ApiResponse.success(response, "Movies found successfully"));
    }

    @GetMapping("/search/movie-duration/{movieDuration}")
    public ResponseEntity<ApiResponse<List<MovieResponse>>> findByMovieDuration(@PathVariable Integer movieDuration) {
        List<MovieResponse> response = movieService.findByMovieDuration(movieDuration);
        return ResponseEntity.ok(ApiResponse.success(response, "Movies found successfully"));
    }

    @GetMapping("/search/movie-type/{movieType}")
    public ResponseEntity<ApiResponse<List<MovieResponse>>> findByMovieType(@PathVariable String movieType) {
        List<MovieResponse> response = movieService.findByMovieType(movieType);
        return ResponseEntity.ok(ApiResponse.success(response, "Movies found successfully"));
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<ApiResponse<List<MovieResponse>>> findByStatus(@PathVariable String status) {
        List<MovieResponse> response = movieService.findByStatus(status);
        return ResponseEntity.ok(ApiResponse.success(response, "Movies found successfully"));
    }

    @GetMapping("/search/delete-flag/{deleteFlag}")
    public ResponseEntity<ApiResponse<List<MovieResponse>>> findByDeleteFlag(@PathVariable Boolean deleteFlag) {
        List<MovieResponse> response = movieService.findByDeleteFlag(deleteFlag);
        return ResponseEntity.ok(ApiResponse.success(response, "Movies found successfully"));
    }

    @PutMapping(value = "/{movieId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<MovieResponse>> updateMovie(@PathVariable Integer movieId,
                                                                 @Valid @RequestPart("request") MovieRequest request,
                                                                 @RequestPart("file") MultipartFile file) {
        MovieResponse response = movieService.updateMovie(movieId, request, file);
        return ResponseEntity.ok(ApiResponse.success(response, "Movie updated successfully"));
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<ApiResponse<Void>> deleteMovie(@PathVariable Integer movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.ok(ApiResponse.success(null, "Movie deleted successfully"));
    }
}
