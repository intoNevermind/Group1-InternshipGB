package gb.internship.RESTSimpleStub;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class RequestController {

    // Переменная для работы с логами
    private Log LOG = LogFactory.getLog(RequestController.class);

    // Default response для метода echo.
    public static final String DEFAULT_RESPONSE = "You see default string." +
            "<br>Use: http://localhost:8080/echo?echoString=myString" +
            "<br>to see your string.";

    // Переменная для работы с базой.
    DbOperations dbOperations;

    public RequestController() throws SQLException {
        dbOperations = new DbOperations();
    }

    /**
     * Обслуживает это-запросы.
     *
     * @param echoString строка аргумент из запроса.
     * @return полученный echoString или значение по умолчанию.
     */
    @RequestMapping("/echo")
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
            LOG.warn("Data inserrtion error.");
            e.printStackTrace();
            return "ERROR";
        }
    }
}
