package gb.internship.RESTSimpleStub;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Работа с базой.
 *
 * @author Aleksandr Vvedensky
 */
public class DbOperations {

    // Переменная для работы с логами
    private Log LOG = LogFactory.getLog(DbOperations.class);


    // Переменная с коннектом к базе.
    private Connection connection;

    /**
     * @throws SQLException пока не обрабатываем.
     */
    public DbOperations() throws SQLException {
        // Инициализируем коннект к базе.
        this.connection = DriverManager.getConnection("jdbc:h2:mem:");

        createTable();
    }

    /**
     * @return коннект к базе.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Делаем INSERT полученной строки в таблицу some_lines.
     *
     * @param str строка для вставки в таблицу.
     * @throws SQLException
     */
    public void insertStringInTable(String str) throws SQLException {
        LOG.info("INSERT string: " + str);
        String sqlQuery = "INSERT INTO some_lines VALUES((?))";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, str);
        preparedStatement.execute();
        preparedStatement.close();
    }


    /**
     * SELECT ALL
     *
     * @return список всех строк из базы.
     * @throws SQLException
     */
    public List<String> getAllStringsFromTable() throws SQLException {
        List<String> resultList = new ArrayList<>();

        LOG.info("SELECT all lines.");
        String sqlQuery = "SELECT * FROM some_lines";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        // Кладём полученные данные в список.
        while (resultSet.next()) {
            resultList.add(resultSet.getString("someline"));
        }

        return resultList;
    }

    /**
     * Создаём таблицы в базе.
     *
     * @throws SQLException пока не обрабатываем.
     */
    private void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE some_lines(someline VARCHAR(2048))");
        statement.close();
    }
}
