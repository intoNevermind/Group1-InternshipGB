package gb.internship.rest.db.operations;

import gb.internship.rest.dataobjects.TableKeywords;
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
 * Работа с базой для админского интерфейса.
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

    /**
     * Получение содержимого таблицы keywords.
     *
     * @return список всех ключевых слов, обёрнутых в объекты.
     * @throws SQLException
     */
    public List<TableKeywords> getAllKeywords() throws SQLException {
        List<TableKeywords> resultList = new ArrayList<>();

        LOG.info("SELECT id, name, personid FROM keywords;");
        String sqlQuery = "SELECT id, personid, name FROM keywords;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            resultList.add(new TableKeywords(resultSet.getInt("id"),
                    resultSet.getInt("personid"),
                    resultSet.getString("name")));
        }
        statement.close();

        return resultList;
    }

    /**
     * Добавление ключевого слова.
     *
     * @param name     ключевое слово.
     * @param personId идентификатор личности.
     * @throws SQLException
     */
    public void addKeyword(String name, Integer personId) throws SQLException {
        LOG.info("INSERT INTO keywords (name, personid) VALUES (" + name + ", " + personId + ");");
        String sqlQuery = "INSERT INTO keywords (name, personid) VALUES ((?), (?));";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, personId);
        preparedStatement.execute();
        preparedStatement.close();
    }

    /**
     * Удаление ключевого слова.
     *
     * @param id идентификатор ключавого слова.
     * @return количество удалённых строк.
     * @throws SQLException
     */
    public int delKeyword(Integer id) throws SQLException {
        LOG.info("DELETE FROM keywords WHERE id = " + id);
        String sqlQuery = "DELETE FROM keywords WHERE id = (?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);
        int updateResult = preparedStatement.executeUpdate();
        preparedStatement.close();

        return updateResult;
    }

    /**
     * Изменение ключевого слова.
     *
     * @param id       идентификатор ключевого слова.
     * @param personId идентификатор пользователя.
     * @param name     ключевое слово.
     * @return количество удалённых строк.
     * @throws SQLException
     */
    public int modifyKeyword(Integer id, Integer personId, String name) throws SQLException {
        LOG.info("UPDATE keywords SET name " + name + ", personid = " + personId +
                " WHERE id = " + id);
        String sqlQuery = "UPDATE keywords SET name = (?), personid = (?) WHERE id = (?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, personId);
        preparedStatement.setInt(3, id);
        int updateResult = preparedStatement.executeUpdate();
        preparedStatement.close();

        return updateResult;
    }
}
