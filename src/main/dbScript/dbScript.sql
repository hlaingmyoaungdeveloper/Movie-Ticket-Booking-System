-- =========================
-- 1. MOVIES TABLE
-- =========================
CREATE TABLE movies (
    movie_id INT PRIMARY KEY AUTO_INCREMENT,
    movie_name VARCHAR(255),
    movie_description TEXT,
    movie_duration INT,
    movie_type VARCHAR(100),
    status VARCHAR(50),
    delete_flag BOOLEAN DEFAULT FALSE,
    created_by INT,
    created_time TIMESTAMP,
    modified_by INT,
    modified_time TIMESTAMP
);

-- =========================
-- 2. SHOWS TABLE
-- =========================
CREATE TABLE shows (
    show_id INT PRIMARY KEY AUTO_INCREMENT,
    movie_id INT,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    
    CONSTRAINT fk_shows_movie
        FOREIGN KEY (movie_id)
        REFERENCES movies(movie_id)
        ON DELETE CASCADE
);

-- =========================
-- 3. BOOKINGS TABLE
-- =========================
CREATE TABLE bookings (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(255),
    show_id INT,
    total_price DECIMAL(10,2),
    booking_time TIMESTAMP,
    
    CONSTRAINT fk_booking_show
        FOREIGN KEY (show_id)
        REFERENCES shows(show_id)
        ON DELETE CASCADE
);

-- =========================
-- 4. SEAT TYPES TABLE
-- =========================
CREATE TABLE seat_types (
    seat_type_id INT PRIMARY KEY AUTO_INCREMENT,
    seat_type_name VARCHAR(100),
    price DECIMAL(10,2)
);

-- =========================
-- 5. SEATS TABLE
-- =========================
CREATE TABLE seats (
    seat_id INT PRIMARY KEY AUTO_INCREMENT,
    seat_code VARCHAR(50),
    seat_type_id INT,
    status VARCHAR(50),

    CONSTRAINT fk_seat_type
        FOREIGN KEY (seat_type_id)
        REFERENCES seat_types(seat_type_id)
        ON DELETE CASCADE
);

-- =========================
-- 6. BOOKING_SEATS TABLE (Many-to-Many)
-- =========================
CREATE TABLE booking_seats (
    booking_id INT,
    seat_id INT,

    PRIMARY KEY (booking_id, seat_id),

    CONSTRAINT fk_bs_booking
        FOREIGN KEY (booking_id)
        REFERENCES bookings(booking_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_bs_seat
        FOREIGN KEY (seat_id)
        REFERENCES seats(seat_id)
        ON DELETE CASCADE
);