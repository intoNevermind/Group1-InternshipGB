package gb.internship.rest.requestcontrollers.user;

import gb.internship.rest.db.operations.UsersUiSitesDbOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * Created by agcheb on 03.11.17.
 */
@RestController
public class UserUIGeneralStatisticsController {

    private Log LOG = LogFactory.getLog(UserUIGeneralStatisticsController.class);

    private UsersUiSitesDbOperation userUiSitesDbOperations;

    public UserUIGeneralStatisticsController() throws SQLException {
        userUiSitesDbOperations = new UsersUiSitesDbOperation();
    }

}
