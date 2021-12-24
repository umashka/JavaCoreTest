package Lesson6.HomeWork;


import java.io.IOException;
import java.sql.*;
import java.util.List;

public class DatabaseRepositorySQLiteImpl implements DatabaseRepository {
    // блок иннициализации драйвера БД. если не выполняется, то ничего не должно дальше работать
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    String filename = null;
    String createTableQuery = "CREATE TABLE IF NOT EXISTS weather (\n" +
        "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
        "city TEXT NOT NULL,\n" +
        "date_time TEXT NOT NULL,\n" +
        "weather_text TEXT NOT NULL,\n" +
        "temperature REAL NOT NULL,\n" +
        ");";

    // Значение конкретных параметров будем указывать в дальнейшем в коде
    String insertWeatherQuery = "INSERT INTO weather (city, date_time, weather_text, temperature) VALUES (?,?,?,?)";
    String selectWeatherQuery = "SELECT * FROM weather";

    public DatabaseRepositorySQLiteImpl() {
        filename = "application.db";
    }

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + filename);
        return connection;
    }

    private void createTableIfNotExists() {
        try (Connection connection = getConnection()) {
            connection.createStatement().execute(createTableQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean saveWeatherData(WeatherData weatherData) throws SQLException {
        createTableIfNotExists();
        try (Connection connection = getConnection();
        PreparedStatement saveWeather = connection.prepareStatement(insertWeatherQuery)) {
            saveWeather.setString(1, weatherData.getCity());
            saveWeather.setString(2, weatherData.getLocalDate());
            saveWeather.setString(3, weatherData.getText());
            saveWeather.setDouble(4, weatherData.getTemperature());
            return saveWeather.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        throw new SQLException("Failure on saving weather object");
    }

    @Override
    public List<WeatherData> getAllSavedData() throws IOException {
        List<WeatherData> result = null;
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(selectWeatherQuery);
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString("City") + " | " +
                                resultSet.getString("Date") + " | " +
                                resultSet.getString("Text") + " | " +
                                resultSet.getDouble("Temp") + " | "
                );
            }

            //return saveWeather.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
        //throw new SQLException("Failure on saving weather object");
    }
}
