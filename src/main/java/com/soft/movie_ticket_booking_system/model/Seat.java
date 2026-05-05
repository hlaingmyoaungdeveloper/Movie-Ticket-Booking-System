package com.soft.movie_ticket_booking_system.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "seats")
@Getter
@Setter
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Integer seatId;

    @NotBlank(message = "Seat code is required")
    @Size(max = 50, message = "Seat code must be at most 50 characters")
    @Column(name = "seat_code")
    private String seatCode;

    @NotNull(message = "Seat type is required")
    @ManyToOne
    @JoinColumn(name = "seat_type_id")
    private SeatType seatType;

    @NotBlank(message = "Status is required")
    @Pattern(
            regexp = "AVAILABLE|BOOKED|RESERVED",
            message = "Invalid status"
    )
    @Column(name = "status", nullable = false)
    private String status;


    @ManyToMany(mappedBy = "seats")
    private Set<Booking> bookings = new HashSet<>();
}
