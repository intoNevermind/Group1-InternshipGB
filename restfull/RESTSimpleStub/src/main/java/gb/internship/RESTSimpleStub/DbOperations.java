package gb.internship.RESTSimpleStub;

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
        String sqlQuery = "INSERT INTO test VALUES((?))";
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
        String sqlQuery = "SELECT * FROM test";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        // Кладём полученные данные в список.
        while (resultSet.next()) {
            resultList.add(resultSet.getString("someline"));
        }

        return resultList;
    }

    /**
     * @author Баранов
     *
     * Делаем Запрос на выбор по параметрам.
     *
     * Прошу прошения если это не то.....:)
     *
     */
        public List <String> getDataUser(){
             List<String> data = new ArrayList<>();

         String dataQuery = "SELECT URL, SiteID, FoundDataTime, LastScanDate FROM pages";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(dataQuery);
                while (resultSet.next()){
                    data.add(resultSet.getString("Json ответ... "));

                }
            } catch (SQLException e) {
                LOG.info("Connection error");
            }

            return data ;
        }
}
