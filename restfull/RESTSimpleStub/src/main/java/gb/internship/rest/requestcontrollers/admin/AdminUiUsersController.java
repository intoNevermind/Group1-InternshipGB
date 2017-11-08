package gb.internship.rest.requestcontrollers.admin;

import gb.internship.rest.dataobjects.TableUsers;
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
 * Операции с пользователями в админском интерфейсе.
 *
 * @author Aleksandr Vvedensky
 */
@RestController
public class AdminUiUsersController {
    private Log LOG = LogFactory.getLog(AdminUiSitesController.class);
    private AdminUiSitesDbOperations adminUiSitesDbOperations;

    public AdminUiUsersController() {
        adminUiSitesDbOperations = new AdminUiSitesDbOperations();
    }

    /**
     * Отображение всех пользователей.
     *
     * @return список всех пользователей.
     */
    @RequestMapping("/admin/ui/getAllUsers")
    public List<TableUsers> getAllUsers() {
        List<TableUsers> resultList = new ArrayList<>();
        try {
            resultList = adminUiSitesDbOperations.getAllUsers();
        } catch (Exception ex) {
            LOG.warn("Error getting all Users data.");
            ex.printStackTrace();
        }

        return resultList;
    }


    /**
     * Добавление пользователя.
     *
     * @param login    логин.
     * @param admin    является администратором.
     * @param password пароль.
     * @param active   активен.
     * @return сообщение о статусе выполнения.
     */
    @RequestMapping("/admin/ui/addUser")
    public ResponseEntity addSite(@RequestParam(value = "login") String login,
                                  @RequestParam(value = "admin") String admin,
                                  @RequestParam(value = "password") String password,
                                  @RequestParam(value = "active") String active) {

        if ("".equals(login)) {
            LOG.warn("Error in /admin/ui/addUser. name is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/addUser. name is empty.");
        }
        if ("".equals(admin)) {
            LOG.warn("Error in /admin/ui/addUser. url is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/addUser. url is empty.");
        }
        if ("".equals(password)) {
            LOG.warn("Error in /admin/ui/addUser. url is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/addUser. url is empty.");
        }
        if ("".equals(active)) {
            LOG.warn("Error in /admin/ui/addUser. active is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/addUser. active is empty.");
        }

        Boolean adminBooleanValue = Boolean.parseBoolean(admin);
        Boolean activeBooleanValue = Boolean.parseBoolean(active);
        try {
            adminUiSitesDbOperations.addUser(login, adminBooleanValue, password, activeBooleanValue);
        } catch (Exception ex) {
            LOG.warn("Error at run add User.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error at run add User.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body("OK");
    }


    /**
     * @param id
     * @return
     */
    @RequestMapping("/admin/ui/delUser")
    public ResponseEntity delSite(@RequestParam(value = "id") Integer id) {
        int deletedRows;

        if (id == null) {
            LOG.warn("Error in /admin/ui/delUser. id == null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error. id == null");
        }

        try {
            deletedRows = adminUiSitesDbOperations.delUser(id);
        } catch (SQLException ex) {
            LOG.warn("Error at run del User.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error at run del User.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(deletedRows);
    }

    /**
     * Удаление пользователя.
     *
     * @param id       уникальный идентификатор в таблице sites.
     * @param login    логин.
     * @param admin    является администратором.
     * @param password пароль.
     * @param active   активен.
     * @return сообщение о статусе выполнения.
     */
    @RequestMapping("/admin/ui/modifyUser")
    public ResponseEntity modifySite(@RequestParam(value = "id") Integer id,
                                     @RequestParam(value = "login") String login,
                                     @RequestParam(value = "admin") String admin,
                                     @RequestParam(value = "password") String password,
                                     @RequestParam(value = "active") String active) {
        int updatedRows;

        if (id == null) {
            LOG.warn("Error in /admin/ui/modifyUser. id == null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/modifyUser. id == null");
        }
        if ("".equals(login)) {
            LOG.warn("Error in /admin/ui/modifyUser. name is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/modifyUser. name is empty.");
        }
        if ("".equals(admin)) {
            LOG.warn("Error in /admin/ui/modifyUser. url is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/modifyUser. url is empty.");
        }
        if ("".equals(password)) {
            LOG.warn("Error in /admin/ui/modifyUser. active is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/modifyUser. active is empty.");
        }
        if ("".equals(active)) {
            LOG.warn("Error in /admin/ui/modifyUser. active is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error in /admin/ui/modifyUser. active is empty.");
        }

        Boolean adminBooleanValue = Boolean.parseBoolean(admin);
        Boolean activeBooleanValue = Boolean.parseBoolean(active);

        try {
            updatedRows = adminUiSitesDbOperations
                    .modifyUser(id, login, adminBooleanValue, password, activeBooleanValue);
        } catch (SQLException ex) {
            LOG.warn("Error at run modify User.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error at run modify User.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedRows);
    }
}
