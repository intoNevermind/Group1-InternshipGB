package gb.internship.rest.requestcontrollers;

import gb.internship.rest.db.operations.UsersUiSitesDbOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * Created by agcheb on 03.11.17.
 */
@RestController
public class UserUISitesController {

    private Log LOG = LogFactory.getLog(UserUISitesController.class);

    private UsersUiSitesDbOperation userUiSitesDbOperations;

    public UserUISitesController() throws SQLException {
        userUiSitesDbOperations = new UsersUiSitesDbOperation();
    }

}
