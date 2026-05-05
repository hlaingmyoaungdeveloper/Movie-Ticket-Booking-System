# Movie Ticket Booking System

A professional, scalable backend system for managing movie theater operations, built with **Spring Boot 3**, **MySQL**, and **Cloudinary**. This system provides a clean RESTful API for managing movies, showtimes, and seat bookings with a focus on code quality and robust error handling.

## 🚀 Key Features

### 🎬 Movie Management
- **Full RESTful CRUD**: Comprehensive endpoints for movie lifecycle management.
- **Dynamic Search**: High-performance searching by Name, Type, Status, and more using `@PathVariable` routing.
- **Pagination & Sorting**: Built-in support for paginated results and dynamic sorting (e.g., sort by duration, name, or created date).
- **Cloud Media Integration**: Seamless integration with **Cloudinary** for high-performance image hosting and automatic optimization.

### 📅 Show & Booking Management
- **Show Scheduling**: Flexible scheduling of movie shows with built-in time validation (end time must be after start time).
- **Show Retrieval**: Quick access to shows filtered by specific movies or IDs.
- **Seat & Booking Core**: Solid foundation for seat management and customer booking flows.

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
- **Tooling**: Maven, Lombok, Jakarta Validation

## � API Contract

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
| `GET` | `/api/movies/search/movie-name/{name}` | Search movies by name |
| `POST` | `/api/shows` | Create a new showtime |
| `GET` | `/api/shows/movie/{movieId}` | Get all shows for a specific movie |

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
