package gb.internship.RESTSimpleStub;

import gb.internship.RESTSimpleStub.dataobjects.TableClassSites;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /**
     * Работа с элементами справичника (сайтами). Отображение всех элементов.
     *
     * @return список всех сайтов.
     */
    @RequestMapping("/admin/ui/getAllSites")
    public List<TableClassSites> getAllSites() {
        List<TableClassSites> resultList = new ArrayList<>();
        try {
            resultList = dbOperations.getAllSites();
        } catch (Exception ex) {
            LOG.warn("Error getting all sites data.");
            ex.printStackTrace();
        }

        return resultList;
    }


    /**
     * Работа с элементами справичника (сайтами). Добавление элемента справочника.
     *
     * @param name   имя сайта.
     * @param url    адрес сайта.
     * @param active активен.
     * @return сообщение о статусе выполнения.
     */
    @RequestMapping("/admin/ui/addSite")
    public ResponseEntity addSite(@RequestParam(value = "name") String name,
                                  @RequestParam(value = "url") String url,
                                  @RequestParam(value = "active") String active) {

        if ("".equals(name)) {
            LOG.warn("Error in /admin/ui/addSite. name is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/addSite. name is empty.");
        }
        if ("".equals(url)) {
            LOG.warn("Error in /admin/ui/addSite. url is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/addSite. url is empty.");
        }
        if ("".equals(active)) {
            LOG.warn("Error in /admin/ui/addSite. active is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/addSite. active is empty.");
        }

        Boolean activeBooleanValue = Boolean.parseBoolean(active);
        try {
            dbOperations.addSite(name, url, activeBooleanValue);
        } catch (Exception ex) {
            LOG.warn("Error at run add site.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error at run add site.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body("OK");
    }

    /**
     * Работа с элементами справичника (сайтами). Удаление элемента справочника.
     *
     * @param id уникальный идентификатор в таблице sites.
     * @return сообщение о статусе выполнения.
     * В случае корректного выполнения в теле ответа возвращается количество удалёных записей.
     */
    @RequestMapping("/admin/ui/delSite")
    public ResponseEntity delSite(@RequestParam(value = "id") Integer id) {
        int deletedRows = 0;

        if (id == null) {
            LOG.warn("Error in /admin/ui/delSite. id == null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error. id == null");
        }

        try {
            deletedRows = dbOperations.delSite(id);
        } catch (SQLException ex) {
            LOG.warn("Error at run del site.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error at run del site.");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(deletedRows);
    }

    /**
     * Работа с элементами справичника (сайтами). Редактирование элемента справочника.
     *
     * @param id     уникальный идентификатор в таблице sites.
     * @param name   имя сайта.
     * @param url    адрес сайта.
     * @param active активен.
     * @return сообщение о статусе выполнения.
     */
    @RequestMapping("/admin/ui/modifySite")
    public ResponseEntity modifySite(@RequestParam(value = "id") Integer id,
                                     @RequestParam(value = "name") String name,
                                     @RequestParam(value = "url") String url,
                                     @RequestParam(value = "active") String active) {

        int updatedRows = 0;

        if (id == null) {
            LOG.warn("Error in /admin/ui/modifySite. id == null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/modifySite. id == null");
        }
        if ("".equals(name)) {
            LOG.warn("Error in /admin/ui/modifySite. name is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/modifySite. name is empty.");
        }
        if ("".equals(url)) {
            LOG.warn("Error in /admin/ui/modifySite. url is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/modifySite. url is empty.");
        }
        if ("".equals(active)) {
            LOG.warn("Error in /admin/ui/modifySite. active is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/modifySite. active is empty.");
        }

        Boolean activeBooleanValue = Boolean.parseBoolean(active);

        try {
            updatedRows = dbOperations.modifySite(id, name, url, activeBooleanValue);
        } catch (SQLException ex) {
            LOG.warn("Error at run modify site.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error at run modify site.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedRows);
    }
}
