package pl.sdacademy.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {

    @JsonProperty("main")
    private String groupOfWeather;

    private String description;

    public String getGroupOfWeather() {
        return groupOfWeather;
    }

    public void setGroupOfWeather(String groupOfWeather) {
        this.groupOfWeather = groupOfWeather;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
