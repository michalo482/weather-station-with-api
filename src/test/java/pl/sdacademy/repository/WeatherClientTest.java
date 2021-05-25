package pl.sdacademy.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.model.CurrentWeather;

import static org.junit.jupiter.api.Assertions.*;

class WeatherClientTest {

    private WeatherClient weatherClient;

    @BeforeEach
    void setUp() {
        weatherClient = new WeatherClient();
    }

    @Test
    void shouldParseJsonResponse() {
        //GIVEN
        //WHEN
        CurrentWeather currentWeather = weatherClient.downloadCurrentWeather("Warszawa");
        //THEN
        assertEquals("Warszawa", currentWeather.getCity());

    }

}