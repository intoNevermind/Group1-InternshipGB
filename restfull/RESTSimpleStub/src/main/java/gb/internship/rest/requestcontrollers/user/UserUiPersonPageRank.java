package gb.internship.rest.requestcontrollers.user;

import gb.internship.rest.dataobjects.TablePersonPageRank;
import gb.internship.rest.db.operations.UsersUiDbOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * Операции с табилцей PersonPageRank.
 *
 * @author Aleksandr Vvedensky
 */
@RestController
public class UserUiPersonPageRank {

    private Log LOG = LogFactory.getLog(UserUiPersonPageRank.class);
    private UsersUiDbOperation usersUiDbOperation;

    public UserUiPersonPageRank() {
        usersUiDbOperation = new UsersUiDbOperation();
    }

    /**
     * Возвращает список PersonPageRank по ID пользователя.
     *
     * @param id PersonID
     * @return Список PersonPageRank или ошибку.
     */
    @RequestMapping(value = {"/user/ui/getPersonPageRankByPersonId",
            "/unauthorized/user/ui/getPersonPageRankByPersonId"})
    public ResponseEntity<?> getPersonPageRankByPersonId(@RequestParam(value = "id") Integer id) {
        if (id == null) {
            LOG.warn("Error in /admin/ui/delUser. id == null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error. id == null");
        }

        try {
            List<TablePersonPageRank> resultList = usersUiDbOperation.getPersonPageRankByPersonId(id);
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        } catch (SQLException ex) {
            LOG.warn("Error at run getPersonPageRankByPersonId.");
            ex.printStackTrace();
            return new ResponseEntity<>("Error at run getPersonPageRankByPersonId.", HttpStatus.BAD_REQUEST);
        }
    }
}
