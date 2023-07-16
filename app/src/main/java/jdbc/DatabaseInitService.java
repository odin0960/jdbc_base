package jdbc;

import java.io.IOException;

public class DatabaseInitService {

    public static void main(String[] args) throws IOException {

        Database database = Database.getInstance();

        String initDbFilename = new Settings().getString(Settings.INIT_DB_SQL_FILEPATH);
        database.executeUpdate(Utilities.readSqlFromFile(initDbFilename));
    }
}
