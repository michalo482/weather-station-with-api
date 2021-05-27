package pl.sdacademy.repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sdacademy.model.dto.CurrentWeather;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class WeatherClient {

    private String apiUrl;

    public WeatherClient() {
        this.apiUrl = "http://api.openweathermap.org";
    }

    WeatherClient(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public CurrentWeather downloadCurrentWeather(String parameter) {
        WeatherClientDatabase weatherClientDatabase = new WeatherClientDatabase();
        Optional<String> optionalId = weatherClientDatabase.searchForParameter(parameter);
        String finalParameter = optionalId
                .map(id -> String.format("id=%s", id))
                .orElse(String.format("q=%s", parameter));

        System.out.println("\n" + "Wysłano z parametrem: " + "\n" + finalParameter + "\n");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(apiUrl + "/data/2.5/weather?appid=1bf2280610892b23825a629aeb4cddc0&lang=pl&units=metric&" + finalParameter))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            ObjectMapper mapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            CurrentWeather result = mapper.readValue(json, CurrentWeather.class);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CurrentWeather downloadCurrentWeather(Integer id) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(apiUrl + "/data/2.5/weather?appid=1bf2280610892b23825a629aeb4cddc0&lang=pl&units=metric&id=" + id))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            ObjectMapper mapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            CurrentWeather result = mapper.readValue(json, CurrentWeather.class);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
