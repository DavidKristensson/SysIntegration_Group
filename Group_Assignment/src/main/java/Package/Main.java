package Package;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        SpringApplication.run(Main.class, args);
        DeviceReader deviceReader = new DeviceReader();
        deviceReader.readDataFromArduino();
    }
}