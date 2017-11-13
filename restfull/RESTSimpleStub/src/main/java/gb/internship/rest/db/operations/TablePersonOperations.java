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
 * Created by agcheb on 13.11.17.
 */
public class TablePersonOperations {
    private Log LOG = LogFactory.getLog(UsersUiSitesDbOperation.class);

    private Connection connection;

    public TablePersonOperations() {
        this.connection = DbWrapper.getInstance().getConnection();;
    }

    /**
     * Получение всех ID личностей из таблицы persons.
     *
     * @return список всех ID - Integer.
     * @throws SQLException
     */
    public List<Integer> getIDFromPersonTable() throws SQLException {
        List<Integer> resultList = new ArrayList<>();

        LOG.info("SELECT \"ID\" FROM persons;");

        String sqlQuery = "SELECT \"ID\" FROM persons;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            resultList.add(resultSet.getInt("id"));
        }

        statement.close();

        return resultList;
    }


    /**
     * Получение всех Имен личностей из таблицы Persons.
     *
     * @return список всех имен - String.
     * @throws SQLException
     */
    public List<String> getNameFromPersonTable() throws SQLException {
        List<String> resultList = new ArrayList<>();

        LOG.info("SELECT \"Name\" FROM persons;");

        String sqlQuery = "SELECT \"Name\" FROM persons;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            resultList.add(resultSet.getString("name"));
        }

        statement.close();

        return resultList;
    }

    /**
     * Получение значений всех Active из таблицы Persons.
     *
     * @return список всех состояний Active - Integer.
     * @throws SQLException
     */
    public List<Boolean> getActiveFromPersonTable() throws SQLException {
        List<Boolean> resultList = new ArrayList<>();

        LOG.info("SELECT \"Active\" FROM persons;");

        String sqlQuery = "SELECT \"Active\" FROM persons;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            resultList.add(resultSet.getBoolean("active"));
        }

        statement.close();

        return resultList;
    }
}
