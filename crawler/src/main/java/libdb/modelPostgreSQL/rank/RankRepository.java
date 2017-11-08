package libdb.modelPostgreSQL.rank;

import libdb.AbstractModelRepository;
import libdb.entities.Keyword;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class RankRepository extends AbstractModelRepository<Keyword> {
    private Connection connectDB;
    private static final String TABLE_NAME = "personpagerank";
    private List<Keyword> items = new ArrayList<Keyword>();
    // map of fields table DB
    private static final Map<String, String> fields;
    static {
        HashMap<String, String> tempMap = new HashMap<String, String>();
        tempMap.put("personID", "PersonID");
        tempMap.put("pageID", "PageID");
        tempMap.put("rank", "Rank");
        fields = Collections.unmodifiableMap(tempMap);
    }

    public RankRepository(Connection connectDB) {
        this.connectDB = connectDB;
    }

    public Collection<Keyword> getAllByUser(Integer UserId) throws SQLException {
        Statement statement = connectDB.createStatement();
        ResultSet rs = statement.executeQuery(new RankSpecifications.ByUserId(UserId).toSqlQuery());
        while (rs.next()) {
            Keyword keyword = map(rs);
            items.add(keyword);
        }
        return items;
    }

    public static Keyword map(ResultSet rs) throws SQLException {
//        Keyword keyword = new Keyword(
//                rs.getInt(KeywordRepository.getNameFieldDB("id")),
//                rs.getString(KeywordRepository.getNameFieldDB("name")),
//                rs.getBoolean(KeywordRepository.getNameFieldDB("active")),
//                rs.getInt(KeywordRepository.getNameFieldDB("userId"))
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
