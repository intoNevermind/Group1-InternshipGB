package libdb.modelPostgreSQL.keyword;

import libdb.AbstractModelRepository;
import libdb.SqlSpecification;
import libdb.entities.Keyword;
import libdb.entities.Person;
import libdb.modelPostgreSQL.person.PersonSpecifications;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class KeywordRepository extends AbstractModelRepository<Keyword> {
    private Connection connectDB;
    private static final String TABLE_NAME = "keyword";
    private List<Keyword> items = new ArrayList<Keyword>();
    SqlSpecification specification = new KeywordSpecifications();
    // map of fields table DB
    private static final Map<String, String> fields;
    static {
        HashMap<String, String> tempMap = new HashMap<String, String>();
        tempMap.put("id", "ID");
        tempMap.put("name", "Name");
        tempMap.put("personId", "PersonId");
        fields = Collections.unmodifiableMap(tempMap);
    }

    public KeywordRepository(Connection connectDB) {
        this.connectDB = connectDB;
    }

    /**
     * Select from database all keywords and return list
     * @return list of person's keywords
     * @throws SQLException
     */
    @Override
    public Collection<Keyword> getAll() throws SQLException {
        items.clear();
        Statement statement = connectDB.createStatement();
        ResultSet rs = statement.executeQuery(specification.select());
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
        ResultSet rs = statement.executeQuery(specification.selectAllByUserId(userId, onlyActive));
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
            int affectedRows = statement.executeUpdate(specification.insert(item),
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
        statement.executeUpdate(specification.update(item));
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
        statement.executeUpdate(specification.delete(item));
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
