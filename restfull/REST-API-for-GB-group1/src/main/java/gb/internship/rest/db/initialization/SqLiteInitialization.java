package gb.internship.rest.db.initialization;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Инициализация SQLite.
 *
 * @author Aleksandr Vvedensky
 */
public class SqLiteInitialization {
    private Log LOG = LogFactory.getLog(SqLiteInitialization.class);

    private final String driverName = "org.sqlite.JDBC";
    private final String connectionString = "jdbc:sqlite:persons_statistics.sqlite";

    Connection connection = null;

    public SqLiteInitialization() {
        try {
            LOG.info("Initialization org.sqlite.JDBC.");
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            LOG.error("Can't get class. No driver found.");
            e.printStackTrace();
            return;
        }

        try {
            LOG.info("Get org.sqlite.JDBC connection.");
            this.connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            LOG.error("Can't get connection. Incorrect URL.");
            e.printStackTrace();
            return;
        }
    }

    /**
     * @return коннект к SQLite.
     */
    public Connection getConnection() {
        return connection;
    }
}
