package pl.sdacademy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sdacademy.model.dao.WeatherAPIDatabase;
import pl.sdacademy.utils.ReadFileToStringUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;


public class DataBaseUpdate {
    public static void main(String[] args) {

        WeatherAPIDatabase[] weatherDb = mapper();
        System.out.println(Arrays.toString(weatherDb));

//        for (WeatherAPIDatabase weatherAPIDatabase : weatherAPIDatabases) {
//            System.out.println(weatherAPIDatabase);
//        }
        update();
        System.out.println("Baza danych zostałą zaktualizowana");
    }

    private static WeatherAPIDatabase[] mapper() {
        String fileFromResourceAsString = ReadFileToStringUtils.getFileFromResourceAsString("current.city.list.json");
        try {
            ObjectMapper mapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            WeatherAPIDatabase[] result = mapper.readValue(fileFromResourceAsString, WeatherAPIDatabase[].class);
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new WeatherAPIDatabase[0];
    }

    private static void update() {

        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("weatherstationwithapiPU");
        final EntityManager em = emf.createEntityManager();
        WeatherAPIDatabase[] weatherDb = mapper();

        try {
            em.getTransaction().begin();
            for (WeatherAPIDatabase weatherAPIDatabase : weatherDb) {
                String cityName;
                if (weatherAPIDatabase.getLangs() == null) {
                    cityName = weatherAPIDatabase.getName();
                } else {
                    cityName = weatherAPIDatabase.getLangs().stream()
                            .filter(f -> f.containsKey("pl"))
                            .map(f -> f.get("pl"))
                            .findFirst()
                            .orElse(weatherAPIDatabase.getName());
                }

                WeatherAPIDatabase cityToDb = new WeatherAPIDatabase();
                cityToDb.setCountry(weatherAPIDatabase.getCountry());
                cityToDb.setName(cityName);
                cityToDb.setId(weatherAPIDatabase.getId());


                em.persist(cityToDb);


            }
            em.getTransaction().commit();

        } finally {
            emf.close();
        }
    }


//            em.getTransaction().begin();
//            Stream.of(weatherDb)
//                    .map(weather -> new WeatherAPIDatabase(weather.getId(), weather.getCountry(), weather.getName()))
//                    .forEach(em::persist);
//            em.getTransaction().commit();
//
//
//            emf.close();

    }


