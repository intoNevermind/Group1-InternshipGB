package gb.internship.rest.requestcontrollers.admin;

import gb.internship.rest.dataobjects.TableKeywords;
import gb.internship.rest.db.operations.AdminUiSitesDbOperations;
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
    private AdminUiSitesDbOperations adminUiSitesDbOperations;

    public AdminUiKeywordController() {
        adminUiSitesDbOperations = new AdminUiSitesDbOperations();
    }

    /**
     * Получение всех ключевых слов.
     *
     * @return список всех ключевых слов.
     */
    @RequestMapping(value = {"/admin/ui/getAllKeywords", "/unauthorized/admin/ui/getAllKeywords"})
    public List<TableKeywords> getAllKeywords() {
        List<TableKeywords> resultList = new ArrayList<>();
        try {
            resultList = adminUiSitesDbOperations.getAllKeywords();
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
    @RequestMapping(value = {"/admin/ui/addKeyword", "/unauthorized/admin/ui/addKeyword"})
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
            adminUiSitesDbOperations.addKeyword(name, personId);
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
    @RequestMapping(value = {"/admin/ui/delKeyword", "/unauthorized/admin/ui/delKeyword"})
    public ResponseEntity delKeyword(@RequestParam(value = "id") Integer id) {
        int deletedRows;

        if (id == null) {
            LOG.warn("Error in /admin/ui/delKeyword. id == null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error. id == null");
        }

        try {
            deletedRows = adminUiSitesDbOperations.delKeyword(id);
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
    @RequestMapping(value = {"/admin/ui/modifyKeyword", "/unauthorized/admin/ui/modifyKeyword"})
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
            updatedRows = adminUiSitesDbOperations.modifyKeyword(id, personId, name);
        } catch (SQLException ex) {
            LOG.warn("Error at run modify Keyword.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error at run modify Keyword.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedRows);
    }
}
