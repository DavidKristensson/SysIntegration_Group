package Package;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DbManager {
    public void dataBaseInteraction() throws IOException, ClassNotFoundException, SQLException {
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
                    int temperature = rs.getInt("Temperature");
                    int humidity = rs.getInt("Humidity");

                    System.out.println("Temperature: " + temperature);
                    System.out.println("Humidity: " + humidity);
                }
            }

            catch (SQLException e) {
                e.printStackTrace();
            }
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
