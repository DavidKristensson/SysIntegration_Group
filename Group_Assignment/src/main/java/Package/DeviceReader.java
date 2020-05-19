package Package;

import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.util.Scanner;

public class DeviceReader {

    public DeviceReader() {

    }

    public void readDataFromArduino() throws IOException, ClassNotFoundException {
        SerialPort ports[] = SerialPort.getCommPorts();
        System.out.println("Select a port: ");
        int i = 1;
        for (SerialPort port : ports) {
            System.out.println(i + ", " + port.getSystemPortName());
            i++;
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        System.out.println("You chose: " + choice);

        SerialPort port = ports[choice - 1];
        if (port.openPort()) {
            System.out.println("Succesfully opened port: " + port.getSystemPortName());
        }
        else {
            System.out.println("Could not open port....");
        }

        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

        Scanner data = new Scanner(port.getInputStream());

        DbManager dbManager = new DbManager();
        System.out.println("Test innan loopen :)");
        while (data.hasNext()) {
            Reading arduinoReading = new Reading();
            if (data.next().contains("Humidity:")) {
                String humidity = data.nextLine();
                arduinoReading.setHumidity(Double.parseDouble(humidity));
                System.out.println("humidity: "+humidity);
            }
            if (data.next().contains("Temperature:")) {
                String temperature = data.nextLine();
                arduinoReading.setTemperature(Double.parseDouble(temperature));
                System.out.println("temperature: "+temperature);
            }
            dbManager.insertDataBase(arduinoReading);
        }
        //System.out.println("Arduinoreading hum: "+arduinoReading.getHumidity()+"\nArd reading temp: "+arduinoReading.getTemperature());
    }
}