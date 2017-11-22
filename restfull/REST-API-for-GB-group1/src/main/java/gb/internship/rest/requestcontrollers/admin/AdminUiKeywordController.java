package gb.internship.rest.requestcontrollers.admin;

import gb.internship.rest.dataobjects.TableKeywords;
import gb.internship.rest.db.operations.AdminUiDbOperations;
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
 * Операции с ключевыми словами в админском интерфейсе.
 *
 * @author Aleksandr Vvedensky
 */
@RestController
public class AdminUiKeywordController {
    private Log LOG = LogFactory.getLog(AdminUiKeywordController.class);
    private AdminUiDbOperations adminUiDbOperations;

    public AdminUiKeywordController() {
        adminUiDbOperations = new AdminUiDbOperations();
    }

    /**
     * Получение всех ключевых слов.
     *
     * @return список всех ключевых слов.
     */
    @RequestMapping(value = {"/admin/ui/getAllKeywords", "/unauthorized/admin/ui/getAllKeywords",
            "/user/ui/getAllKeywords", "/unauthorized/user/ui/getAllKeywords"})
    public List<TableKeywords> getAllKeywords() {
        List<TableKeywords> resultList = new ArrayList<>();
        try {
            resultList = adminUiDbOperations.getAllKeywords();
        } catch (Exception ex) {
            LOG.warn("Error getting all Keywords data.");
            ex.printStackTrace();
        }

        return resultList;
    }

    /**
     * Добавление ключевого слова.
     *
     * @param name     ключевое слово.
     * @param personId идентификатор личности.
     * @return сообщение о статусе выполнения.
     */
    @RequestMapping(value = {"/admin/ui/addKeyword", "/unauthorized/admin/ui/addKeyword",
            "/user/ui/addKeyword", "/unauthorized/user/ui/addKeyword"})
    public ResponseEntity addKeyword(@RequestParam(value = "name") String name,
                                     @RequestParam(value = "personId") Integer personId) {

        if ("".equals(name)) {
            LOG.warn("Error in /admin/ui/addKeyword. name is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/addKeyword. name is empty.");
        }
        if (personId == null) {
            LOG.warn("Error in /admin/ui/modifyKeyword. personId == null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/modifyKeyword. personId == null");
        }

        try {
            adminUiDbOperations.addKeyword(name, personId);
        } catch (Exception ex) {
            LOG.warn("Error at run add Keyword.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error at run add Keyword.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body("OK");
    }

    /**
     * Удаление ключевог ослова.
     *
     * @param id идентификатор ключавого слова.
     * @return сообщение о статусе выполнения.
     * В случае корректного выполнения в теле ответа возвращается количество удалёных записей.
     */
    @RequestMapping(value = {"/admin/ui/delKeyword", "/unauthorized/admin/ui/delKeyword",
            "/user/ui/delKeyword", "/unauthorized/user/ui/getKeyword"})
    public ResponseEntity delKeyword(@RequestParam(value = "id") Integer id) {
        int deletedRows;

        if (id == null) {
            LOG.warn("Error in /admin/ui/delKeyword. id == null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error. id == null");
        }

        try {
            deletedRows = adminUiDbOperations.delKeyword(id);
        } catch (SQLException ex) {
            LOG.warn("Error at run del Keyword.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error at run del Keyword.");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(deletedRows);
    }

    /**
     * Изменение ключевогослова.
     *
     * @param id       идентификатор ключавого слова.
     * @param name     ключевое слово.
     * @param personId идентификатор личности.
     * @return сообщение о статусе выполнения.
     * В случае корректного выполнения в теле ответа возвращается количество удалёных записей.
     */
    @RequestMapping(value = {"/admin/ui/modifyKeyword", "/unauthorized/admin/ui/modifyKeyword",
            "/user/ui/modifyKeyword", "/unauthorized/user/ui/modifyKeyword"})
    public ResponseEntity modifySite(@RequestParam(value = "id") Integer id,
                                     @RequestParam(value = "name") String name,
                                     @RequestParam(value = "personId") Integer personId) {

        int updatedRows;

        if (id == null) {
            LOG.warn("Error in /admin/ui/modifyKeyword. id == null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/modifyKeyword. id == null");
        }
        if ("".equals(name)) {
            LOG.warn("Error in /admin/ui/modifyKeyword. name is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/modifyKeyword. name is empty.");
        }
        if (personId == null) {
            LOG.warn("Error in /admin/ui/modifyKeyword. personId == null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/modifyKeyword. personId == null");
        }

        try {
            updatedRows = adminUiDbOperations.modifyKeyword(id, personId, name);
        } catch (SQLException ex) {
            LOG.warn("Error at run modify Keyword.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error at run modify Keyword.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedRows);
    }

    /**
     * Получение списка ключевых слов по идентификатору личности.
     *
     * @param id уникальный иднтификатр личности.
     * @return Список TableKeywords или ошибку.
     */
    @RequestMapping(value = {"/admin/ui/getKeywordsByPersonId", "/unauthorized/admin/ui/getKeywordsByPersonId",
            "/user/ui/getKeywordsByPersonId", "/unauthorized/user/ui/getKeywordsByPersonId"})
    public ResponseEntity<?> getKeywordsByPersonId(@RequestParam(value = "id") Integer id) {
        if (id == null) {
            LOG.warn("Error in /admin/ui/getKeywordsByPersonId. id == null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/getKeywordsByPersonId. id == null");
        }

        try {
            List<TableKeywords> resultList = adminUiDbOperations.getKeywordsByPersonId(id);
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        } catch (SQLException e) {
            LOG.warn("Error at run getKeywordsByPersonId.");
            e.printStackTrace();
            return new ResponseEntity<>("Error at run getKeywordsByPersonId.", HttpStatus.BAD_REQUEST);
        }

    }
}
