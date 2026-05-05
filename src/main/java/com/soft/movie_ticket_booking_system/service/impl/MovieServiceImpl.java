package com.soft.movie_ticket_booking_system.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.soft.movie_ticket_booking_system.dto.request.MovieRequest;
import com.soft.movie_ticket_booking_system.dto.response.MovieResponse;
import com.soft.movie_ticket_booking_system.dto.response.PageResponse;
import com.soft.movie_ticket_booking_system.exception.FileStorageException;
import com.soft.movie_ticket_booking_system.exception.ResourceNotFoundException;
import com.soft.movie_ticket_booking_system.mapper.MovieMapper;
import com.soft.movie_ticket_booking_system.model.Movie;
import com.soft.movie_ticket_booking_system.repository.MovieRepository;
import com.soft.movie_ticket_booking_system.service.CloudinaryService;
import com.soft.movie_ticket_booking_system.service.MovieService;
import com.soft.movie_ticket_booking_system.utils.PaginationValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private static final Set<String> MOVIE_SORT_FIELDS = Set.of(
            "movieId", "movieName", "movieDescription", "movieDuration", "movieType", "status", "deleteFlag",
            "createdBy", "createdTime", "modifiedBy", "modifiedTime");

    private final MovieRepository movieRepository;
    private final CloudinaryService cloudinaryService;

    @Override
    public MovieResponse createMovie(MovieRequest request, MultipartFile file) {
        try {
            Map uploadPhoto = cloudinaryService.uploadFile(file);

            String imageUrl = (String) uploadPhoto.get("secure_url");
            String publicId = (String) uploadPhoto.get("public_id");
            Movie movie = MovieMapper.toMovie(request);
            movie.setImageUrl(imageUrl);
            movie.setPublicId(publicId);

            Movie savedMovie = movieRepository.save(movie);
            return MovieMapper.toMovieDto(savedMovie);
        } catch (IOException e) {
            throw new FileStorageException("Failed to create movie", e);
        }
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
    public MovieResponse findByMovieId(Integer movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + movieId));
        return MovieMapper.toMovieDto(movie);
    }

    @Override
    public List<MovieResponse> findByMovieName(String movieName) {
        return movieRepository.findByMovieNameContainingIgnoreCase(movieName).stream().map(MovieMapper::toMovieDto)
                .toList();
    }

    @Override
    public List<MovieResponse> findByMovieDuration(Integer movieDuration) {
        return movieRepository.findByMovieDuration(movieDuration).stream().map(MovieMapper::toMovieDto).toList();
    }

    @Override
    public List<MovieResponse> findByMovieType(String movieType) {
        return movieRepository.findByMovieTypeContainingIgnoreCase(movieType).stream().map(MovieMapper::toMovieDto)
                .toList();
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
    @Transactional
    public MovieResponse updateMovie(Integer movieId, MovieRequest request, MultipartFile file) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + movieId));

        try {
            if (file != null && !file.isEmpty()) {
                // Delete old image
                if (movie.getPublicId() != null) {
                    cloudinaryService.deleteFile(movie.getPublicId());
                }
                // Upload new image
                Map uploadResult = cloudinaryService.uploadFile(file);
                movie.setImageUrl(uploadResult.get("secure_url").toString());
                movie.setPublicId(uploadResult.get("public_id").toString());
            }
        } catch (IOException e) {
            throw new FileStorageException("Failed to update movie image", e);
        }

        movie.setMovieName(request.getMovieName());
        movie.setMovieDescription(request.getMovieDescription());
        movie.setMovieDuration(request.getMovieDuration());
        movie.setMovieType(request.getMovieType());
        movie.setStatus(request.getStatus());

        Movie updatedMovie = movieRepository.save(movie);
        return MovieMapper.toMovieDto(updatedMovie);
    }

    @Override
    @Transactional
    public void deleteMovie(Integer movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + movieId));

        try {
            cloudinaryService.deleteFile(movie.getPublicId());
        } catch (IOException e) {
            throw new FileStorageException("Failed to delete movie image", e);
        }

        movieRepository.delete(movie);
    }
}
