package com.soft.movie_ticket_booking_system.service;

import java.util.List;

import com.soft.movie_ticket_booking_system.dto.request.ShowRequest;
import com.soft.movie_ticket_booking_system.dto.response.PageResponse;
import com.soft.movie_ticket_booking_system.dto.response.ShowResponse;

public interface ShowService {

    ShowResponse createShow(ShowRequest request);

    PageResponse<ShowResponse> getAllShows(int page, int size, String sortBy, String direction);

    ShowResponse findByShowId(Integer showId);

    List<ShowResponse> findByMovieId(Integer movieId);

    List<ShowResponse> findByStartTime(String startTime);

    List<ShowResponse> findByEndTime(String endTime);

    ShowResponse updateShow(Integer showId, ShowRequest request);

    void deleteShow(Integer showId);
}
