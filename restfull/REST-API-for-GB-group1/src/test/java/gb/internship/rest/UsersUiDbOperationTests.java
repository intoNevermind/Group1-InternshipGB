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
 *
 * @author Aleksandr Vvedensky
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersUiDbOperationTests {
    UsersUiDbOperation uiDbOperation = new UsersUiDbOperation();

    /**
     * Тестирование получения из базы ключевых слов по идентификатору пользователя.
     */
    @Test
    public void test_getKeywordsByPersonId() {
        try {
            Assert.assertTrue(!uiDbOperation.getKeywordsByPersonId(1).isEmpty());
        } catch (SQLException e) {
            Assert.fail();
        }
    }

}
