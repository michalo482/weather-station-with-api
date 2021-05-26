package pl.sdacademy.model.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CurrentWeather {

    @JsonProperty("name")
    private String location;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
