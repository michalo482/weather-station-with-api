package pl.sdacademy.model.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "cityapi")
@Data
public class WeatherAPIDatabase {

    private String country;

    @Column(name = "city")
    private String name;

    @Id
    @Column(name = "city_id")
    private Integer id;

    @Transient
    public List<Map<String, String>> langs;

}
