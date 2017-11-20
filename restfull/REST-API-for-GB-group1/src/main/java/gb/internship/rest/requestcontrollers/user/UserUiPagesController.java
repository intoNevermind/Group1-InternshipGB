package gb.internship.rest.requestcontrollers.user;

import gb.internship.rest.dataobjects.TablePages;
import gb.internship.rest.db.operations.UsersUiDbOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by Баранов
 * Операции с табилцей Pages
 */

@RestController
public class UserUiPagesController {
    private Log LOG = LogFactory.getLog(UserUiPersonPageRank.class);
    private UsersUiDbOperation usersUiDbOperation;

   public UserUiPagesController () throws SQLException{
       usersUiDbOperation = new UsersUiDbOperation();
   }

//
//   @RequestMapping (value = {"{/user/ui/getPersonsOfLastScanDate",
//           "/unauthorized/user/ui/getPersonsOfLastScanDate"})
//        public  List<TablePages> getPersons(){
//       List<TablePages> resultlist = new ArrayList<>();
//       try {
//           resultlist = usersUiDbOperation.getPersonsOfLastScanDate();
//
//       }catch (SQLException e){
//           e.printStackTrace();
//            }
//            return resultlist;
//   }

}






