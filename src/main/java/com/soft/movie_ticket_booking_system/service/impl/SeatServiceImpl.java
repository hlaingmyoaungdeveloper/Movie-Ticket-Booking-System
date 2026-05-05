package com.soft.movie_ticket_booking_system.service.impl;

import com.soft.movie_ticket_booking_system.dto.request.SeatRequest;
import com.soft.movie_ticket_booking_system.dto.response.SeatResponse;
import com.soft.movie_ticket_booking_system.model.Seat;
import com.soft.movie_ticket_booking_system.model.SeatType;
import com.soft.movie_ticket_booking_system.repository.SeatRepository;
import com.soft.movie_ticket_booking_system.repository.SeatTypeRepository;
import com.soft.movie_ticket_booking_system.service.SeatService;
import com.soft.movie_ticket_booking_system.mapper.SeatMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements SeatService{

    private final SeatRepository seatRepository;
    private final SeatTypeRepository seatTypeRepository;

    private final SeatMapper seatMapper;

    public SeatServiceImpl(SeatRepository seatRepository,
                           SeatTypeRepository seatTypeRepository,
                           SeatMapper seatMapper) {
        this.seatRepository = seatRepository;
        this.seatTypeRepository = seatTypeRepository;
        this.seatMapper = seatMapper;
    }

    // creating seats
    @Override
    public SeatResponse createSeat(SeatRequest requestDTO) {

        SeatType seatType = seatTypeRepository.findById(requestDTO.getSeatTypeId())
                .orElseThrow(() -> new RuntimeException("SeatType not found"));

        Seat seat = new Seat();
        seat.setSeatCode(requestDTO.getSeatCode());
        seat.setStatus(requestDTO.getStatus().toUpperCase());
        seat.setSeatType(seatType);

        Seat savedSeat = seatRepository.save(seat);

        return seatMapper.toResponseDTO(seat);
    }


    // getting all seats
    @Override
    public List<SeatResponse> getAllSeats() {
        return seatRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    // getting by id

    @Override
    public SeatResponse getSeatById(Integer id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        return seatMapper.toResponseDTO(seat);
    }

    // updating seat
    @Override
    public SeatResponse updateSeat(Integer id, SeatRequest requestDTO) {

        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        SeatType seatType = seatTypeRepository.findById(requestDTO.getSeatTypeId())
                .orElseThrow(() -> new RuntimeException("SeatType not found"));

        seat.setSeatCode(requestDTO.getSeatCode());
        seat.setStatus(requestDTO.getStatus().toUpperCase());
        seat.setSeatType(seatType);

        Seat updatedSeat = seatRepository.save(seat);

        return seatMapper.toResponseDTO(seat);
    }


    // deleting seat
    @Override
    public void deleteSeat(Integer id) {
        if (!seatRepository.existsById(id)) {
            throw new RuntimeException("Seat not found");
        }
        seatRepository.deleteById(id);
    }


    // available seats
    @Override
    public List<SeatResponse> getAvailableSeats() {
        return seatRepository.findByStatus("AVAILABLE")
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    // searching by status
    @Override
    public List<SeatResponse> getSeatsByStatus(String status) {
        return seatRepository.findByStatus(status.toUpperCase())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


}
