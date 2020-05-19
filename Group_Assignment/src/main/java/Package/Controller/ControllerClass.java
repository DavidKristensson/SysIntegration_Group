package Package.Controller;

import Package.DbManager;
import Package.Reading;
import Package.DeviceReader;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
@CrossOrigin
@RestController
public class ControllerClass {
    DbManager dbManager = new DbManager();



    @RequestMapping("/getDataFromDatabase")
    public ArrayList<Reading> getReadingsFromDb() throws IOException, ClassNotFoundException { return dbManager.getReadingsFromDb(); }


    @RequestMapping(value = "/current", produces = "application/json")
    public Reading getCurrentValue(){ return new Reading(dbManager.staticTemperature, dbManager.staticHumidity); }
}
