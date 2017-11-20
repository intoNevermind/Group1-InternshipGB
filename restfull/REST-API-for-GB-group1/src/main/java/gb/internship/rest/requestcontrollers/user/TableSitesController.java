package gb.internship.rest.requestcontrollers.user;

import gb.internship.rest.db.operations.TableSitesOperations;
import gb.internship.rest.requestcontrollers.admin.AdminUiPersonController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agcheb on 14.11.17.
 */
public class TableSitesController {
    private Log LOG = LogFactory.getLog(AdminUiPersonController.class);
    private TableSitesOperations tableSitesOperations;

    public TableSitesController() {
        tableSitesOperations = new TableSitesOperations();
    }

    /**
     * Работа с сайтами.
     * Отображение списка ID сайтов.
     *
     * @return список всех ID сайтов.
     */
    @RequestMapping(value = {"/user/ui/getIDFromSitesTable","/unauthorized/user/ui/getIDFromSitesTable"})
    public List<Integer> getIDFromSitesTable() {
        List<Integer> resultList = new ArrayList<>();
        try {
            resultList = tableSitesOperations.getIDFromSitesTable();
        } catch (Exception ex) {
            LOG.warn("Error getting all sites ID.");
            ex.printStackTrace();
        }

        return resultList;
    }

    /**
     * Отображение списка имен сайтов.
     *
     * @return список всех имен сайтов.
     */
    @RequestMapping(value = {"/user/ui/getNameFromSitesTable", "/unauthorized/user/ui/getNameFromSitesTable"})
    public List<String> getNameFromSitesTable() {
        List<String> resultList = new ArrayList<>();
        try {
            resultList = tableSitesOperations.getNameFromSitesTable();
        } catch (Exception ex) {
            LOG.warn("Error getting all sites Names.");
            ex.printStackTrace();
        }

        return resultList;
    }


    /**
     * Отображение списка url сайтов.
     *
     * @return список всех url сайтов.
     */
    @RequestMapping(value = {"/user/ui/getURLFromSitesTable", "/unauthorized/user/ui/getURLFromSitesTable"})
    public List<String> getURLFromSitesTable() {
        List<String> resultList = new ArrayList<>();
        try {
            resultList = tableSitesOperations.getURLFromSitesTable();
        } catch (Exception ex) {
            LOG.warn("Error getting all sites URL.");
            ex.printStackTrace();
        }

        return resultList;
    }
    /**
     * Отображение списка состояний Active сайтов.
     *
     * @return список всех состояний Active сайтов.
     */
    @RequestMapping(value = {"/user/ui/getActiveFromSitesTable", "/unauthorized/user/ui/getActiveFromSitesTable"})
    public List<Boolean> getActiveFromSitesTable() {
        List<Boolean> resultList = new ArrayList<>();
        try {
            resultList = tableSitesOperations.getActiveFromSitesTable();
        } catch (Exception ex) {
            LOG.warn("Error getting all sites Active state.");
            ex.printStackTrace();
        }

        return resultList;
    }

}
