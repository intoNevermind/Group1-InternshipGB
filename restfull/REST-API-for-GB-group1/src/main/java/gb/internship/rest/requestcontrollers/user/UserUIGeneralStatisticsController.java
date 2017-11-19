package gb.internship.rest.requestcontrollers.user;

import gb.internship.rest.dataobjects.PersonGeneralStatistic;
import gb.internship.rest.db.operations.UsersUiDbOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private UsersUiDbOperation userUiSitesDbOperations;

    public UserUIGeneralStatisticsController() {
        userUiSitesDbOperations = new UsersUiDbOperation();
    }

    /**
     * Получение общей статистики по всем личностям для выбранного сайта.
     *
     * @param site     ключевое слово.
     * @return список всех рейтингов личностей.
     */
    @RequestMapping(value = {"/user/ui/getGeneralStatistics", "/unauthorized/user/ui/getGeneralStatistics"})
    public ResponseEntity<?> getGeneralStatistic(@RequestParam(value = "site") String site) {

        if ("".equals(site)) {
            LOG.warn("Error in /user/ui/getGeneralStatistics. sitename is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error. site == \"\"");
        }


        try {
            List<PersonGeneralStatistic> resultList = userUiSitesDbOperations.getAllRatesForChoosenSite(site);;
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.warn("Error at run get general Statistic.");
            ex.printStackTrace();
            return new ResponseEntity<>("Error at run getGeneralStatistics.", HttpStatus.BAD_REQUEST);
        }
    }
}
