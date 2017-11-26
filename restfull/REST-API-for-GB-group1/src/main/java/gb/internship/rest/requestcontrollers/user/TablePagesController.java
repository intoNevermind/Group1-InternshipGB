package gb.internship.rest.requestcontrollers.user;

import gb.internship.rest.db.operations.TablePagesDbOperations;
import gb.internship.rest.requestcontrollers.admin.AdminUiPersonController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by agcheb on 18.11.17.
 */
@RestController
public class TablePagesController {
    private Log LOG = LogFactory.getLog(AdminUiPersonController.class);
    private TablePagesDbOperations tablePagesDbOperations;

    public TablePagesController() {
        tablePagesDbOperations = new TablePagesDbOperations();
    }

    /**
     * Работа с сстраницами.
     * Отображение списка ID страниц.
     *
     * @return список всех ID страниц.
     */
    @RequestMapping(value = {"/user/ui/getIDFromPagesTable","/unauthorized/user/ui/getIDFromPagesTable"})
    public List<Integer> getIDFromPagesTable() {
        List<Integer> resultList = new ArrayList<>();
        try {
            resultList = tablePagesDbOperations.getIDFromPagesTable();
        } catch (Exception ex) {
            LOG.warn("Error getting all pages ID.");
            ex.printStackTrace();
        }

        return resultList;
    }


    /**
     * Отображение списка ID сайтов, к которым относится страница.
     *
     * @return список всех ID сайтов.
     */
    @RequestMapping(value = {"/user/ui/getSiteIDFromPagesTable","/unauthorized/user/ui/getSiteIDFromPagesTable"})
    public List<Integer> getSiteIDFromPagesTable() {
        List<Integer> resultList = new ArrayList<>();
        try {
            resultList = tablePagesDbOperations.getSiteIDFromPagesTable();
        } catch (Exception ex) {
            LOG.warn("Error getting all sites ID.");
            ex.printStackTrace();
        }

        return resultList;
    }



    /**
     * Отображение списка url страниц.
     *
     * @return список всех url страниц.
     */
    @RequestMapping(value = {"/user/ui/getURLFromPagesTable","/unauthorized/user/ui/getURLFromPagesTable"})
    public List<String> getURLFromPagesTable() {
        List<String> resultList = new ArrayList<>();
        try {
            resultList = tablePagesDbOperations.getURLFromPagesTable();
        } catch (Exception ex) {
            LOG.warn("Error getting all pages URL.");
            ex.printStackTrace();
        }

        return resultList;
    }
    /**
     * Отображение списка FoundDateTime страницы.
     *
     * @return список всех FoundDateTime страницы.
     */
    @RequestMapping(value = {"/user/ui/getFoundDateTimeFromPagesTable","/unauthorized/user/ui/getFoundDateTimeFromPagesTable"})
    public List<Date> getFoundDateTimeFromPagesTable() {
        List<Date> resultList = new ArrayList<>();
        try {
            resultList = tablePagesDbOperations.getFoundDateTimeFromPagesTable();
        } catch (Exception ex) {
            LOG.warn("Error getting all pages FoundDateTime.");
            ex.printStackTrace();
        }

        return resultList;
    }


    /**
     * Отображение списка LastscanDate страницы.
     *
     * @return список всех LastScanDate страницы.
     */
    @RequestMapping(value = {"/user/ui/getLastScanDateFromPagesTable","/unauthorized/user/ui/getLastScanDateFromPagesTable"})
    public List<Date> getLastScanDateFromPagesTable() {
        List<Date> resultList = new ArrayList<>();
        try {
            resultList = tablePagesDbOperations.getLastScanDateFromPagesTable();
        } catch (Exception ex) {
            LOG.warn("Error getting all pages LastScanDate.");
            ex.printStackTrace();
        }

        return resultList;
    }

}
