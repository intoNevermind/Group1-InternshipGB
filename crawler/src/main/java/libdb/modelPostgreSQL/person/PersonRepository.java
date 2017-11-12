package libdb.modelPostgreSQL.person;

import libdb.SqlSpecification;
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

    /**
     * Select from database all persons and return list
     * @return list of user's persons
     * @throws SQLException
     */
    @Override
    public Collection<Person> getAll(Boolean onlyActive) throws SQLException {
        items.clear();
        Statement statement = connectDB.createStatement();
        ResultSet rs = statement.executeQuery(new PersonSpecifications.Select(onlyActive).toSqlQuery());
        while (rs.next()) {
            Person person = map(rs);
            items.add(person);
        }
        statement.close();
        return items;
    }

    @Override
    public Collection<Person> getAll() throws SQLException {
        return getAll(false);
    }

    /**
     * Select from database user's persons and return list
     * @param userId
     * @return list of user's persons
     * @throws SQLException
     */
    @Override
    public Collection<Person> getAll(final Long userId, Boolean onlyActive) throws SQLException {
        items.clear();
        Statement statement = connectDB.createStatement();
        ResultSet rs = statement.executeQuery(new PersonSpecifications.SelectByUserId(userId, onlyActive).toSqlQuery());
        while (rs.next()) {
            Person person = map(rs);
            items.add(person);
        }
        statement.close();
        return items;
    }

    @Override
    public Collection<Person> getAll(final Long userId) throws SQLException {
        return getAll(userId, false);
    }

    /**
     * Add person in the database
     * @param item
     * @throws SQLException
     */
    @Override
    public void add(final Person item) throws SQLException {
        if (!items.contains(item) && item.getId().equals(0)) {
            Statement statement = connectDB.createStatement();
            int affectedRows = statement.executeUpdate(new PersonSpecifications.Insert(item).toSqlQuery(),
                                                        Statement.RETURN_GENERATED_KEYS);
            if (affectedRows == 0) {
                throw new SQLException("Creating person failed, no rows affected.");
            }
            try {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Creating person failed, no ID obtained.");
                }
                items.add(item);
            } catch (Exception ex) {
                throw new SQLException("Creating person failed, no ID obtained.");
            }
        }
    }

    /**
     * Update person in the database
     * @param item
     * @throws SQLException
     */
    @Override
    public void update(Person item) throws SQLException {
        Statement statement = connectDB.createStatement();
        statement.executeUpdate(new PersonSpecifications.Update(item).toSqlQuery());
        statement.close();
        items.remove(item);
    }

    /**
     * Try delete person in the database
     * @param item
     * @throws SQLException
     */
    @Override
    public void delete(Person item) throws SQLException {
        Statement statement = connectDB.createStatement();
        statement.executeUpdate(new PersonSpecifications.Delete(item).toSqlQuery());
        statement.close();
        items.remove(item);
    }

    /**
     * Map ResultSet to Person and return it
     * @param rs
     * @return person
     * @throws SQLException
     */
    public Person map(ResultSet rs) throws SQLException {
        Person person = new Person(
                rs.getLong(PersonRepository.getNameFieldDB("id")),
                rs.getString(PersonRepository.getNameFieldDB("name")),
                rs.getBoolean(PersonRepository.getNameFieldDB("active")),
                rs.getLong(PersonRepository.getNameFieldDB("userId"))
        );
        return person;
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
