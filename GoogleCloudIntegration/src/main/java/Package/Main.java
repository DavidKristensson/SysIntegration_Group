package Package;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        DbManager dbManager = new DbManager();
        dbManager.dataBaseInteraction();
    }
}