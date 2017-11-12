package libdb.modelPostgreSQL.site;

import libdb.SqlSpecification;
import libdb.entities.Site;
import libdb.AbstractModelRepository;
import libdb.entities.User;
import libdb.modelPostgreSQL.rank.RankSpecifications;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class SiteRepository extends AbstractModelRepository<Site> {
    private Connection connectDB;
    private static final String TABLE_NAME = "persons";
    private List<Site> items = new ArrayList<Site>();

    // map of fields table DB
    private static final Map<String, String> fields;
    static {
        HashMap<String, String> tempMap = new HashMap<String, String>();
        tempMap.put("id", "ID");
        tempMap.put("name", "Name");
        tempMap.put("url", "URL");
        tempMap.put("active", "Active");
        tempMap.put("userId", "UserId");
        fields = Collections.unmodifiableMap(tempMap);
    }

    public SiteRepository(Connection connectDB) {
        this.connectDB = connectDB;
    }

    /**
     * Select from database all sites and return list
     * @return list of user's sites
     * @throws SQLException
     */
    @Override
    public Collection<Site> getAll(Boolean onlyActive) throws SQLException {
        items.clear();
        Statement statement = connectDB.createStatement();
        ResultSet rs = statement.executeQuery(new SiteSpecifications.Select(onlyActive).toSqlQuery());
        while (rs.next()) {
            Site site = map(rs);
            rs.getLong(SiteRepository.getNameFieldDB("userId"));
            items.add(site);
        }
        statement.close();
        return items;
    }

    @Override
    public Collection<Site> getAll() throws SQLException {
        return getAll(false);
    }

    /**
     * Select from database user's persons and return list
     * @param userId
     * @return list of user's persons
     * @throws SQLException
     */
    @Override
    public Collection<Site> getAll(final Long userId, Boolean onlyActive) throws SQLException {
        items.clear();
        Statement statement = connectDB.createStatement();
        ResultSet rs = statement.executeQuery(new SiteSpecifications.SelectByUserId(userId, onlyActive).toSqlQuery());
        while (rs.next()) {
            Site site = map(rs);
            site.setUserId(userId);
            items.add(site);
        }
        statement.close();
        return items;
    }

    @Override
    public Collection<Site> getAll(final Long userId) throws SQLException {
        return getAll(userId, false);
    }

    /**
     * Add site in the database
     * @param item
     * @throws SQLException
     */
    @Override
    public void add(final Site item) throws SQLException {
        if (!items.contains(item) && item.getId().equals(0)) {
            Statement statement = connectDB.createStatement();
            int affectedRows = statement.executeUpdate(new SiteSpecifications.Insert(item).toSqlQuery(),
                    Statement.RETURN_GENERATED_KEYS);
            if (affectedRows == 0) {
                throw new SQLException("Creating site failed, no rows affected.");
            }
            try {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Creating site failed, no ID obtained.");
                }
                items.add(item);
            } catch (Exception ex) {
                throw new SQLException("Creating site failed, no ID obtained.");
            }
        }
    }

    /**
     * Update site in the database
     * @param item
     * @throws SQLException
     */
    @Override
    public void update(Site item) throws SQLException {
        Statement statement = connectDB.createStatement();
        statement.executeUpdate(new SiteSpecifications.Update(item).toSqlQuery());
        statement.close();
        items.remove(item);
    }

    /**
     * Try delete site in the database
     * @param item
     * @throws SQLException
     */
    @Override
    public void delete(Site item) throws SQLException {
        Statement statement = connectDB.createStatement();
        statement.executeUpdate(new SiteSpecifications.Delete(item).toSqlQuery());
        statement.close();
        items.remove(item);
    }

    /**
     * Map ResultSet to Site and return it
     * @param rs
     * @return person
     * @throws SQLException
     */
    public Site map(ResultSet rs) throws SQLException {
        Site person = new Site(
                rs.getLong(getNameFieldDB("id")),
                rs.getString(getNameFieldDB("name")),
                rs.getString(getNameFieldDB("url")),
                rs.getBoolean(getNameFieldDB("active")),
                rs.getLong(getNameFieldDB("userId"))
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
