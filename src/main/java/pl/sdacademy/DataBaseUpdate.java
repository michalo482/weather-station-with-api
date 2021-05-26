package pl.sdacademy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sdacademy.model.dao.WeatherAPIDatabase;
import pl.sdacademy.utils.ReadFileToStringUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataBaseUpdate {
    public static void main(String[] args) {
        String json = ReadFileToStringUtils.getFileFromResourceAsString("current.city.list.json");
//        WeatherAPIDatabase[] weatherAPIDatabases = mapper(json);
//        for (WeatherAPIDatabase weatherAPIDatabase : weatherAPIDatabases) {
//            System.out.println(weatherAPIDatabase);
//        }
        update(json);
        System.out.println("Baza danych zostałą zaktualizowana");
    }

    private static WeatherAPIDatabase[] mapper(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            WeatherAPIDatabase[] result;
            result = mapper.readValue(json, WeatherAPIDatabase[].class);
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new WeatherAPIDatabase[0];
    }

    private static boolean update(String json) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("weatherstationwithapiPU");
        final EntityManager em = emf.createEntityManager();
        boolean result = false;
        WeatherAPIDatabase[] weatherAPIDatabases = mapper(json);

        try {
            em.getTransaction().begin();
            for (WeatherAPIDatabase weatherAPIDatabase : weatherAPIDatabases) {
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
                cityToDb.setName(cityName);
                cityToDb.setCountry(weatherAPIDatabase.getCountry());
                cityToDb.setId(weatherAPIDatabase.getId());

                em.persist(cityToDb);
                result = true;

            }
            em.getTransaction().commit();
            return result;
        } finally {
            emf.close();
        }
    }

}
