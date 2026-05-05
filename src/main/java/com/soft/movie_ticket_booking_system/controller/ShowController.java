package com.soft.movie_ticket_booking_system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soft.movie_ticket_booking_system.dto.request.ShowRequest;
import com.soft.movie_ticket_booking_system.dto.response.ApiResponse;
import com.soft.movie_ticket_booking_system.dto.response.PageResponse;
import com.soft.movie_ticket_booking_system.dto.response.ShowResponse;
import com.soft.movie_ticket_booking_system.service.ShowService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @PostMapping
    public ResponseEntity<ApiResponse<ShowResponse>> createShow(@Valid @RequestBody ShowRequest request) {
        ShowResponse response = showService.createShow(request);
        return new ResponseEntity<>(ApiResponse.success(response, "Show created successfully"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ShowResponse>>> getAllShows(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "showId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        PageResponse<ShowResponse> response = showService.getAllShows(page, size, sortBy, direction);
        return ResponseEntity.ok(ApiResponse.success(response, "Shows retrieved successfully"));
    }

    @GetMapping("/search/show-id/{showId}")
    public ResponseEntity<ApiResponse<ShowResponse>> findByShowId(@PathVariable Integer showId) {
        ShowResponse response = showService.findByShowId(showId);
        return ResponseEntity.ok(ApiResponse.success(response, "Show found successfully"));
    }

    @GetMapping("/search/movie-id/{movieId}")
    public ResponseEntity<ApiResponse<List<ShowResponse>>> findByMovieId(@PathVariable Integer movieId) {
        List<ShowResponse> response = showService.findByMovieId(movieId);
        return ResponseEntity.ok(ApiResponse.success(response, "Shows for movie found successfully"));
    }

    @GetMapping("/search/start-time/{startTime}")
    public ResponseEntity<ApiResponse<List<ShowResponse>>> findByStartTime(@PathVariable String startTime) {
        List<ShowResponse> response = showService.findByStartTime(startTime);
        return ResponseEntity.ok(ApiResponse.success(response, "Shows by start time found successfully"));
    }

    @GetMapping("/search/end-time/{endTime}")
    public ResponseEntity<ApiResponse<List<ShowResponse>>> findByEndTime(@PathVariable String endTime) {
        List<ShowResponse> response = showService.findByEndTime(endTime);
        return ResponseEntity.ok(ApiResponse.success(response, "Shows by end time found successfully"));
    }

    @PutMapping("/{showId}")
    public ResponseEntity<ApiResponse<ShowResponse>> updateShow(@PathVariable Integer showId, @Valid @RequestBody ShowRequest request) {
        ShowResponse response = showService.updateShow(showId, request);
        return ResponseEntity.ok(ApiResponse.success(response, "Show updated successfully"));
    }

    @DeleteMapping("/{showId}")
    public ResponseEntity<ApiResponse<Void>> deleteShow(@PathVariable Integer showId) {
        showService.deleteShow(showId);
        return ResponseEntity.ok(ApiResponse.success(null, "Show deleted successfully"));
    }
}
