package REST_service_Model;

import com.fazecast.jSerialComm.SerialPort;

import java.util.Scanner;

public class ReadDataFromArduino {

    public ReadDataFromArduino(){

    }

    public void readDataFunction(){
        SerialPort ports[] = SerialPort.getCommPorts();
        System.out.println("Select a port: ");
        int i = 1;
        for(SerialPort port : ports){
            System.out.println(i + ", " + port.getSystemPortName());
            i++;
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        System.out.println("You chose: "+choice);

        SerialPort port = ports[choice - 1];
        if(port.openPort()){
            System.out.println("Succesfully opened port: "+port.getSystemPortName());
        }
        else{
            System.out.println("Could not open port....");
            return;
        }

        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

        Scanner data = new Scanner(port.getInputStream());
        Reading arduinoReading = new Reading();
        while(data.hasNext()){
            //System.out.println("\nData from arduino: " + data.nextLine());
            if(data.next().contains("Humidity:")){
                String humidity = data.nextLine();
                System.out.println("TEST with string humidity: "+humidity);
                arduinoReading.setHumidity(Double.parseDouble(humidity));
            }
            if(data.next().contains("Temperature:")){
                String temperature = data.nextLine();
                System.out.println("TEST with string temperature: "+temperature);
                arduinoReading.setTemperature(Double.parseDouble(temperature));
            }
            System.out.println("Arduino OBJECT humidity: "+arduinoReading.getHumidity());
            System.out.println("Arduino OBJECT temperature: "+arduinoReading.getTemperature());
        }

        System.out.println("Testing to get out of the while loop in dataReading: ");
    }
}
