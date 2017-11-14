package gb.internship.rest.db.operations;

import gb.internship.rest.db.DbWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by agcheb on 14.11.17.
 */
public class TableSitesOperations {
    private Log LOG = LogFactory.getLog(UsersUiSitesDbOperation.class);

    private Connection connection;

    public TableSitesOperations() {
        this.connection = DbWrapper.getInstance().getConnection();;
    }

    /**
     * Получение всех ID сайтов из таблицы sites.
     *
     * @return список всех ID - Integer.
     * @throws SQLException
     */
    public List<Integer> getIDFromSitesTable() throws SQLException {
        List<Integer> resultList = new ArrayList<>();

        LOG.info("SELECT \"ID\" FROM sites;");

        String sqlQuery = "SELECT \"ID\" FROM sites;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            resultList.add(resultSet.getInt("id"));
        }

        statement.close();

        return resultList;
    }


    /**
     * Получение всех Имен сайтов из таблицы sites.
     *
     * @return список всех имен сайтов - String.
     * @throws SQLException
     */
    public List<String> getNameFromSitesTable() throws SQLException {
        List<String> resultList = new ArrayList<>();

        LOG.info("SELECT \"Name\" FROM sites;");

        String sqlQuery = "SELECT \"Name\" FROM sites;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            resultList.add(resultSet.getString("name"));
        }

        statement.close();

        return resultList;
    }


    /**
     * Получение всех URL сайтов из таблицы sites.
     *
     * @return список всех URL сайтов - String.
     * @throws SQLException
     */
    public List<String> getURLFromSitesTable() throws SQLException {
        List<String> resultList = new ArrayList<>();

        LOG.info("SELECT \"URL\" FROM sites;");

        String sqlQuery = "SELECT \"URL\" FROM sites;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            resultList.add(resultSet.getString("url"));
        }

        statement.close();

        return resultList;
    }

    /**
     * Получение значений всех Active из таблицы sites.
     *
     * @return список всех состояний Active - Boolean.
     * @throws SQLException
     */
    public List<Boolean> getActiveFromSitesTable() throws SQLException {
        List<Boolean> resultList = new ArrayList<>();

        LOG.info("SELECT \"Active\" FROM sites;");

        String sqlQuery = "SELECT \"Active\" FROM sites;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            resultList.add(resultSet.getBoolean("active"));
        }

        statement.close();

        return resultList;
    }
}
