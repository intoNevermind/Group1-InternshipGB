package libdb.modelPostgreSQL.person;

import libdb.entities.Person;
import libdb.AbstractModelRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class PersonRepository extends AbstractModelRepository<Person> {
    private Connection connectDB;
    private static final String TABLE_NAME = "persons";
    private List<Person> items = new ArrayList<Person>();
    // map of fields table DB
    private static final Map<String, String> fields;
    static {
        HashMap<String, String> tempMap = new HashMap<String, String>();
        tempMap.put("id", "ID");
        tempMap.put("name", "Name");
        tempMap.put("active", "Active");
        tempMap.put("userId", "UserId");
        fields = Collections.unmodifiableMap(tempMap);
    }

    public PersonRepository(Connection connectDB) {
        this.connectDB = connectDB;
    }

    public Collection<Person> getAllByUser(Integer UserId) throws SQLException {
        Statement statement = connectDB.createStatement();
        ResultSet rs = statement.executeQuery(new PersonSpecifications.ByUserId(UserId).toSqlQuery());
        while (rs.next()) {
            Person person = map(rs);
            items.add(person);
        }
        return items;
    }

    public static Person map(ResultSet rs) throws SQLException {
//        Person person = new Person(
//                rs.getInt(PersonRepository.getNameFieldDB("id")),
//                rs.getString(PersonRepository.getNameFieldDB("name")),
//                rs.getBoolean(PersonRepository.getNameFieldDB("active")),
//                rs.getInt(PersonRepository.getNameFieldDB("userId"))
//        );
        return null;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static Map<String, String> getFields() {
        return fields;
    }

    public static String getNameFieldDB(String field) {
        return fields.get(field);
    }

}
