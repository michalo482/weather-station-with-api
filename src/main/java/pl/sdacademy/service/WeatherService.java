package pl.sdacademy.service;

import pl.sdacademy.model.CurrentWeather;
import pl.sdacademy.repository.WeatherClient;

public class WeatherService {

    private WeatherClient weatherClient;

    public WeatherService() {
        this.weatherClient = new WeatherClient();
    }

    public String widgetText(String city) {
        CurrentWeather currentWeather = weatherClient.downloadCurrentWeather(city);
        StringBuilder sb = new StringBuilder();
        sb.append("Aktualna pogoda dla: ")
                .append(currentWeather.getCity())
                .append("\n")
                .append("Stan pogody: ")
                .append(currentWeather.getWeatherList().get(0).getGroupOfWeather())
                .append(", ")
                .append(currentWeather.getWeatherList().get(0).getDescription())
                .append("\n")
                .append("Temperatura: ")
                .append(currentWeather.getTemperature().getTemperatureValue())
                .append(" \u2103")
                .append(" ")
                .append("\n")
                .append("Prędkość wiatru: ")
                .append(currentWeather.getWind().getSpeed())
                .append(" m/s");
        return sb.toString();
    }

}
