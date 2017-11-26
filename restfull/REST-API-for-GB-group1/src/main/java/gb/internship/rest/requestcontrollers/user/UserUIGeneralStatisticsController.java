package gb.internship.rest.requestcontrollers.user;

import gb.internship.rest.dataobjects.PersonDailyStatistic;
import gb.internship.rest.dataobjects.PersonGeneralStatistic;
import gb.internship.rest.db.operations.UsersUiDbOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.sql.Date;
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
     * @param siteID     site index.
     * @return список всех рейтингов личностей.
     */
    @RequestMapping(value = {"/user/ui/getGeneralStatistics", "/unauthorized/user/ui/getGeneralStatistics"})
    public ResponseEntity<?> getGeneralStatistic(@RequestParam(value = "siteID") Integer siteID) {

        if (siteID == null) {
            LOG.warn("Error in /user/ui/getGeneralStatistics. sitename is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error. site == \"\"");
        }


        try {
            List<PersonGeneralStatistic> resultList = userUiSitesDbOperations.getAllRatesForChoosenSite(siteID);;
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.warn("Error at run get general Statistic.");
            ex.printStackTrace();
            return new ResponseEntity<>("Error at run getGeneralStatistics.", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Получение ежедневной статистики по всем личностям для выбранного сайта в выбранный диапазон дат.
     *
     * @param siteID     site index.
     * @param dateFrom
     * @param dateTo
     * @return список всех рейтингов личностей.
     */
    @RequestMapping(value = {"/user/ui/getDailyStatistics", "/unauthorized/user/ui/getDailyStatistics"})
    public ResponseEntity<?> getDailyStatistic(@RequestParam(value = "siteID") Integer siteID,
                                               @RequestParam(value = "datefrom") Date dateFrom,
                                               @RequestParam(value = "dateto") Date dateTo) {

        if (siteID == null) {
            LOG.warn("Error in /user/ui/getDailyStatistics. sitename is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error. site == \"\"");
        }
        if (dateFrom == null) {
            LOG.warn("Error in /user/ui/getDailyStatistics. dateFrom is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error. site == \"\"");
        }
        if (dateTo == null) {
            LOG.warn("Error in /user/ui/getDailyStatistics. dateTo is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error. site == \"\"");
        }
        try {
            List<PersonDailyStatistic> resultList = userUiSitesDbOperations.getPersonDailyStatistic(siteID,dateFrom,dateTo);
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.warn("Error at run get daily Statistic.");
            ex.printStackTrace();
            return new ResponseEntity<>("Error at run getDailyStatistics.", HttpStatus.BAD_REQUEST);
        }
    }

}
