package libdb.modelPostgreSQL.person;

import libdb.entities.Person;
import libdb.AbstractModelRepository;
import libdb.entities.User;
import libdb.modelPostgreSQL.keyword.KeywordSpecifications;
import libdb.modelPostgreSQL.rank.RankSpecifications;

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
     * Add person in the database
     * @param item
     * @throws SQLException
     */
    @Override
    public void add(final Person item) throws SQLException {
        if (!items.contains(item) && item.getId().equals(0)) {
            Statement statement = connectDB.createStatement();
            int affectedRows = statement.executeUpdate(new PersonSpecifications.InsertPerson(item).toSqlQuery(),
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
                super.add(item);
            } catch (Exception ex) {
                throw new SQLException("Creating person failed, no ID obtained.");
            }
        }
    }

    /**
     * Select from database all persons and return list
     * @return list of user's persons
     * @throws SQLException
     */
    @Override
    public Collection<Person> getAll() throws SQLException {
        items.clear();
        Statement statement = connectDB.createStatement();
        ResultSet rs = statement.executeQuery(new PersonSpecifications.AllPerson().toSqlQuery());
        while (rs.next()) {
            Person person = map(rs);
            rs.getLong(PersonRepository.getNameFieldDB("userId"));
            items.add(person);
        }
        statement.close();
        return items;
    }

    /**
     * Select from database user's persons and return list
     * @param user
     * @return list of user's persons
     * @throws SQLException
     */
    @Override
    public Collection<Person> getAllByUser(final User user) throws SQLException {
        items.clear();
        Statement statement = connectDB.createStatement();
        ResultSet rs = statement.executeQuery(new PersonSpecifications.ByUserId(user.getId()).toSqlQuery());
        while (rs.next()) {
            Person person = map(rs);
            person.setUser(user);
            items.add(person);
        }
        statement.close();
        return items;
    }

    /**
     * Update person in the database
     * @param item
     * @throws SQLException
     */
    @Override
    public void update(Person item) throws SQLException {
        Statement statement = connectDB.createStatement();
        statement.executeUpdate(new PersonSpecifications.UpdatePerson(item).toSqlQuery());
        statement.close();
        super.delete(item);
    }

    /**
     * Try delete person in the database
     * @param item
     * @throws SQLException
     */
    @Override
    public void delete(Person item) throws SQLException {
        Statement statement = connectDB.createStatement();
        statement.executeUpdate(new PersonSpecifications.DeletePerson(item).toSqlQuery());
        statement.close();
        super.delete(item);
    }

    /**
     * Cascade delete in the database: keywords, ranks, person
     * @param item
     * @throws SQLException
     */
    @Override
    public void deleteCascade(Person item) throws SQLException {
        Statement statement = connectDB.createStatement();
        try {
            connectDB.setAutoCommit(false);
            // Delete keywords
            statement.addBatch(new KeywordSpecifications.DeleteKeywordsByPersonId(item).toSqlQuery());
            // Delete ranks
            statement.addBatch(new RankSpecifications.DeleteRanksByPersonId(item).toSqlQuery());
            // Delete person
            statement.addBatch(new PersonSpecifications.DeletePerson(item).toSqlQuery());
            statement.executeBatch();
            connectDB.commit();
            super.deleteCascade(item);
        } catch (SQLException ex) {
            throw ex;
        } finally {
            connectDB.setAutoCommit(true);
            statement.close();
        }
    }

    /**
     * Map ResultSet to Person and return it
     * @param rs
     * @return person
     * @throws SQLException
     */
    public static Person map(ResultSet rs) throws SQLException {
        Person person = new Person(
                rs.getLong(PersonRepository.getNameFieldDB("id")),
                rs.getString(PersonRepository.getNameFieldDB("name")),
                rs.getBoolean(PersonRepository.getNameFieldDB("active"))
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
