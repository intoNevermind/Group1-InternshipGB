package gb.internship.rest.requestcontrollers.user;

import gb.internship.rest.dataobjects.TableKeywords;
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
 * Операции с табилцей keywords.
 *
 * @author Aleksandr Vvedensky
 */
@RestController
public class UserUiKeywords {
    private Log LOG = LogFactory.getLog(UserUiKeywords.class);
    private UsersUiDbOperation usersUiDbOperation;

    public UserUiKeywords() {
        usersUiDbOperation = new UsersUiDbOperation();
    }

    /**
     * Получение списка keywords.
     *
     * @param id идентификатор личности.
     * @return Список keywords или ошибку.
     */
    @RequestMapping(value = {"/user/ui/getKeywordsByPersonId",
            "/unauthorized/user/ui/getKeywordsByPersonId"})
    public ResponseEntity<?> getKeywordsByPersonId(@RequestParam(value = "id") Integer id) {
        if (id == null) {
            LOG.warn("Error in /admin/ui/getKeywordsByPersonId. id == null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error. id == null");
        }

        try {
            List<TableKeywords> resultList = usersUiDbOperation.getKeywordsByPersonId(id);
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        } catch (SQLException ex) {
            LOG.warn("Error at run getKeywordsByPersonId.");
            ex.printStackTrace();
            return new ResponseEntity<>("Error at run getKeywordsByPersonId.", HttpStatus.BAD_REQUEST);
        }
    }
}
