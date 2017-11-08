import libdb.Repository;
import libdb.entities.Person;
import libdb.modelPostgreSQL.person.PersonRepository;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

/**
 * Created by zarodov on 02.11.2017.
 */
public class TestWorkDB {
    static Properties prop = new Properties();
    static Connection connectDB;
    public static void main(String[] args) {
        InputStream input = null;
        try {
            // Load properties from file
            input = new FileInputStream("connection.properties");
            prop.load(input);
            // Create connecion DB
            Class.forName("org.postgresql.Driver");
            connectDB = DriverManager.getConnection("jdbc:postgresql://" + prop.getProperty("server") + "/" + prop.getProperty("database"), prop.getProperty("dbuser"), prop.getProperty("dbpassword"));
            System.out.println(connectDB.getClientInfo());
            Repository<Person> repository = new PersonRepository(connectDB);
            List<Person> list = (List<Person>) repository.getAll();
            for(Person person : list) {
                System.out.println(person);
            }
            connectDB.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
