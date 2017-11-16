package gb.internship.rest.requestcontrollers.user;

import gb.internship.rest.db.operations.TablePersonOperations;
import gb.internship.rest.requestcontrollers.admin.AdminUiPersonController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agcheb on 13.11.17.
 */

@RestController
public class TablePersonController {
    private Log LOG = LogFactory.getLog(AdminUiPersonController.class);
    private TablePersonOperations tablePersonOperations;

    public TablePersonController() {
        tablePersonOperations = new TablePersonOperations();
    }

    /**
     * Работа с личностями.
     * Отображение списка ID личностей.
     *
     * @return список всех ID личностей.
     */
    @RequestMapping(value = {"/unauthorized/user/ui/getIDFromPersonTable"})
    public List<Integer> getIDFromPersonTable() {
        List<Integer> resultList = new ArrayList<>();
        try {
            resultList = tablePersonOperations.getIDFromPersonTable();
        } catch (Exception ex) {
            LOG.warn("Error getting all persons ID.");
            ex.printStackTrace();
        }

        return resultList;
    }

    /**
     * Отображение списка имен личностей.
     *
     * @return список всех имен личностей.
     */
    @RequestMapping(value = {"/unauthorized/user/ui/getNameFromPersonTable"})
    public List<String> getNameFromPersonTable() {
        List<String> resultList = new ArrayList<>();
        try {
            resultList = tablePersonOperations.getNameFromPersonTable();
        } catch (Exception ex) {
            LOG.warn("Error getting all persons Names.");
            ex.printStackTrace();
        }

        return resultList;
    }
    /**
     * Отображение списка состояний Active личностей.
     *
     * @return список всех состояний Active личностей.
     */
    @RequestMapping(value = {"/unauthorized/user/ui/getActiveFromPersonTable"})
    public List<Boolean> getActiveFromPersonTable() {
        List<Boolean> resultList = new ArrayList<>();
        try {
            resultList = tablePersonOperations.getActiveFromPersonTable();
        } catch (Exception ex) {
            LOG.warn("Error getting all persons Active state.");
            ex.printStackTrace();
        }

        return resultList;
    }

}
