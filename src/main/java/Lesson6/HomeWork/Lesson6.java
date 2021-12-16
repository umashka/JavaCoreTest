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
            System.out.println("Введите город (на англ.) и количество дней (1 or 5). Введите 'by' для выхода");
            System.out.println("Например: 'Moscow 1' или 'Tokyo 5'");
            System.out.print("-- > ");
            String scString = scanner.nextLine();
            if (scString.equals("by")) break;
            String[] scStrings = scString.split(" ");
            weatherResponse.getWeather(scStrings[0], scStrings[1]);
            System.out.println("***");
        }

        // Выведено на консоль:
        // Введите город (на англ.) и количество дней (1 or 5). Введите 'by' для выхода
        // Например: 'Moscow 1' или 'Tokyo 5'
        // -- > Glasgow 1
        // Найден город Glasgow в стране United Kingdom
        // В городе Glasgow на дату 2021-12-18 ожидается Облачно, температура: 9.7
        // ***
        // Введите город (на англ.) и количество дней (1 or 5). Введите 'by' для выхода
        // Например: 'Moscow 1' или 'Tokyo 5'
        // -- > Toronto 5
        // Найден город Toronto в стране Canada
        // В городе Toronto на дату 2021-12-15 ожидается Дождь, температура: 7.2
        // В городе Toronto на дату 2021-12-16 ожидается Преимущественно облачно с ливнями, температура: 13.0
        // В городе Toronto на дату 2021-12-17 ожидается Преимущественно ясно, температура: 4.4
        // В городе Toronto на дату 2021-12-18 ожидается Снег, температура: 1.1
        // В городе Toronto на дату 2021-12-19 ожидается Небольшая облачность, температура: 0.0
        // ***
        // Введите город (на англ.) и количество дней (1 or 5). Введите 'by' для выхода
        // Например: 'Moscow 1' или 'Tokyo 5'
        // -- > Dubna 1
        // Найден город Dubna в стране Russia
        // В городе Dubna на дату 2021-12-18 ожидается Ливни, температура: 2.2
        // ***
        // Введите город (на англ.) и количество дней (1 or 5). Введите 'by' для выхода
        // Например: 'Moscow 1' или 'Tokyo 5'
        // -- > Samoa 1
        // Найден город Samoa в стране Ghana
        // В городе Samoa на дату 2021-12-16 ожидается Небольшая облачность, температура: 36.4
        // ***
        // Введите город (на англ.) и количество дней (1 or 5). Введите 'by' для выхода
        // Например: 'Moscow 1' или 'Tokyo 5'
        // -- > Mexico 5
        // Найден город Mexico City в стране Mexico
        // В городе Mexico на дату 2021-12-15 ожидается Солнечно, температура: 23.5
        // В городе Mexico на дату 2021-12-16 ожидается Преимущественно ясно, температура: 23.5
        // В городе Mexico на дату 2021-12-17 ожидается Преимущественно ясно, температура: 23.4
        // В городе Mexico на дату 2021-12-18 ожидается Переменная облачность, температура: 23.2
        // В городе Mexico на дату 2021-12-19 ожидается Переменная облачность, температура: 22.3
        // ***
        // Введите город (на англ.) и количество дней (1 or 5). Введите 'by' для выхода
        // Например: 'Moscow 1' или 'Tokyo 5'
        // -- > by
    }
}
