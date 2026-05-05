package com.soft.movie_ticket_booking_system.service.impl;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soft.movie_ticket_booking_system.dto.request.ShowRequest;
import com.soft.movie_ticket_booking_system.dto.response.PageResponse;
import com.soft.movie_ticket_booking_system.dto.response.ShowResponse;
import com.soft.movie_ticket_booking_system.exception.BusinessValidationException;
import com.soft.movie_ticket_booking_system.exception.ResourceNotFoundException;
import com.soft.movie_ticket_booking_system.mapper.ShowMapper;
import com.soft.movie_ticket_booking_system.model.Movie;
import com.soft.movie_ticket_booking_system.model.Show;
import com.soft.movie_ticket_booking_system.repository.MovieRepository;
import com.soft.movie_ticket_booking_system.repository.ShowRepository;
import com.soft.movie_ticket_booking_system.service.ShowService;
import com.soft.movie_ticket_booking_system.utils.PaginationValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {

    private static final Set<String> SHOW_SORT_FIELDS = Set.of("showId", "startTime", "endTime");

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;

    @Override
    @Transactional
    public ShowResponse createShow(ShowRequest request) {
        validateShowTimes(request);
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + request.getMovieId()));

        Show show = ShowMapper.toShow(request, movie);
        Show savedShow = showRepository.save(show);
        return ShowMapper.toShowDto(savedShow);
    }

    @Override
    public PageResponse<ShowResponse> getAllShows(int page, int size, String sortBy, String direction) {
        PaginationValidator.validate(page, size, sortBy, direction, SHOW_SORT_FIELDS);

        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Show> showPage = showRepository.findAll(pageable);
        List<ShowResponse> data = showPage.getContent().stream().map(ShowMapper::toShowDto).toList();

        return new PageResponse<>(
                data,
                showPage.getNumber(),
                showPage.getSize(),
                showPage.getTotalElements(),
                showPage.getTotalPages());
    }

    @Override
    public ShowResponse findByShowId(Integer showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found with id: " + showId));
        return ShowMapper.toShowDto(show);
    }

    @Override
    public List<ShowResponse> findByMovieId(Integer movieId) {
        return showRepository.findByMovie_MovieId(movieId).stream()
                .map(ShowMapper::toShowDto)
                .toList();
    }

    @Override
    public List<ShowResponse> findByStartTime(String startTime) {
        Instant parsedStartTime = parseInstant(startTime, "startTime");
        return showRepository.findByStartTime(parsedStartTime).stream()
                .map(ShowMapper::toShowDto)
                .toList();
    }

    @Override
    public List<ShowResponse> findByEndTime(String endTime) {
        Instant parsedEndTime = parseInstant(endTime, "endTime");
        return showRepository.findByEndTime(parsedEndTime).stream()
                .map(ShowMapper::toShowDto)
                .toList();
    }

    @Override
    @Transactional
    public ShowResponse updateShow(Integer showId, ShowRequest request) {
        validateShowTimes(request);
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found with id: " + showId));

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + request.getMovieId()));

        show.setMovie(movie);
        show.setStartTime(request.getStartTime());
        show.setEndTime(request.getEndTime());

        Show updatedShow = showRepository.save(show);
        return ShowMapper.toShowDto(updatedShow);
    }

    @Override
    @Transactional
    public void deleteShow(Integer showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found with id: " + showId));
        showRepository.delete(show);
    }

    private Instant parseInstant(String value, String fieldName) {
        try {
            return Instant.parse(value);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Invalid " + fieldName + " format. Expected ISO-8601 instant.", ex);
        }
    }

    private void validateShowTimes(ShowRequest request) {
        if (request.getEndTime().isBefore(request.getStartTime())) {
            throw new BusinessValidationException("Show end time must be after start time");
        }
    }
}
