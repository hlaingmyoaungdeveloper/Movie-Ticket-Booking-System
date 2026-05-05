package com.soft.movie_ticket_booking_system.mapper;

import com.soft.movie_ticket_booking_system.dto.request.ShowRequest;
import com.soft.movie_ticket_booking_system.dto.response.ShowResponse;
import com.soft.movie_ticket_booking_system.model.Movie;
import com.soft.movie_ticket_booking_system.model.Show;


public class ShowMapper {

    public static Show toShow(ShowRequest request,Movie movie) {
        Show show = new Show();
        show.setMovie(movie);
        show.setStartTime(request.getStartTime());
        show.setEndTime(request.getEndTime());
        return show;
    }

    public static ShowResponse toShowDto(Show show) {
        ShowResponse response = new ShowResponse();
        response.setShowId(show.getShowId());
        response.setStartTime(show.getStartTime());
        response.setEndTime(show.getEndTime());
        if (show.getMovie() != null) {
            response.setMovie(MovieMapper.toMovieDto(show.getMovie()));
        }
        return response;
    }
}
