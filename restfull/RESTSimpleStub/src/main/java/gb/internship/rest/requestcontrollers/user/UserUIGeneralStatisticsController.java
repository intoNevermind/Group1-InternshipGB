package gb.internship.rest.requestcontrollers.user;

import gb.internship.rest.dataobjects.PersonGeneralStatistic;
import gb.internship.rest.db.operations.UsersUiSitesDbOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agcheb on 03.11.17.
 */
@RestController
public class UserUIGeneralStatisticsController {

    private Log LOG = LogFactory.getLog(UserUIGeneralStatisticsController.class);

    private UsersUiSitesDbOperation userUiSitesDbOperations;

    public UserUIGeneralStatisticsController() {
        userUiSitesDbOperations = new UsersUiSitesDbOperation();
    }

    /**
     * Получение общей статистики по всем личностям для выбранного сайта.
     *
     * @param site     ключевое слово.
     * @return список всех рейтингов личностей.
     */
    @RequestMapping("/user/ui/getGeneralStatistics")
    public List<PersonGeneralStatistic> getGeneralStatistic(@RequestParam(value = "site") String site) {
        List<PersonGeneralStatistic> resultList = new ArrayList<>();
        if ("".equals(site)) {
            LOG.warn("Error in /user/ui/getGeneralStatistics. sitename is empty.");
            throw new RuntimeException("Error in /user/ui/getGeneralStatistics. sitename is empty.");
        }


        try {
            resultList = userUiSitesDbOperations.getAllRatesForChoosenSite(site);
        } catch (Exception ex) {
            LOG.warn("Error at run get general Statistic.");
            ex.printStackTrace();
        }

        return resultList;
    }
}
