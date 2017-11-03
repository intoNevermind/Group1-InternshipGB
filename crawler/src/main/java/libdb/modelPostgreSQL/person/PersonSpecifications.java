package libdb.modelPostgreSQL.person;

import libdb.SqlSpecification;

public class PersonSpecifications implements SqlSpecification {

    /**
     * All persons specification
     *
     */
    public static class AllPerson implements SqlSpecification {
        public AllPerson() {
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
