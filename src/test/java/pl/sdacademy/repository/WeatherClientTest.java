package pl.sdacademy.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;
import pl.sdacademy.model.CurrentWeather;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;


public class WeatherClientTest {

    private WeatherClient weatherClient;
    private static ClientAndServer mockServer;

    @BeforeEach
    void setUp() {
        weatherClient = new WeatherClient("http://localhost:8080");
    }

    @BeforeAll
    public static void startServer() {
        mockServer = startClientAndServer(8080);
    }

    @AfterAll
    public static void stopServer() {
        mockServer.stop();
    }

    @Test
    void shouldParseJsonResponse() {
        //GIVEN
        createResponseForWeatherApp();
        //WHEN
        CurrentWeather currentWeather = weatherClient.downloadCurrentWeather("Warszawa");
        //THEN
        assertEquals("Warszawa", currentWeather.getLocation());

    }

    private void createResponseForWeatherApp() {
        mockServer.when(
                request()
                        .withMethod("GET")
                        .withPath("/data/2.5/weather")
                        .withQueryStringParameter("appid,1bf2280610892b23825a629aeb4cddc0")
                        .withQueryStringParameter("lang","pl")
                        .withQueryStringParameter("units","metric")
                        .withQueryStringParameter("q","Warszawa")
        ).respond(
                response()
                        .withStatusCode(200)
                        .withHeader("Content-type", "application/json")
                        .withBody(jsonFromWarsaw)
                        .withDelay(TimeUnit.SECONDS,1)
        );
    }

    private String jsonFromWarsaw = "{\n" +
            "\"coord\": {\n" +
            "\"lon\": 21.0118,\n" +
            "\"lat\": 52.2298\n" +
            "},\n" +
            "\"weather\": [\n" +
            "{\n" +
            "\"id\": 801,\n" +
            "\"main\": \"Clouds\",\n" +
            "\"description\": \"lekkie zachmurzenie\",\n" +
            "\"icon\": \"02d\"\n" +
            "}\n" +
            "],\n" +
            "\"base\": \"stations\",\n" +
            "\"main\": {\n" +
            "\"temp\": 15.98,\n" +
            "\"feels_like\": 15.44,\n" +
            "\"temp_min\": 13.73,\n" +
            "\"temp_max\": 17.1,\n" +
            "\"pressure\": 1010,\n" +
            "\"humidity\": 69\n" +
            "},\n" +
            "\"visibility\": 10000,\n" +
            "\"wind\": {\n" +
            "\"speed\": 0.45,\n" +
            "\"deg\": 8,\n" +
            "\"gust\": 2.68\n" +
            "},\n" +
            "\"clouds\": {\n" +
            "\"all\": 20\n" +
            "},\n" +
            "\"dt\": 1621956904,\n" +
            "\"sys\": {\n" +
            "\"type\": 2,\n" +
            "\"id\": 2032856,\n" +
            "\"country\": \"PL\",\n" +
            "\"sunrise\": 1621909662,\n" +
            "\"sunset\": 1621967905\n" +
            "},\n" +
            "\"timezone\": 7200,\n" +
            "\"id\": 756135,\n" +
            "\"name\": \"Warszawa\",\n" +
            "\"cod\": 200\n" +
            "}";

}