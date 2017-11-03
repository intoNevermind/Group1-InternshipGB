package libdb.modelPostgreSQL.keyword;

import libdb.SqlSpecification;

public class KeywordSpecifications implements SqlSpecification {
    /**
     * UserId specification
     *
     */
    public static class ByUserId implements SqlSpecification {
        private Integer userId;

        public ByUserId(final Integer userId) {
            this.userId = userId;
        }

        @Override
        public String toSqlQuery() {
            return String.format(
                    "SELECT * FROM %1$s WHERE \"%2$s\" = %3$d ORDER BY \"%4$s\";",
                    KeywordRepository.getTableName(),
                    KeywordRepository.getNameFieldDB("userId"),
                    userId,
                    KeywordRepository.getNameFieldDB("name")
            );
        }
    }

    /**
     * PersonID and UserId specification
     *
     */
    public static class ByPersonIdAndUserId implements SqlSpecification {
        private Integer personId;
        private Integer userId;

        public ByPersonIdAndUserId(final Integer personId, final Integer userId) {
            this.personId = personId;
            this.userId = userId;
        }

        @Override
        public String toSqlQuery() {
            return String.format(
                    "SELECT * FROM %1$s WHERE \"%2$s\" = %3$d AND \"%4$s\" = %5$d ORDER BY \"%4$s\";",
                    KeywordRepository.getTableName(),
                    KeywordRepository.getNameFieldDB("personId"),
                    personId,
                    KeywordRepository.getNameFieldDB("userId"),
                    userId,
                    KeywordRepository.getNameFieldDB("name")
            );
        }
    }

    public String toSqlQuery() {
        return null;
    }
}
