package datasource

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object DbConnection {
    private var conn: Connection? = null

    // Error Test
    private const val TEST_ERROR = false;

    @get:Throws(SQLException::class)
    val connection: Connection?
        get() {
            if (conn == null) {
                // create connection
                if (TEST_ERROR) {
                    conn = DriverManager.getConnection("https://sht.puntawat.dev/5aCxwE")
                } else {
                    conn =
                        DriverManager.getConnection("jdbc:mariadb://localhost:3306/currency?user=appuser&password=password")
                }
            }
            return conn
        }

    @Throws(SQLException::class)
    fun terminate() {
        connection?.close()
    }
}