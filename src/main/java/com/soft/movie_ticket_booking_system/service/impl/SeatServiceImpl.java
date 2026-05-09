package com.soft.movie_ticket_booking_system.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soft.movie_ticket_booking_system.dto.request.SeatRequest;
import com.soft.movie_ticket_booking_system.dto.response.PageResponse;
import com.soft.movie_ticket_booking_system.dto.response.SeatResponse;
import com.soft.movie_ticket_booking_system.exception.ResourceNotFoundException;
import com.soft.movie_ticket_booking_system.mapper.SeatMapper;
import com.soft.movie_ticket_booking_system.model.Seat;
import com.soft.movie_ticket_booking_system.model.SeatType;
import com.soft.movie_ticket_booking_system.repository.SeatRepository;
import com.soft.movie_ticket_booking_system.repository.SeatTypeRepository;
import com.soft.movie_ticket_booking_system.service.SeatService;
import com.soft.movie_ticket_booking_system.utils.PaginationValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private static final Set<String> SEAT_SORT_FIELDS = Set.of("seatId", "seatCode", "status");

    private final SeatRepository seatRepository;
    private final SeatTypeRepository seatTypeRepository;

    @Override
    @Transactional
    public SeatResponse createSeat(SeatRequest request) {
        SeatType seatType = seatTypeRepository.findById(request.getSeatTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("SeatType not found with id: " + request.getSeatTypeId()));

        Seat seat = SeatMapper.toSeat(request, seatType);
        Seat savedSeat = seatRepository.save(seat);
        return SeatMapper.toSeatDto(savedSeat);
    }

    @Override
    public PageResponse<SeatResponse> getAllSeats(int page, int size, String sortBy, String direction) {
        PaginationValidator.validate(page, size, sortBy, direction, SEAT_SORT_FIELDS);

        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Seat> seatPage = seatRepository.findAll(pageable);
        List<SeatResponse> data = seatPage.getContent().stream().map(SeatMapper::toSeatDto).toList();

        return new PageResponse<>(
                data,
                seatPage.getNumber(),
                seatPage.getSize(),
                seatPage.getTotalElements(),
                seatPage.getTotalPages());
    }

    @Override
    public SeatResponse findBySeatId(Integer seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found with id: " + seatId));
        return SeatMapper.toSeatDto(seat);
    }

    @Override
    public List<SeatResponse> findBySeatCode(String seatCode) {
        return seatRepository.findBySeatCodeContainingIgnoreCase(seatCode).stream()
                .map(SeatMapper::toSeatDto)
                .toList();
    }

    @Override
    public List<SeatResponse> findBySeatTypeId(Integer seatTypeId) {
        return seatRepository.findBySeatType_SeatTypeId(seatTypeId).stream()
                .map(SeatMapper::toSeatDto)
                .toList();
    }

    @Override
    @Transactional
    public SeatResponse updateSeat(Integer seatId, SeatRequest request) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found with id: " + seatId));

        SeatType seatType = seatTypeRepository.findById(request.getSeatTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("SeatType not found with id: " + request.getSeatTypeId()));

        seat.setSeatCode(request.getSeatCode());
        seat.setSeatType(seatType);
        seat.setStatus(request.getStatus());

        Seat updatedSeat = seatRepository.save(seat);
        return SeatMapper.toSeatDto(updatedSeat);
    }

    @Override
    @Transactional
    public void deleteSeat(Integer seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found with id: " + seatId));
        seatRepository.delete(seat);
    }
}
