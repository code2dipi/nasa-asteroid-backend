package com.dhan.nasaasteroids.service;

import com.dhan.nasaasteroids.controller.AsteroidController;
import com.dhan.nasaasteroids.exception.NotFoundException;
import com.dhan.nasaasteroids.exception.UnauthorizedException;
import com.dhan.nasaasteroids.model.NeoObject;
import com.dhan.nasaasteroids.model.NeoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AsteroidService {
    private static final Logger logger = LoggerFactory.getLogger(AsteroidService.class);
    private static final String LANDING_PAGE_CACHE = "landingPageCache";
    private static final String ASTEROID_DETAILS_CACHE = "asteroidDetailsCache";


    @Value("${nasa.api.url-neo-feed}")
    private String nasaApiUrl;

    @Value("${nasa.api.url.neo-lookup}")
    private String nasaApiUrlForAsteroid;

    private final RestTemplate restTemplate;

    public AsteroidService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = LANDING_PAGE_CACHE, key = "'landingPage-' + #startDate + '-' + #endDate", unless = "#result == null")

    public List<NeoObject> getAsteroidsForLandingPage(LocalDate startDate, LocalDate endDate, String apiKey) {
        validateApiKey(apiKey);

        // Handle scenarios where only start date or end date is provided
        if (startDate == null && endDate != null) {
            startDate = endDate.minusDays(7); // Assuming a default range of 7 days
        } else if (startDate != null && endDate == null) {
            endDate = startDate.plusDays(7);
        } else {
            validateDate(startDate, "start_date");
            validateDate(endDate, "end_date");
            validateDateRange(startDate, endDate);

        }


        String url = buildApiUrl(startDate, endDate, apiKey);
        NeoResponse neoResponse = restTemplate.getForObject(url, NeoResponse.class);
        logger.info("URL: {}", url);
        logger.info("Response: {}", neoResponse);

        if (neoResponse == null || neoResponse.getNearEarthObjects() == null) {
            throw new NotFoundException("No asteroids found for the specified date range");
        }

        sortAsteroidsByMissDistance(neoResponse.getNearEarthObjects());

        // Collect sorted asteroids into a single list
        List<NeoObject> sortedAsteroids = neoResponse.getNearEarthObjects().values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return sortedAsteroids;
    }

    @Cacheable(value = ASTEROID_DETAILS_CACHE, key = "'asteroidDetails-' + #asteroidId", unless = "#result == null")
    public NeoObject getAsteroidDetails(String asteroidId, String apiKey) {
        validateApiKey(apiKey);

        String url = buildApiUrlForAsteroid(asteroidId, apiKey);
        NeoObject asteroid = restTemplate.getForObject(url, NeoObject.class);

        if (asteroid == null) {
            throw new NotFoundException("Asteroid not found");
        }

        return asteroid;
    }

    private void validateApiKey(String apiKey) {
        if (!isValidApiKey(apiKey)) {
            throw new UnauthorizedException("Invalid API key");
        }
    }

    private void validateDate(LocalDate date, String paramName) {
        if (date == null) {
            throw new IllegalArgumentException(paramName + " cannot be null");
        }
    }

    private void validateDateRange(LocalDate startDate, LocalDate endDate) {
        if (endDate.isAfter(startDate.plusDays(7))) {
            throw new IllegalArgumentException("Date range cannot be greater than 7 days");
        }
    }

    private boolean isValidApiKey(String apiKey) {
        return apiKey != null && !apiKey.trim().isEmpty();
    }

    private String buildApiUrl(LocalDate startDate, LocalDate endDate, String apiKey) {
        return String.format("%s?start_date=%s&end_date=%s&api_key=%s", nasaApiUrl, startDate, endDate, apiKey);
    }

    private String buildApiUrlForAsteroid(String asteroidId, String apiKey) {
        return String.format("%s/%s?api_key=%s", nasaApiUrlForAsteroid, asteroidId, apiKey);
    }


    private Map<String, List<NeoObject>> sortAsteroidsByMissDistance(Map<String, List<NeoObject>> nearEarthObjects) {
        nearEarthObjects.forEach((date, asteroids) ->
                asteroids.sort(Comparator.comparingDouble(neoObject ->
                        Double.parseDouble(neoObject.getCloseApproachData().get(0).getMissDistance().getAstronomical()))));

        return nearEarthObjects;
    }
}