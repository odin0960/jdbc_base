package jdbc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DatabasePopulateService {

    public static void main(String[] args) {

        Database database = Database.getInstance();

        try {
            String populateDbFilename = new Settings().getString(Settings.POPULATE_DB_SQL_FILEPATH);

//            String sql = String.join("\n", Files.readString(Path.of(populateDbFilename)));
            String sql = Files.readString(Path.of(populateDbFilename));

            database.executeUpdate(sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
