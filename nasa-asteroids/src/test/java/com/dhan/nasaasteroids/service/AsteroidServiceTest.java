package com.dhan.nasaasteroids.service;

import com.dhan.nasaasteroids.controller.AsteroidController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;


@ExtendWith(MockitoExtension.class)
class AsteroidServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(AsteroidServiceTest.class);

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AsteroidService asteroidService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void TestGetAsteroidsForLandingPage() {

    }

    @Test
    void testGetAsteroidDetails() {
    }
}