package com.dhan.nasaasteroids.controller;

import com.dhan.nasaasteroids.exception.NotFoundException;
import com.dhan.nasaasteroids.exception.UnauthorizedException;
import com.dhan.nasaasteroids.model.NeoObject;
import com.dhan.nasaasteroids.service.AsteroidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/asteroids")
@CrossOrigin(origins = "http://localhost:3000")
public class AsteroidController {
    private static final Logger logger = LoggerFactory.getLogger(AsteroidController.class);

    @Autowired
    private AsteroidService asteroidService;

    @GetMapping("/landing")
    public ResponseEntity<List<NeoObject>> getAsteroidsForLandingPage(
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String apiKey) {
        logger.info("Received request for asteroids landing page. Start Date: {}, End Date: {}", startDate, endDate);
        try {
            LocalDate parsedStartDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
            LocalDate parsedEndDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

            List<NeoObject> asteroids = asteroidService.getAsteroidsForLandingPage(parsedStartDate, parsedEndDate, apiKey);

            return ResponseEntity.ok(asteroids);
        } catch (IllegalArgumentException e) {
            logger.error("Bad request for landing page. Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (UnauthorizedException e) {
            logger.error("Unauthorized request for landing page. Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping("/details/{asteroidId}")
    public ResponseEntity<NeoObject> getAsteroidDetails(
            @PathVariable String asteroidId,
            @RequestParam String apiKey) {
        try {
            NeoObject asteroid = asteroidService.getAsteroidDetails(asteroidId, apiKey);
            logger.info("Asteroid detials: " + asteroid);

            return ResponseEntity.ok(asteroid);
        } catch (NotFoundException e) {
            logger.error("Asteroid not found. Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (UnauthorizedException e) {
            logger.error("Unauthorized request for asteroid details. Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}