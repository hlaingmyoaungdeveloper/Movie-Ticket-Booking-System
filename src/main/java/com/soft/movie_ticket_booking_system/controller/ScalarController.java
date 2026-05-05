package com.soft.movie_ticket_booking_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScalarController {

        @GetMapping("/scalar")
        public String scalar() {
                return "forward:/scalar.html";
        }

        @GetMapping("/docs")
        public String docs() {
                return "redirect:/scalar";
        }
}