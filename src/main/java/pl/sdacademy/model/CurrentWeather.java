package pl.sdacademy.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CurrentWeather {

    @JsonProperty("name")
    private String city;

    @JsonProperty("weather")
    private List<Weather> weatherList;

    @JsonProperty("main")
    private Temperature temperature;

    private Wind wind;


    public CurrentWeather() {
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
