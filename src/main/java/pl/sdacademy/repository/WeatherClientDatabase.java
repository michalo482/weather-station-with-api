package pl.sdacademy.repository;


import pl.sdacademy.model.dto.WeatherAPIDatabase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class WeatherClientDatabase {

    public Optional<String> searchForParameter(String parameter) {

        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("weatherstationwithapiPU");
        final EntityManager em = emf.createEntityManager();

        try {
            final TypedQuery<WeatherAPIDatabase> weatherQuery = em.createQuery(
                    "SELECT wad FROM WeatherAPIDatabase wad WHERE wad.name = :parameter1 OR wad.id = :parameter2", WeatherAPIDatabase.class
            );
            weatherQuery.setParameter("parameter1", parameter);
            weatherQuery.setParameter("parameter2", parameter);

            Optional<String> result = weatherQuery.getResultList().stream()
                    .map(WeatherAPIDatabase::getId)
                    .findFirst();
            return result;
        } finally {
            emf.close();
        }
    }
}
