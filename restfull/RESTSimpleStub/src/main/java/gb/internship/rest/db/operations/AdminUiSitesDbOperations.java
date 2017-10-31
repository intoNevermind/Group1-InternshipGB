package gb.internship.rest.db.operations;

import gb.internship.rest.dataobjects.TablePersons;
import gb.internship.rest.dataobjects.TableSites;
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
    public List<TableSites> getAllSites() throws SQLException {
        List<TableSites> resultList = new ArrayList<>();

        LOG.info("SELECT id, name, url, active FROM sites");
        String sqlQuery = "SELECT id, name, url, active FROM sites;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            resultList.add(new TableSites(resultSet.getInt("id"),
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

    /**
     * Получение всех личносетй из тиблицы persons.
     *
     * @return список всех личности, обёрнутых в объекты.
     * @throws SQLException
     */
    public List<TablePersons> getAllPersons() throws SQLException {
        List<TablePersons> resultList = new ArrayList<>();

        LOG.info("SELECT id, name, active FROM persons;");
        String sqlQuery = "SELECT id, name, active FROM persons;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            resultList.add(new TablePersons(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getBoolean("active")));
        }
        statement.close();

        return resultList;
    }

    /**
     * Добавление личности в таблицу.
     *
     * @param name   имя личности.
     * @param active статус личности.
     * @throws SQLException
     */
    public void addPerson(String name, Boolean active) throws SQLException {
        LOG.info("INSERT INTO persons (name, active) VALUES (" + name + ", " + active + ");");
        String sqlQuery = "INSERT INTO persons (name, active) VALUES ((?), (?));";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, name);
        preparedStatement.setBoolean(2, active);
        preparedStatement.execute();
        preparedStatement.close();
    }

    /**
     * Удаляет личность из таблицы.
     *
     * @param id идентификатор личности.
     * @return количество удалённых строк.
     * @throws SQLException
     */
    public int delPerson(Integer id) throws SQLException {
        LOG.info("DELETE FROM persons WHERE id = " + id);
        String sqlQuery = "DELETE FROM persons WHERE id = (?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);
        int updateResult = preparedStatement.executeUpdate();
        preparedStatement.close();

        return updateResult;
    }

    /**
     * Изменение личности.
     *
     * @param id     уникальный идентификатор в таблице persons.
     * @param name   имя личности.
     * @param active статус личности.
     * @return количество модифицированных строк.
     * @throws SQLException
     */
    public int modifyPerson(Integer id, String name, Boolean active) throws SQLException {
        LOG.info("UPDATE persons SET name = " + name + ", active = " + active +
                " WHERE id = " + id);
        String sqlQuery = "UPDATE persons SET name = (?), active = (?) WHERE id = (?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, name);
        preparedStatement.setBoolean(2, active);
        preparedStatement.setInt(3, id);
        int updateResult = preparedStatement.executeUpdate();
        preparedStatement.close();

        return updateResult;
    }
}
