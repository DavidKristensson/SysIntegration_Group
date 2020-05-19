package Package.Controller;

import Package.DbManager;
import Package.Reading;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class ControllerClass {
    DbManager dbManager = new DbManager();

    @RequestMapping("/getDataFromDatabase")
    public ArrayList<Reading> getReadingsFromDb() throws IOException, ClassNotFoundException { return dbManager.getReadingsFromDb(); }
}
