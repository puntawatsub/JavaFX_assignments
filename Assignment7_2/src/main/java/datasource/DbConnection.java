package datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static Connection conn = null;

    // Error Test
    private final static boolean TEST_ERROR = false;

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            // create connection
            if (TEST_ERROR) {
                conn = DriverManager.getConnection("https://sht.puntawat.dev/5aCxwE");
            } else {
                conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/currency?user=appuser&password=password");
            }

        }
        return conn;
    }

    public static void terminate() throws SQLException {
        getConnection().close();
    }
}
