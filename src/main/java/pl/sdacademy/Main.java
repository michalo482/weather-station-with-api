package pl.sdacademy;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sdacademy.service.WeatherService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WeatherService weatherService = new WeatherService();
        System.out.println("Podaj nazwę miasta: ");
        String city = scanner.nextLine();
        System.out.println(weatherService.widgetText(city) + "\n");
//        System.out.println(weatherService.widgetText("Warszawa") + "\n");
//        System.out.println(weatherService.widgetText("Katowice") + "\n");
        System.out.println("Koniec");
    }
}
