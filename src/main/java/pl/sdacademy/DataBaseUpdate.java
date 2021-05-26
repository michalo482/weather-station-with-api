package pl.sdacademy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sdacademy.model.dao.WeatherAPIDatabase;
import pl.sdacademy.utils.ReadFileToStringUtils;

public class DataBaseUpdate {
    public static void main(String[] args) {
        String json = ReadFileToStringUtils.getFileFromResourceAsString("current.city.list.json");
        WeatherAPIDatabase[] weatherAPIDatabases = update(json);
        for (WeatherAPIDatabase weatherAPIDatabase : weatherAPIDatabases) {
            System.out.println(weatherAPIDatabase);
        }
        System.out.println("Baza danych zostałą zaktualizowana");
    }

    private static WeatherAPIDatabase[] update(String json) {

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

}
