package gb.internship.rest.requestcontrollers.admin;

import gb.internship.rest.dataobjects.TablePersons;
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
 * Операции с личностями в админском интерфейсе.
 *
 * @author Aleksandr Vvedensky
 */
@RestController
public class AdminUiPersonController {
    private Log LOG = LogFactory.getLog(AdminUiPersonController.class);
    private AdminUiSitesDbOperations adminUiSitesDbOperations;

    public AdminUiPersonController() {
        adminUiSitesDbOperations = new AdminUiSitesDbOperations();
    }

    /**
     * Работа с личностями. Отображение всех личностей.
     *
     * @return список всех личностей.
     */
    @RequestMapping("/admin/ui/getAllPersons")
    public List<TablePersons> getAllPersons() {
        List<TablePersons> resultList = new ArrayList<>();
        try {
            resultList = adminUiSitesDbOperations.getAllPersons();
        } catch (Exception ex) {
            LOG.warn("Error getting all persons data.");
            ex.printStackTrace();
        }

        return resultList;
    }

    /**
     * @param name   имя сайта.
     * @param active активен.
     * @return сообщение о статусе выполнения.
     */
    @RequestMapping("/admin/ui/addPerson")
    public ResponseEntity addPerson(@RequestParam(value = "name") String name,
                                    @RequestParam(value = "active") String active) {

        if ("".equals(name)) {
            LOG.warn("Error in /admin/ui/addPerson. name is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/addPerson. name is empty.");
        }
        if ("".equals(active)) {
            LOG.warn("Error in /admin/ui/addPerson. active is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/addPerson. active is empty.");
        }

        Boolean activeBooleanValue = Boolean.parseBoolean(active);
        try {
            adminUiSitesDbOperations.addPerson(name, activeBooleanValue);
        } catch (Exception ex) {
            LOG.warn("Error at run add person.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error at run add person.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body("OK");
    }


    /**
     * Удаление личностей.
     *
     * @param id уникальный идентификатор в таблице persons.
     * @return сообщение о статусе выполнения.
     * В случае корректного выполнения в теле ответа возвращается количество удалёных записей.
     */
    @RequestMapping("/admin/ui/delPerson")
    public ResponseEntity delPerson(@RequestParam(value = "id") Integer id) {
        int deletedRows;

        if (id == null) {
            LOG.warn("Error in /admin/ui/delPerson. id == null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error. id == null");
        }

        try {
            deletedRows = adminUiSitesDbOperations.delPerson(id);
        } catch (SQLException ex) {
            LOG.warn("Error at run del person.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error at run del person.");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(deletedRows);
    }

    @RequestMapping("/admin/ui/modifyPerson")
    public ResponseEntity modifySite(@RequestParam(value = "id") Integer id,
                                     @RequestParam(value = "name") String name,
                                     @RequestParam(value = "active") String active) {

        int updatedRows;

        if (id == null) {
            LOG.warn("Error in /admin/ui/modifyPerson. id == null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/modifyPerson. id == null");
        }
        if ("".equals(name)) {
            LOG.warn("Error in /admin/ui/modifyPerson. name is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/modifyPerson. name is empty.");
        }
        if ("".equals(active)) {
            LOG.warn("Error in /admin/ui/modifyPerson. active is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/modifyPerson. active is empty.");
        }

        Boolean activeBooleanValue = Boolean.parseBoolean(active);

        try {
            updatedRows = adminUiSitesDbOperations.modifyPerson(id, name, activeBooleanValue);
        } catch (SQLException ex) {
            LOG.warn("Error at run modify person.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error at run modify person.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedRows);
    }

}
