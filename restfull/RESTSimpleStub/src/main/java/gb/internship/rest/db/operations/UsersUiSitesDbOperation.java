package gb.internship.rest.db.operations;

import gb.internship.rest.dataobjects.PersonGeneralStatistic;
import gb.internship.rest.dataobjects.TablePersons;
import gb.internship.rest.db.DbWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.sql.Date;
import java.util.*;

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

        for (TablePersons person: persons) {
            Integer rank = 0;
            Integer personID = person.getId();
            for (Integer pageID:idPages) {
                LOG.info("SELECT \"RANK\" FROM PersonPageRank WHERE siteId = " + pageID + "AND personId =" + personID + ";");
                String sqlQuery = "SELECT \"RANK\" FROM pages WHERE siteId = ? AND personId = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setInt(1,pageID);
                preparedStatement.setInt(2,personID);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    rank += resultSet.getInt("RANK");
                }
                preparedStatement.close();
            }
            resultList.add(new PersonGeneralStatistic(personID,person.getName(),rank));
        }
    return resultList;
    }

    /**
     * Получение всех личносетй из таблицы persons.
     *
     * @return список всех личности, обёрнутых в объекты.
     * @throws SQLException
     */
    private List<TablePersons> getAllPersons() {
        List<TablePersons> resultList = new ArrayList<>();

        LOG.info("SELECT \"ID\", \"Name\", \"Active\" FROM persons;");
        String sqlQuery = "SELECT \"ID\", \"Name\", \"Active\" FROM persons;";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                resultList.add(new TablePersons(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("active")));
            }
            statement.close();
        }catch (SQLException e){
            LOG.warn("Error get persons. Please show log");
            e.printStackTrace();

        }

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


    /**
     * Получение всех id сайта по его имени.
     *
     * @param site  - имя сайта, по которому просматривается статистика
     * @return id сайта, обернутый в Integer
     * @throws SQLException
     */

    private Integer getSiteID(String site) throws SQLException {
        Integer result = null;
        LOG.info("SELECT \"ID\" FROM pages WHERE site = " + site + ";");
        String sqlQuery = "SELECT \"ID\" FROM pages WHERE site = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, site);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            result = resultSet.getInt("ID");
        }
        preparedStatement.close();

        return result;
    }


/**
 * @author Баранов
 * Получаем ежедневную статистику
 */

    private Date getPersonsOfLastScanDate(java.util.Date lastscandate, String name) throws SQLException {
        Date result = null;
        LOG.info("SELECT * FROM (SELECT * FROM pages WHERE lastscandate = " +lastscandate +  " SELECT * FROM persons WHERE name = " + name + ";");
        String sqlQuery = "SELECT * FROM (SELECT * FROM pages WHERE lastscandate = ? SELECT * FROM persons WHERE name =?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setDate(1, (Date) lastscandate);
        preparedStatement.setString(2,name);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            result = resultSet.getDate("lastscandate");
        }
        preparedStatement.close();

        return result;

    }

    /**
     * Добаление сайта от пользователя
     * @param name имя сайта
     * @param url   адрес сайта
     * @param active активность
     * @throws SQLException
     */
    public void addSiteofUser(String name, String url, Boolean active)  {

        LOG.info( "INSERT INTO sites: \"Name\" = " + name + ", url = " + url + ", \"Active\" = " + active);
        String sqlQuery = "INSERT INTO \"sites\" (\"Name\", \"URL\",\"Active\") VALUES ((?), (?), (?));";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, url);
            preparedStatement.setBoolean(3, active);
            preparedStatement.execute();
            preparedStatement.close();

        }catch (SQLException e){
            LOG.warn("Error add sites. Please show log");
            e.printStackTrace();

        }

    }

    /**
     * Цдаление сайта под пользователем
     * @param id сайтa
     * @return
     * @throws SQLException
     */
    public int delSiteofUser(Integer id) {
        LOG.info("DELETE FROM sites WHERE \"ID\" = " + id);
        String sqlQuery = "DELETE FROM \"sites\" WHERE \"ID\" = (?);";
        PreparedStatement preparedStatement = null;
        int updateResult = 0;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            updateResult = preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            LOG.warn("Error delete sites. Please show log.");
            e.printStackTrace();
        }
        return updateResult;

    }


}
