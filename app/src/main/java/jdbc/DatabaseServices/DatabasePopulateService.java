package jdbc.DatabaseServices;

import jdbc.Database;
import jdbc.Settings;
import jdbc.Utilities;

import java.io.IOException;

public class DatabasePopulateService {

    public static void main(String[] args) throws IOException {

        Database database = Database.getInstance();

        String populateDbFilename = new Settings().getString(Settings.POPULATE_DB_SQL_FILEPATH);

        database.executeUpdate(Utilities.readSqlFromFile(populateDbFilename));
    }
}
