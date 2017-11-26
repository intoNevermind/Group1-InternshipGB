package gb.internship.rest.db.operations;

import gb.internship.rest.db.DbWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by agcheb on 18.11.17.
 */
public class TablePagesDbOperations {
    private Log LOG = LogFactory.getLog(UsersUiDbOperation.class);

    private Connection connection;

    public TablePagesDbOperations() {
        this.connection = DbWrapper.getInstance().getConnection();;
    }

    /**
     * Получение всех ID страниц из таблицы pages.
     *
     * @return список всех ID - Integer.
     * @throws SQLException
     */
    public List<Integer> getIDFromPagesTable() throws SQLException {
        List<Integer> resultList = new ArrayList<>();

        LOG.info("SELECT \"ID\" FROM pages;");

        String sqlQuery = "SELECT \"ID\" FROM pages;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            resultList.add(resultSet.getInt("id"));
        }

        statement.close();

        return resultList;
    }


    /**
     * Получение всех ID сайта, к которому относится страница из таблицы pages.
     *
     * @return список всех ID сайтов, к которым относится страница - Integer.
     * @throws SQLException
     */
    public List<Integer> getSiteIDFromPagesTable() throws SQLException {
        List<Integer> resultList = new ArrayList<>();

        LOG.info("SELECT \"SiteID\" FROM pages;");

        String sqlQuery = "SELECT \"SiteID\" FROM pages;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            resultList.add(resultSet.getInt("siteid"));
        }

        statement.close();

        return resultList;
    }



    /**
     * Получение всех URL страниц из таблицы pages.
     *
     * @return список всех URL страниц - String.
     * @throws SQLException
     */
    public List<String> getURLFromPagesTable() throws SQLException {
        List<String> resultList = new ArrayList<>();

        LOG.info("SELECT \"URL\" FROM pages;");

        String sqlQuery = "SELECT \"URL\" FROM pages;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            resultList.add(resultSet.getString("url"));
        }

        statement.close();

        return resultList;
    }

    /**
     * Получение значений всех FoundDateTime из таблицы pages.
     *
     * @return список всех состояний FoundDateTime - Date.
     * @throws SQLException
     */
    public List<Date> getFoundDateTimeFromPagesTable() throws SQLException {
        List<Date> resultList = new ArrayList<>();

        LOG.info("SELECT \"FoundDateTime\" FROM pages;");

        String sqlQuery = "SELECT \"FoundDateTime\" FROM pages;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            resultList.add(resultSet.getDate("founddatetime"));
        }

        statement.close();

        return resultList;
    }

    /**
     * Получение значений всех LastScanDate из таблицы pages.
     *
     * @return список всех состояний LastScanDate - Date.
     * @throws SQLException
     */
    public List<Date> getLastScanDateFromPagesTable() throws SQLException {
        List<Date> resultList = new ArrayList<>();

        LOG.info("SELECT \"LastScanDate\" FROM pages;");

        String sqlQuery = "SELECT \"LastScanDate\" FROM pages;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            resultList.add(resultSet.getDate("lastscandate"));
        }

        statement.close();

        return resultList;
    }
}
