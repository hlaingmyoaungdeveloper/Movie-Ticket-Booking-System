package com.soft.movie_ticket_booking_system.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soft.movie_ticket_booking_system.dto.request.SeatTypeRequest;
import com.soft.movie_ticket_booking_system.dto.response.PageResponse;
import com.soft.movie_ticket_booking_system.dto.response.SeatTypeResponse;
import com.soft.movie_ticket_booking_system.exception.ResourceNotFoundException;
import com.soft.movie_ticket_booking_system.mapper.SeatTypeMapper;
import com.soft.movie_ticket_booking_system.model.SeatType;
import com.soft.movie_ticket_booking_system.repository.SeatTypeRepository;
import com.soft.movie_ticket_booking_system.service.SeatTypeService;
import com.soft.movie_ticket_booking_system.utils.PaginationValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatTypeServiceImpl implements SeatTypeService {

    private static final Set<String> SEAT_TYPE_SORT_FIELDS = Set.of("seatTypeId", "seatTypeName", "price");

    private final SeatTypeRepository seatTypeRepository;

    @Override
    @Transactional
    public SeatTypeResponse createSeatType(SeatTypeRequest request) {
        SeatType seatType = SeatTypeMapper.toSeatType(request);
        SeatType savedSeatType = seatTypeRepository.save(seatType);
        return SeatTypeMapper.toSeatTypeDto(savedSeatType);
    }

    @Override
    public PageResponse<SeatTypeResponse> getAllSeatTypes(int page, int size, String sortBy, String direction) {
        PaginationValidator.validate(page, size, sortBy, direction, SEAT_TYPE_SORT_FIELDS);

        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<SeatType> seatTypePage = seatTypeRepository.findAll(pageable);
        List<SeatTypeResponse> data = seatTypePage.getContent().stream().map(SeatTypeMapper::toSeatTypeDto).toList();

        return new PageResponse<>(
                data,
                seatTypePage.getNumber(),
                seatTypePage.getSize(),
                seatTypePage.getTotalElements(),
                seatTypePage.getTotalPages());
    }

    @Override
    public SeatTypeResponse findBySeatTypeId(Integer seatTypeId) {
        SeatType seatType = seatTypeRepository.findById(seatTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("SeatType not found with id: " + seatTypeId));
        return SeatTypeMapper.toSeatTypeDto(seatType);
    }

    @Override
    public List<SeatTypeResponse> findBySeatTypeName(String seatTypeName) {
        return seatTypeRepository.findBySeatTypeNameContainingIgnoreCase(seatTypeName).stream()
                .map(SeatTypeMapper::toSeatTypeDto)
                .toList();
    }

    @Override
    @Transactional
    public SeatTypeResponse updateSeatType(Integer seatTypeId, SeatTypeRequest request) {
        SeatType seatType = seatTypeRepository.findById(seatTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("SeatType not found with id: " + seatTypeId));

        seatType.setSeatTypeName(request.getSeatTypeName());
        seatType.setPrice(request.getPrice());

        SeatType updatedSeatType = seatTypeRepository.save(seatType);
        return SeatTypeMapper.toSeatTypeDto(updatedSeatType);
    }

    @Override
    @Transactional
    public void deleteSeatType(Integer seatTypeId) {
        SeatType seatType = seatTypeRepository.findById(seatTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("SeatType not found with id: " + seatTypeId));
        seatTypeRepository.delete(seatType);
    }
}
