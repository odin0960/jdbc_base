package jdbc.DatabaseServices;

import jdbc.Database;
import jdbc.Settings;
import jdbc.Utilities;

import java.io.IOException;

public class DatabaseInitService {

    public static void main(String[] args) throws IOException {

        Database database = Database.getInstance();

        String initDbFilename = new Settings().getString(Settings.INIT_DB_SQL_FILEPATH);
        database.executeUpdate(Utilities.readSqlFromFile(initDbFilename));
    }
}
