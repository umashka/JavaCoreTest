package Lesson6.HomeWork;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Lesson6 {
    public static void main(String[] args) throws IOException {
        WeatherResponse weatherResponse = new WeatherResponse();

        // Для тестирования:
        //weatherResponse.getWeather("Moscow", "1");
        //weatherResponse.getWeather("Tokyo", "5");

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Enter city (in English) and time period (1 or 5). Type 'by' to exit");
            System.out.println("For example: 'Moscow 1' or 'Tokyo 5'");
            System.out.println("-- >");
            String scString = scanner.nextLine();
            if (scString.equals("by")) break;
            String[] scStrings = scString.split(" ");
            weatherResponse.getWeather(scStrings[0], scStrings[1]);
        }
    }
}
