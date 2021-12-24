package Lesson6.HomeWork;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// Репозиторий для работы
public interface DatabaseRepository { // контракт взаимодействия с данными в БД

    boolean saveWeatherData(WeatherData weatherData) throws SQLException; // сохраняет данные в БД

    List<WeatherData> getAllSavedData() throws IOException; // получает данные из БД
}
