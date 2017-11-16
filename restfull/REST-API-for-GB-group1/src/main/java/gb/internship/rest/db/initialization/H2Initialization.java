package gb.internship.rest.db.initialization;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Инициализация H2.
 *
 * @author Aleksandr Vvedensky
 */
public class H2Initialization {
    private Log LOG = LogFactory.getLog(H2Initialization.class);

    private Connection connection;

    public H2Initialization() {
        try {
            LOG.info("Initialization org.sqlite.JDBC.");
            this.connection = DriverManager.getConnection("jdbc:h2:mem:");
        } catch (SQLException e) {
            LOG.error("Can't get class. No driver found.");
            e.printStackTrace();
            return;
        }

        createTable();
    }

    /**
     * Создаём таблицы в базе.
     *
     * @throws SQLException пока не обрабатываем.
     */
    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE test(someline VARCHAR(2048))");
            statement.close();
        } catch (SQLException ex) {
            LOG.error("Can't create table in base.");
            ex.printStackTrace();
        }
    }
}
