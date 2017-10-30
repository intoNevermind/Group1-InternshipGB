package gb.internship.rest.db.operations;

import gb.internship.rest.dataobjects.TableClassSites;
import gb.internship.rest.db.DbWrapper;
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
public class AdminUiSitesDbOperations {

    private Log LOG = LogFactory.getLog(AdminUiSitesDbOperations.class);

    private Connection connection;

    public AdminUiSitesDbOperations() {
        this.connection = DbWrapper.getInstance().getConnection();
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

    /**
     * @param id уникальный идентификатор в таблице sites.
     * @return количество удалённых строк.
     * @throws SQLException
     */
    public int delSite(Integer id) throws SQLException {
        LOG.info("DELETE FROM sites WHERE id = " + id);
        String sqlQuery = "DELETE FROM sites WHERE id = (?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);
        int updateResult = preparedStatement.executeUpdate();
        preparedStatement.close();

        return updateResult;
    }

    /**
     * @param id     уникальный идентификатор в таблице sites.
     * @param name   имя сайта.
     * @param url    адрес сайта.
     * @param active активен.
     * @return количество удалённых строк.
     * @throws SQLException
     */
    public int modifySite(Integer id, String name, String url, Boolean active) throws SQLException {
        LOG.info("UPDATE sites SET name = " + name + ", url = " + url + ", active = " + active +
                " WHERE id = " + id);
        String sqlQuery = "UPDATE sites SET name = (?), url = (?), active = (?) WHERE id = (?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, url);
        preparedStatement.setBoolean(3, active);
        preparedStatement.setInt(4, id);
        int updateResult = preparedStatement.executeUpdate();
        preparedStatement.close();

        return updateResult;
    }
}
