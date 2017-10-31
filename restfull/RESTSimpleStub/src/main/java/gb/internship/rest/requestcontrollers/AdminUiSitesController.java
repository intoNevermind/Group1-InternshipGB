package gb.internship.rest.requestcontrollers;

import gb.internship.rest.dataobjects.TablePersons;
import gb.internship.rest.db.operations.AdminUiSitesDbOperations;
import gb.internship.rest.dataobjects.TableSites;
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
 * REST контроллер интерфейса администратора для работа с элементами справичника (сайтами).
 *
 * @author Aleksandr Vvedensky
 */
@RestController
public class AdminUiSitesController {
    private Log LOG = LogFactory.getLog(AdminUiSitesController.class);

    private AdminUiSitesDbOperations adminUiSitesDbOperations;

    public AdminUiSitesController() throws SQLException {
        adminUiSitesDbOperations = new AdminUiSitesDbOperations();
    }

    /**
     * Работа с элементами справичника (сайтами). Отображение всех элементов.
     *
     * @return список всех сайтов.
     */
    @RequestMapping("/admin/ui/getAllSites")
    public List<TableSites> getAllSites() {
        List<TableSites> resultList = new ArrayList<>();
        try {
            resultList = adminUiSitesDbOperations.getAllSites();
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
            adminUiSitesDbOperations.addSite(name, url, activeBooleanValue);
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
        int deletedRows;

        if (id == null) {
            LOG.warn("Error in /admin/ui/delSite. id == null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error. id == null");
        }

        try {
            deletedRows = adminUiSitesDbOperations.delSite(id);
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

        int updatedRows;

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
            updatedRows = adminUiSitesDbOperations.modifySite(id, name, url, activeBooleanValue);
        } catch (SQLException ex) {
            LOG.warn("Error at run modify site.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error at run modify site.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedRows);
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
