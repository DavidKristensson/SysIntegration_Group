package Package;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DbManager {

    ArrayList<Reading> listOfReadings = new ArrayList<>();
    String lassesFilePath = "C:\\Users\\lasse\\GitProjects\\SysIntegration_Group\\Group_Assignment\\src\\main\\java\\Package\\settings.properties";
    String davidsFilePath = "C:\\Users\\User123\\GitProjects\\SysIntegration_Group\\Group_Assignment\\src\\main\\java\\Package\\settings.properties";

    public ArrayList<Reading> getReadingsFromDb() throws IOException, ClassNotFoundException{
        Properties p = new Properties();
        p.load(new FileInputStream(lassesFilePath));

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(
                     "select * from Readings")){

            ResultSet rs = stmt.executeQuery();

            listOfReadings.clear();
            while (rs.next()) {
                int id = rs.getInt("Id");
                double temperature = rs.getDouble("Temperature");
                double humidity = rs.getDouble("Humidity");
                Date createdDate = rs.getDate("Created");

                Reading reading = new Reading();
                reading.setId(id);
                reading.setTemperature(temperature);
                reading.setHumidity(humidity);
                reading.setCreatedDate(createdDate);
                listOfReadings.add(reading);

                System.out.println("Id: " + id);
                System.out.println("Temperature: " + temperature);
                System.out.println("Humidity: " + humidity);
                System.out.println("Created: " + createdDate);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfReadings;
    }

    public void insertDataBase(Reading readingFromArduino) throws IOException, ClassNotFoundException {
        Properties p = new Properties();
        p.load(new FileInputStream(lassesFilePath));

        Class.forName("com.mysql.cj.jdbc.Driver");
        ResultSet rs = null;


        double temperature = readingFromArduino.getTemperature();
        double humidity = readingFromArduino.getHumidity();


        try (
                Connection con = DriverManager.getConnection(
                        p.getProperty("connectionString"),
                        p.getProperty("username"),
                        p.getProperty("password"));
                PreparedStatement stmt = con.prepareStatement("INSERT INTO Readings (Temperature, Humidity) VALUES (?, ?)")) {
            stmt.setDouble(1, temperature);
            stmt.setDouble(2, humidity);
            stmt.executeUpdate();
            System.out.println("Successfully inserted data into database! :D");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
