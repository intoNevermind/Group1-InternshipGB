package libdb.modelPostgreSQL.person;

import libdb.SqlSpecification;
import libdb.entities.Person;

public class PersonSpecifications implements SqlSpecification {

    /**
     * All persons specification
     *
     */
    public static class AllPerson implements SqlSpecification {
        public AllPerson() {
        }

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
     * UserId persons specification
     *
     */
    public static class ByUserId implements SqlSpecification {
        private Long userId;

        public ByUserId(final Long userId) {
            this.userId = userId;
        }

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

    /**
     * Update specification
     *
     */
    public static class UpdatePerson implements SqlSpecification {
        private Person person;

        public UpdatePerson(final Person person) {
            this.person = person;
        }

        public String toSqlQuery() {
            return String.format(
                    "UPDATE %1$s SET \"%2$s\" = '%3$s', \"%3$s\" = %4$b WHERE \"%5$s\" = %6$d;",
                    PersonRepository.getTableName(),
                    PersonRepository.getNameFieldDB("name"),
                    person.getName(),
                    PersonRepository.getNameFieldDB("active"),
                    person.getActive(),
                    PersonRepository.getNameFieldDB("personId"),
                    person.getId()
            );
        }
    }

    /**
     * INSERT specification
     *
     */
    public static class InsertPerson implements SqlSpecification {
        private Person person;

        public InsertPerson(final Person person) {
            this.person = person;
        }

        public String toSqlQuery() {
            return String.format(
                    "INSERT INTO %1$s(\"%2$s\", \"%3$s\", \"%4$s\") VALUES ('%5$s', %6$b, %7$d);",
                    PersonRepository.getTableName(),
                    PersonRepository.getNameFieldDB("name"),
                    PersonRepository.getNameFieldDB("active"),
                    PersonRepository.getNameFieldDB("userId"),
                    person.getName(),
                    person.getActive(),
                    person.getUser().getId()
            );
        }
    }

    /**
     * DELETE specification
     *
     */
    public static class DeletePerson implements SqlSpecification {
        private Person person;

        public DeletePerson(final Person person) {
            this.person = person;
        }

        public String toSqlQuery() {
            return String.format(
                    "DELETE FROM %1$s WHERE \"%2$s\" = %3$d AND \"%4$s\" = %5$d;",
                    PersonRepository.getTableName(),
                    PersonRepository.getNameFieldDB("id"),
                    person.getId(),
                    PersonRepository.getNameFieldDB("userId"),
                    person.getUser().getId()
            );
        }
    }

    public String toSqlQuery() {
        return null;
    }
}
