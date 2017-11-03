package gb.internship.rest.db.operations;

import gb.internship.rest.db.DbWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;

/**
 * @author баранов
 * Класс для работы с базой с User UI
 */


public class UsersUiSitesDbOperation {
    private Log LOG = LogFactory.getLog(UsersUiSitesDbOperation.class);

    private Connection connection;

    public UsersUiSitesDbOperation() {
        this.connection = DbWrapper.getInstance().getConnection();
    }

}
