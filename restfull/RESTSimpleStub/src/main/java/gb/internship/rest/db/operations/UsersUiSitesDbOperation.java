package gb.internship.rest.db.operations;

import gb.internship.rest.dataobjects.PersonGeneralStatistic;
import gb.internship.rest.dataobjects.TablePersons;
import gb.internship.rest.db.DbWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author баранов
 * Класс для работы с базой с User UI
 */


public class UsersUiSitesDbOperation {
    private Log LOG = LogFactory.getLog(UsersUiSitesDbOperation.class);

    private Connection connection;

    public UsersUiSitesDbOperation() {
        this.connection = DbWrapper.getInstance().getConnection();
    }

    /**
     * Получение общей статистики по всем личностям из таблицы personpagerank.
     *
     * @param site - имя сайта, по которому пользователь желает посмотреть статистику
     * @return список всех личностей с их рейтингом, обёрнутых в объекты.
     * @throws SQLException
     */
    public List<PersonGeneralStatistic> getAllRatesForChoosenSite(String site) throws SQLException {
        List<PersonGeneralStatistic> resultList = new ArrayList<>();

        List<TablePersons> persons = getAllPersons();
        List<Integer> idPages = getAllPagesIDOfSite(getSiteID(site));


        LOG.info("SELECT \"ID\", \"Name\", \"URL\", \"Active\" FROM sites;");
        String sqlQuery = "SELECT \"ID\", \"Name\", \"URL\", \"Active\" FROM sites;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

//        while (resultSet.next()) {
//            resultList.add(new PersonGeneralStatistic(resultSet.getInt("id"),
//                    resultSet.getString("name"),
//                    resultSet.getString("url"),
//                    resultSet.getInt("active")));
//        }

        statement.close();

        return resultList;
    }

    /**
     * Получение всех личносетй из таблицы persons.
     *
     * @return список всех личности, обёрнутых в объекты.
     * @throws SQLException
     */
    private List<TablePersons> getAllPersons() throws SQLException {
        List<TablePersons> resultList = new ArrayList<>();

        LOG.info("SELECT \"ID\", \"Name\", \"Active\" FROM persons;");
        String sqlQuery = "SELECT \"ID\", \"Name\", \"Active\" FROM persons;";
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
     * Получение всех id страниц из таблицы Pages.
     *
     * @param siteId  - id сайта, по которому просматривается статистика
     * @return список всех id страниц, оернутых в Integer
     * @throws SQLException
     */

    private List<Integer> getAllPagesIDOfSite(int siteId) throws SQLException {
        List<Integer> resultList = new ArrayList<>();

        LOG.info("SELECT \"ID\" FROM pages WHERE siteId = " + siteId + ";");
        String sqlQuery = "SELECT \"ID\" FROM pages WHERE siteId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1,siteId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            resultList.add(resultSet.getInt("ID"));
        }
        preparedStatement.close();

        return resultList;
    }

    private Integer getSiteID(String site){
        return null;
    }
}
