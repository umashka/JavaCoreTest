package Lesson6.HomeWork;

import Lesson8.project.ApplicationGlobalState;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import okhttp3.Response;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeatherResponse {
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECAST = "forecasts";
    private static final String API_VERSION = "v1";
    private static final String FORECAST_TYPE = "daily";
    private static final String FORECAST_PERIOD_5 = "5day";
    private static final String FORECAST_PERIOD_1 = "1day";

    //private static final String SAINT_PETERSBURG_KEY = "474212_PC";

    // Получила свой ключ при регистрации на сайте
    private static final String API_KEY = "HVQ5fjRRMFqpGG9P3NwpEZGsoUDQIBJO";

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<WeatherData> getWeather(String city, String period) throws IOException {
        String cityKey = detectCityKey(city);
        List<WeatherData> result = null;
        if (period.equals("1")) {
            HttpUrl url = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment(FORECAST)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(FORECAST_TYPE)
                    .addPathSegment(FORECAST_PERIOD_1)
                    .addPathSegment(cityKey)
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("language", "ru-ru")
                    .addQueryParameter("metric", "true")
                    .build();

            Request request = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            //System.out.println(response.body().string());
            String jsonResponse = response.body().string();

            // Реализовать вывод погоды на 1 день в формате
            //| В городе CITY на дату DATE ожидается WEATHER_TEXT, температура - TEMPERATURE |

            String dateValue= objectMapper
                    .readTree(jsonResponse).at("/Headline/EffectiveDate").asText().split("T")[0];
            String textValue = objectMapper
                    .readTree(jsonResponse).at("/DailyForecasts").get(0).at("/Day/IconPhrase").asText();
            JsonNode tempValue = objectMapper
                    .readTree(jsonResponse).at("/DailyForecasts").get(0).at("/Temperature/Maximum/Value");

            //System.out.println("В городе " + city + " на дату " + dateValue +
            //        " ожидается " + textValue + ", температура: " + tempValue);
            result = new ArrayList<WeatherData>();
            result.add(new WeatherData(
                    city,
                    dateValue,
                    textValue,
                    tempValue.asDouble()));


        }
        if (period.equals("5")) {
            HttpUrl url = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment(FORECAST)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(FORECAST_TYPE)
                    .addPathSegment(FORECAST_PERIOD_5)
                    .addPathSegment(cityKey)
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("language", "ru-ru")
                    .addQueryParameter("metric", "true")
                    .build();

            Request request = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            //System.out.println(response.body().string());
            String jsonResponse = response.body().string();


            // Реализовать вывод погоды на следующие 5 дней в формате
            //| В городе CITY на дату DATE ожидается WEATHER_TEXT, температура - TEMPERATURE |

            String dateValue= objectMapper
                    .readTree(jsonResponse).at("/Headline/EffectiveDate").asText().split("T")[0];
            String textValue = objectMapper
                    .readTree(jsonResponse).at("/DailyForecasts").get(0).at("/Day/IconPhrase").asText();
            JsonNode tempValue = objectMapper
                    .readTree(jsonResponse).at("/DailyForecasts").get(0).at("/Temperature/Maximum/Value");

            String[] dateValues = new String[5];
            String[] textValues = new String[5];
            String[] tempValues = new String[5];

            result = new ArrayList<WeatherData>();

            for (int i = 0; i < 5; i++){
                dateValues[i] = objectMapper.readTree(jsonResponse)
                        .at("/DailyForecasts").get(i).at("/Date").asText().split("T")[0];
                textValues[i] = objectMapper.readTree(jsonResponse)
                        .at("/DailyForecasts").get(i).at("/Day/IconPhrase").asText();
                tempValues[i] = objectMapper.readTree(jsonResponse)
                        .at("/DailyForecasts").get(i).at("/Temperature/Maximum/Value").asText();
                result.add(new WeatherData(
                        city,
                        dateValue,
                        textValue,
                        tempValue.asDouble()));
            }
        }
        return result;
    }

    public String detectCityKey(String city) throws IOException {
        HttpUrl detectLocationURL = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment("locations")
                .addPathSegment(API_VERSION)
                .addPathSegment("cities")
                .addPathSegment("autocomplete")
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", city)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(detectLocationURL)
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Невозможно прочесть информацию о городе. " +
                    "Код ответа сервера = " + response.code() + " тело ответа = " + response.body().string());
        }
        String jsonResponse = response.body().string();
        //System.out.println("Произвожу поиск города " + city);

        if (objectMapper.readTree(jsonResponse).size() > 0) {
            String cityName = objectMapper.readTree(jsonResponse).get(0).at("/LocalizedName").asText();
            String countryName = objectMapper.readTree(jsonResponse).get(0).at("/Country/LocalizedName").asText();
            System.out.println("Найден город " + cityName + " в стране " + countryName);
        } else throw new IOException("Server returns 0 cities");

        return objectMapper.readTree(jsonResponse).get(0).at("/Key").asText();
    }
}
