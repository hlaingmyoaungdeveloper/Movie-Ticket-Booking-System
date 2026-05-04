# Movie Ticket Booking System

A robust and scalable backend system for managing movie theater bookings, shows, and movie catalogs. Built with Spring Boot 3, MySQL, and Cloudinary for seamless media management.

## 🚀 Features

### 🎬 Movie Management
- **Full CRUD Operations**: Create, Read, Update, and Delete movies.
- **Advanced Search**: Filter movies by ID, name, duration, type, or status.
- **Pagination & Sorting**: Efficiently retrieve movie lists with customizable sorting (name, duration, etc.) and pagination.
- **Image Upload**: Integrated with **Cloudinary** for secure movie poster storage and retrieval.

### 📅 Show & Booking Management
- **Show Scheduling**: Manage movie showtimes with automatic duration tracking.
- **Seat Management**: Support for different seat types (VIP, Regular, etc.) and availability tracking.
- **Booking System**: Process customer bookings with multi-seat selection and real-time price calculation.

### 🛠 Technical Highlights
- **Layered Architecture**: Clear separation of concerns between Controllers, Services, Repositories, and Mappers.
- **Global Exception Handling**: Custom error responses for 404 Not Found, 415 Unsupported Media Type, and Validation errors.
- **Audit Tracking**: Automatic tracking of creation and modification times via `BaseEntity`.
- **Validation**: Strict input validation using Jakarta Validation annotations.

## 💻 Tech Stack
- **Framework**: Spring Boot 3.4.x
- **Database**: MySQL 8.x
- **Persistence**: Spring Data JPA / Hibernate
- **Image Storage**: Cloudinary
- **Build Tool**: Maven
- **Java Version**: 21
- **Utilities**: Lombok, MapStruct (via manual mapping)

## 🛠 Setup & Installation

### Prerequisites
- JDK 21
- MySQL Server
- Cloudinary Account (for image uploads)

### Configuration
Update the `src/main/resources/application.properties` with your credentials:

```properties
# Database
spring.datasource.url=jdbc:mysql://localhost:3306/movie_ticket_booking_system
spring.datasource.username=your_username
spring.datasource.password=your_password

# Cloudinary
cloudinary.cloud_name=your_cloud_name
cloudinary.api_key=your_api_key
cloudinary.api_secret=your_api_secret
```

### Running the App
```bash
mvn clean install
mvn spring-boot:run
```

## 🔌 API Endpoints (Quick Reference)

### Movies
- `POST /api/movies` - Create movie (Multipart form-data)
- `GET /api/movies` - Get all movies (Paginated)
- `GET /api/movies/{id}` - Get movie by ID
- `PUT /api/movies/{id}` - Update movie
- `DELETE /api/movies/{id}` - Soft/Hard delete movie

## 📸 Media Handling
This project uses `@RequestPart` for handling multipart requests. When adding a movie via Postman:
1. Set body to `form-data`.
2. Add a `request` part with `Content-Type: application/json`.
3. Add a `file` part with your image.

---
Developed as a high-performance backend solution for cinema management.
