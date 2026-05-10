# Movie Ticket Booking System

A professional, scalable backend system for managing movie theater operations, built with **Spring Boot 3**, **MySQL**, and **Cloudinary**. This system provides a clean RESTful API for managing movies, showtimes, and seat bookings with a focus on code quality and robust error handling.

## 🚀 Key Features

### 🎬 Movie Management
- **Full RESTful CRUD**: Comprehensive endpoints for movie lifecycle management.
- **Dynamic Search**: High-performance searching by Name, Type, Status, Duration, Movie ID, and Delete Flag using `@PathVariable` routing.
- **Pagination & Sorting**: Built-in support for paginated results and dynamic sorting (e.g., sort by duration, name, or created date).
- **Cloud Media Integration**: Seamless integration with **Cloudinary** for high-performance image hosting and automatic optimization.

### 📅 Show Management
- **Show Scheduling**: Flexible scheduling of movie shows.
- **Show Retrieval**: Quick access to shows filtered by Show ID, Movie ID, Start Time, and End Time.
- **Pagination & Sorting**: Built-in pagination and sorting for show listings.

### 💺 Seat & Seat Type Management
- **Seat Management**: Full CRUD operations, search by Seat ID, Seat Code, and Seat Type ID.
- **Seat Type Management**: Full CRUD operations, search by Seat Type ID and Seat Type Name.
- **Pagination & Sorting**: Built-in pagination and sorting for both seats and seat types.

### 🎟️ Booking Management
- **Booking Operations**: Full CRUD for ticket bookings, search by Booking ID, Customer Name, and Show ID.
- **QR Code Generation**: Automatic QR code generation for each booking (PNG image).
- **Booking Verification**: Endpoint to check/verify bookings using QR code data.
- **Pagination & Sorting**: Built-in pagination and sorting for booking listings.

### 📚 API Documentation
- **OpenAPI/Swagger**: Auto-generated API documentation.
- **Scalar UI**: Clean, modern API reference interface available at `/scalar`.

### 🛠 Architecture & Quality
- **Standardized API Responses**: Every response follows a consistent `ApiResponse` wrapper, ensuring a predictable contract for frontend developers.
- **Centralized Media Service**: Decoupled media logic into a dedicated `CloudinaryService` for better maintainability.
- **Global Error Handling**: Robust exception management that converts system errors into user-friendly JSON messages.
- **Audit Tracking**: Automatic `created_time` and `modified_time` tracking via JPA auditing.
- **Clean DTO Pattern**: Clear separation between database entities and API data transfer objects.

## 💻 Tech Stack
- **Core**: Java 21, Spring Boot 3.4.x
- **Data**: Spring Data JPA, MySQL 8
- **Media**: Cloudinary API
- **QR Codes**: ZXing (Zebra Crossing)
- **API Docs**: OpenAPI/Swagger, Scalar UI
- **Tooling**: Maven, Lombok, Jakarta Validation

## 📜 API Contract

### Response Structure
All API responses follow this standard format:
```json
{
  "success": true,
  "message": "Operation successful",
  "data": { ... },
  "timestamp": "2026-05-05 14:30:00"
}
```

### Endpoints (Sample)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/movies` | Create movie (Multipart: `request` JSON + `file`) |
| `GET` | `/api/movies` | Get all movies with pagination & sorting |
| `GET` | `/api/movies/search/movie-name/{name}` | Search movies by name |
| `GET` | `/api/movies/search/movie-type/{type}` | Search movies by type |
| `POST` | `/api/shows` | Create a new showtime |
| `GET` | `/api/shows/search/movie-id/{movieId}` | Get all shows for a specific movie |
| `POST` | `/api/seats` | Create a new seat |
| `GET` | `/api/seats/search/seat-type-id/{seatTypeId}` | Get seats by seat type |
| `POST` | `/api/seat-types` | Create a new seat type |
| `POST` | `/api/bookings` | Create a new booking |
| `GET` | `/api/bookings/qr/{bookingId}` | Get QR code for booking (PNG) |
| `POST` | `/api/bookings/check` | Verify a booking using QR data |

## 🛠 Setup & Installation

1. **Clone the repository**
2. **Configure Environment**: Update `src/main/resources/application.properties` with your database and Cloudinary credentials.
3. **Build & Run**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## 📸 Media Upload Guide (Postman)
To create or update a movie with an image:
1. Set Body type to **form-data**.
2. Key `request`: Paste your JSON data. **Crucial**: Hover over the row, click the vertical dots (⋮), and set **Content-Type** to `application/json`.
3. Key `file`: Change type to **File** and select your image.

---
Developed with ❤️ as a high-performance cinema management solution.
