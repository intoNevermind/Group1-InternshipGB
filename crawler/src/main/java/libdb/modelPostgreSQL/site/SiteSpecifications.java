package libdb.modelPostgreSQL.site;

import libdb.SqlSpecification;
import libdb.modelPostgreSQL.person.PersonRepository;

public class SiteSpecifications implements SqlSpecification {

    /**
     * All sites specification
     *
     */
    public static class AllSite implements SqlSpecification {
        public AllSite() {
        }

        @Override
        public String toSqlQuery() {
            return String.format(
                    "SELECT * FROM %1$s WHERE \"%2$s\" = %3$b ORDER BY \"%4$s\";",
                    PersonRepository.getTableName(),
                    PersonRepository.getNameFieldDB("active"),
                    true,
                    PersonRepository.getNameFieldDB("name")
            );
        }
    }

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
                    "SELECT * FROM %1$s WHERE \"%2$s\" = %3$d AND \"%4$s\" = %5$b ORDER BY \"%6$s\";",
                    PersonRepository.getTableName(),
                    PersonRepository.getNameFieldDB("userId"),
                    userId,
                    PersonRepository.getNameFieldDB("active"),
                    true,
                    PersonRepository.getNameFieldDB("name")
            );
        }
    }

    public String toSqlQuery() {
        return null;
    }
}
