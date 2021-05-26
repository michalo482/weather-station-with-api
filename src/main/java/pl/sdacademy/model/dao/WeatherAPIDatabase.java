package pl.sdacademy.model.dao;
// pola w bazie danych: id, country, nazwa miasta w jezyku polskim, (langs -> pl), jeśli nie ma to pierwsza pozycja z langs

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "table")
@Data
public class WeatherAPIDatabase {

    @Id
    @Column(name = "id")
    private Integer dbId;

    private String country;

    @Column(name = "city")
    private String name;

    @Column(name = "city_id")
    private String id;

    private List<Map<String, String>> langs;

}
