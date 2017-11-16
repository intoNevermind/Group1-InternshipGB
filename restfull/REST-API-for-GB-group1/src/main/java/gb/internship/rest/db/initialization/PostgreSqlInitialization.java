package gb.internship.rest.db.initialization;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Инициалзация PostgreSQL.
 *
 * @author Aleksandr Vvedensky
 */
public class PostgreSqlInitialization {
    private Log LOG = LogFactory.getLog(SqLiteInitialization.class);
    Connection connection = null;

    /**
     *
     * @param driverName org.postgresql.Driver
     * @param connectionString dbc:postgresql://127.0.0.1:5432/dbname
     * @param username username
     * @param password password
     */
    public PostgreSqlInitialization(String driverName, String connectionString, String username, String password) {

        try {
            LOG.info("Initialization " + driverName);
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            LOG.error("Can't get class. No driver found.");
            e.printStackTrace();
            return;
        }

        try {
            LOG.info("Get " + driverName + "  connection.");
            this.connection = DriverManager.getConnection(connectionString, username, password);
        } catch (SQLException e) {
            LOG.error("Can't get connection. Incorrect URL.");
            e.printStackTrace();
            return;
        }
    }

    /**
     * @return коннект к PostgreSQL.
     */
    public Connection getConnection() {
        return connection;
    }

}
