package gb.internship.rest;

import gb.internship.rest.db.operations.UsersUiDbOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

/**
 * Тестирование класса UsersUiDbOperation.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersUiDbOperationTests {
    UsersUiDbOperation uiDbOperation = new UsersUiDbOperation();

    @Test
    public void test_getKeywordsByPersonId() {
        try {
            System.err.println(System.getProperty("user.dir"));
            Assert.assertTrue(!uiDbOperation.getKeywordsByPersonId(1).isEmpty());
        } catch (SQLException e) {
            Assert.fail();
        }
    }

}
