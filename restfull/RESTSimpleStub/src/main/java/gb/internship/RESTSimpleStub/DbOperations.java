package gb.internship.RESTSimpleStub;

import gb.internship.RESTSimpleStub.dataobjects.TableClassSites;
import gb.internship.RESTSimpleStub.db.SqLiteInitialization;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
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

    private Log LOG = LogFactory.getLog(DbOperations.class);

    private Connection connection;

    public DbOperations() {
        this.connection = new SqLiteInitialization().getConnection();
    }

    /**
     * @return коннект к базе.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Делаем INSERT полученной строки в таблицу test.
     *
     * @param str строка для вставки в таблицу.
     * @throws SQLException
     */
    public void insertStringInTable(String str) throws SQLException {
        LOG.info("INSERT string: " + str);
        String sqlQuery = "INSERT INTO test VALUES((?));";
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
        String sqlQuery = "SELECT * FROM test;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        // Кладём полученные данные в список.
        while (resultSet.next()) {
            resultList.add(resultSet.getString("someline"));
        }

        statement.close();

        return resultList;
    }


    /**
     * Получение всех сайтов из таблицы sites.
     *
     * @return список всех сайтов обёрнутых в объекты.
     * @throws SQLException
     */
    public List<TableClassSites> getAllSites() throws SQLException {
        List<TableClassSites> resultList = new ArrayList<>();

        LOG.info("SELECT id, name, url, active FROM sites");
        String sqlQuery = "SELECT id, name, url, active FROM sites;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            resultList.add(new TableClassSites(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("url"),
                    resultSet.getBoolean("active")));
        }

        statement.close();

        return resultList;
    }

    /**
     * Добавление сайта в таблицу.
     *
     * @param name   имя сайта.
     * @param url    адрес сайта.
     * @param active активен.
     * @throws SQLException
     */
    public void addSite(String name, String url, Boolean active) throws SQLException {
        LOG.info("INSERT INTO sites: name = " + name + ", url = " + url + ", active = " + active);
        String sqlQuery = "INSERT INTO sites (name, url,active) VALUES ((?), (?), (?));";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, url);
        preparedStatement.setBoolean(3, active);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public int delSite(Integer id) throws SQLException {
        LOG.info("DELETE FROM sites WHERE id = " + id);
        String sqlQuery = "DELETE FROM sites WHERE id = (?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);
        int updateResult = preparedStatement.executeUpdate();
        preparedStatement.close();

        return updateResult;
    }
}
