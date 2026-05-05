package com.soft.movie_ticket_booking_system.service;

import java.io.IOException;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    Map uploadFile(MultipartFile file) throws IOException;
    void deleteFile(String publicId) throws IOException;
}
