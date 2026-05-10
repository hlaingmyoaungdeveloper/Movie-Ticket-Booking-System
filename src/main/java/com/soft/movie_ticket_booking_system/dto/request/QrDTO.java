package com.soft.movie_ticket_booking_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QrDTO {

    @NotBlank(message = "QR code is required")
    private String qrCode;
}
