package gb.internship.rest.requestcontrollers;

import gb.internship.rest.db.operations.TestDbOperations;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Простой REST контроллер.
 *
 * @author Aleksandr Vvedensky
 */

@RestController
public class TestController {

    private Log LOG = LogFactory.getLog(TestController.class);

    // Default response для метода echo.
    private static final String DEFAULT_RESPONSE = "You see default string." +
            "<br>Use: http://localhost:8080/echo?echoString=myString" +
            "<br>to see your string.";

    // Переменная для работы с базой.
    private TestDbOperations dbOperations;

    public TestController() throws SQLException {
        dbOperations = new TestDbOperations();
    }

    /**
     * Обслуживает это-запросы.
     *
     * @param echoString строка аргумент из запроса.
     * @return полученный echoString или значение по умолчанию.
     */
    @RequestMapping(value = {"/echo", "/unauthorized/echo"})
    public String echo(@RequestParam(value = "echoString", defaultValue = DEFAULT_RESPONSE) String echoString) {
        return echoString;
    }

    /**
     * Делает запрос в базу.
     *
     * @return список всех строк из базы.
     */
    @RequestMapping("/getAll")
    public List<String> getAllStrings() {
        List<String> resultList = new ArrayList<>();
        try {
            resultList = dbOperations.getAllStringsFromTable();
        } catch (SQLException ex) {
            LOG.warn("Error in receipt data.");
            ex.printStackTrace();
        }

        return resultList;
    }


    /**
     * @author баранов
     * Делает запрос в базу по параметрам пользователя
     */
    @RequestMapping("/getStatic")
    public List<String> getPersonStatic() throws SQLException {
        List<String> datalist = new ArrayList<>();
        datalist = dbOperations.getDataUser();
        return  datalist;
    }

    /**
     * Делает запрос в базу.
     *
     * @param stringToInsert строка для вставки в базу.
     * @return OK, если сработалшо как задумано. Иначе ERROR.
     */
    @RequestMapping("/insertString")
    public String insertStringInTable(@RequestParam(value = "stringToInsert", defaultValue = "") String stringToInsert) {

        if ("".equals(stringToInsert)) {
            LOG.warn("Trying to insert empty string. Skipping.");
            return "ERROR";
        }

        try {
            dbOperations.insertStringInTable(stringToInsert);
            return "OK";
        } catch (SQLException e) {
            LOG.warn("Data insertion error.");
            e.printStackTrace();
            return "ERROR";
        }
    }
}
