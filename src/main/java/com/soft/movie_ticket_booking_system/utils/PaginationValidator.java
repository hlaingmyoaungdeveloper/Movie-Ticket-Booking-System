package com.soft.movie_ticket_booking_system.utils;

import java.util.Set;

import com.soft.movie_ticket_booking_system.exception.BusinessValidationException;

public final class PaginationValidator {
    private PaginationValidator() {
    }

    public static void validate(int page, int size, String sortBy, String direction, Set<String> allowedSortFields) {
        if (page < 0) {
            throw new BusinessValidationException("Page must be greater than or equal to 0");
        }
        if (size <= 0 || size > 100) {
            throw new BusinessValidationException("Size must be between 1 and 100");
        }
        if (!"asc".equalsIgnoreCase(direction) && !"desc".equalsIgnoreCase(direction)) {
            throw new BusinessValidationException("Direction must be either 'asc' or 'desc'");
        }
        if (!allowedSortFields.contains(sortBy)) {
            throw new BusinessValidationException("Invalid sortBy field: " + sortBy);
        }
    }
}
