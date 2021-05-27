package pl.sdacademy.model.dao;
// pola w bazie danych: id, country, nazwa miasta w jezyku polskim, (langs -> pl), jeśli nie ma to pierwsza pozycja z langs




import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "cityapi")
public class WeatherAPIDatabase {




    private String country;

    @Column(name = "city")
    private String name;

    @Id
    @Column(name = "city_id")
    private Integer id;


    @Transient
    public List<Map<String, String>> langs;


    public WeatherAPIDatabase(String country, String name, int id) {
        this.country = country;
        this.name = name;
        this.id = id;
    }

    public WeatherAPIDatabase() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Map<String, String>> getLangs() {
        return langs;
    }

    public void setLangs(List<Map<String, String>> langs) {
        this.langs = langs;
    }

    @Override
    public String toString() {
        return  country + ", "
                + name + ", " + id;
    }
}
