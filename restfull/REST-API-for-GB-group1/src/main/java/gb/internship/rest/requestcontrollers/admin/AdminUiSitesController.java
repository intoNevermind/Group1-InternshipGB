package gb.internship.rest.requestcontrollers.admin;

import gb.internship.rest.db.operations.AdminUiDbOperations;
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

    private AdminUiDbOperations adminUiDbOperations;

    public AdminUiSitesController() throws SQLException {
        adminUiDbOperations = new AdminUiDbOperations();
    }

    /**
     * Работа с элементами справичника (сайтами). Отображение всех элементов.
     *
     * @return список всех сайтов.
     */
    @RequestMapping(value = {"/admin/ui/getAllSites", "/unauthorized/admin/ui/getAllSites",
            "/user/ui/getAllSites", "/unauthorized/user/ui/getAllSites"})
    public List<TableSites> getAllSites() {
        List<TableSites> resultList = new ArrayList<>();
        try {
            resultList = adminUiDbOperations.getAllSites();
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
    @RequestMapping(value = {"/admin/ui/addSite", "/unauthorized/admin/ui/addSite",
            "/user/ui/addSite", "/unauthorized/user/ui/addSite"})
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
            adminUiDbOperations.addSite(name, url, activeBooleanValue);
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
    @RequestMapping(value = {"/admin/ui/delSite", "/unauthorized/admin/ui/delSite",
            "/user/ui/delSite", "/unauthorized/user/ui/delSite"})
    public ResponseEntity delSite(@RequestParam(value = "id") Integer id) {
        int deletedRows;

        if (id == null) {
            LOG.warn("Error in /admin/ui/delSite. id == null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error. id == null");
        }

        try {
            deletedRows = adminUiDbOperations.delSite(id);
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
    @RequestMapping(value = {"/admin/ui/modifySite", "/unauthorized/admin/ui/modifySite",
            "/user/ui/modifySite", "/unauthorized/user/ui/modifySite"})
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
            updatedRows = adminUiDbOperations.modifySite(id, name, url, activeBooleanValue);
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
