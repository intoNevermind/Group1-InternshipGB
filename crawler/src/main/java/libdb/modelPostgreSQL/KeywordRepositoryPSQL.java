package libdb.modelPostgreSQL;

import libdb.entity.Keyword;
import libdb.repository.AbstractModelRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class KeywordRepositoryPSQL extends AbstractModelRepository<Keyword> {
    private static final String TABLE_NAME = "Keywords";
    private static final String ID = "ID";
    private static final Map<String, String> fields;

    static {
        HashMap<String, String> tempMap = new HashMap<String, String>();
        tempMap.put("ID", "ID");
        tempMap.put("Person", "PersonID");
        tempMap.put("Keyword", "Keyword");
        fields = Collections.unmodifiableMap(tempMap);
    }

    public KeywordRepositoryPSQL() {

    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static Map<String, String> getFields() {
        return fields;
    }
}
