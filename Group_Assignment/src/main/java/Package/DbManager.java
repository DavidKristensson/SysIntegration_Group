package Package;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class DbManager {

    ArrayList<Reading> listOfReadings = new ArrayList<>();

    public ArrayList<Reading> getListOfHistoricalReadings() throws IOException, ClassNotFoundException, SQLException {
            listOfReadings.clear();
            Properties p = new Properties();
            p.load(new FileInputStream("C:\\Users\\lasse\\GitProjects\\SysIntegration_Group\\Group_Assignment\\src\\main\\java\\Package\\settings.properties"));

            Class.forName("com.mysql.cj.jdbc.Driver");


            try (Connection con = DriverManager.getConnection(
                    p.getProperty("connectionString"),
                    p.getProperty("username"),
                    p.getProperty("password"));
                 PreparedStatement stmt = con.prepareStatement(
                         "select * from Readings")){

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int readingId = rs.getInt("Id");
                    double temperature = rs.getDouble("Temperature");
                    double humidity = rs.getDouble("Humidity");
                    Date created = rs.getDate("Created");

                    Reading newReading = new Reading();
                    newReading.setId(readingId);
                    newReading.setTemperature(temperature);
                    newReading.setHumidity(humidity);
                    newReading.setCreated(created);
                    listOfReadings.add(newReading);

                    System.out.println("Temperature: " + temperature);
                    System.out.println("Humidity: " + humidity);
                    System.out.println("Created: " + created);
                }
            }

            catch (SQLException e) {
                e.printStackTrace();
            }
            return listOfReadings;
    }

    public void insertDataBase(Reading readingFromArduino) throws IOException, ClassNotFoundException {
        Properties p = new Properties();
        p.load(new FileInputStream("C:\\Users\\lasse\\GitProjects\\SysIntegration_Group\\Group_Assignment\\src\\main\\java\\Package\\settings.properties"));

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
