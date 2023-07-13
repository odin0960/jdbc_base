package jdbc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DatabaseInitService {

    public static void main(String[] args) {

        Database database = Database.getInstance();

        try {
            String initDbFilename = new Settings().getString(Settings.INIT_DB_SQL_FILEPATH);

//            String sql = String.join("\n", Files.readString(Path.of(initDbFilename)));
            String sql = Files.readString(Path.of(initDbFilename));

            database.executeUpdate(sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public void initDb(Database database) {
//        try {
//            String initDbFilename = new Settings().getString(Settings.INIT_DB_SQL_FILEPATH);
//            String sql = String.join("\n", Files.readString(Path.of(initDbFilename)));
//            database.executeUpdate(sql);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
