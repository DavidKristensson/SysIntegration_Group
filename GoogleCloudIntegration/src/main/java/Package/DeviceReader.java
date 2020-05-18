package Package;

import com.fazecast.jSerialComm.SerialPort;

import java.util.Scanner;

public class DeviceReader {

    public DeviceReader() {

    }

    public Reading readDataFromArduino() {
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
        Reading arduinoReading = new Reading();
        while (data.hasNext()) {
            if (data.next().contains("Humidity:")) {
                String humidity = data.nextLine();
                arduinoReading.setHumidity(Double.parseDouble(humidity));
            }
            if (data.next().contains("Temperature:")) {
                String temperature = data.nextLine();
                arduinoReading.setTemperature(Double.parseDouble(temperature));
            }
        }
        return arduinoReading;
    }
}