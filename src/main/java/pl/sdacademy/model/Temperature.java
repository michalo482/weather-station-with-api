package pl.sdacademy.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature {

    @JsonProperty("temp")
    private Integer temperature;

    public Integer getTemperatureValue() {
        return temperature;
    }

    public void setTemperatureValue(Integer temperature) {
        this.temperature = temperature;
    }

}
