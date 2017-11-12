package libdb.modelPostgreSQL.person;

import libdb.SqlSpecification;
import libdb.entities.Person;

public class PersonSpecifications implements SqlSpecification {
    /**
     * Select all persons specification
     */
    public static class SelectOnlyActive implements SqlSpecification {
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
     * Select all persons specification
     */
    public static class Select implements SqlSpecification {
        private Boolean onlyActive;
        public Select(Boolean onlyActive) {
            this.onlyActive = onlyActive;
        }
        @Override
        public String toSqlQuery() {
            if (onlyActive)
                return new SelectOnlyActive().toSqlQuery();
            else
                return String.format(
                        "SELECT * FROM %1$s ORDER BY \"%2$s\";",
                        PersonRepository.getTableName(),
                        PersonRepository.getNameFieldDB("name")
                );
        }
    }

    /**
     * Select all by UserId persons specification
     */
    public static class SelectOnlyActiveByUserId implements SqlSpecification {
        private Long userId;
        public SelectOnlyActiveByUserId(Long userId) {
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

    /**
     * Select all by UserId persons specification
     */
    public static class SelectByUserId implements SqlSpecification {
        private Long userId;
        private Boolean onlyActive;
        public SelectByUserId(Long userId, Boolean onlyActive) {
            this.userId = userId;
            this.onlyActive = onlyActive;
        }
        @Override
        public String toSqlQuery() {
            if (onlyActive)
                return new SelectOnlyActiveByUserId(userId).toSqlQuery();
            else
                return String.format(
                        "SELECT * FROM %1$s WHERE \"%2$s\" = %3$d ORDER BY \"%4$s\";",
                        PersonRepository.getTableName(),
                        PersonRepository.getNameFieldDB("userId"),
                        userId,
                        PersonRepository.getNameFieldDB("name")
                );
        }
    }

    /**
     * Insert specification
     */
    public static class Insert implements SqlSpecification {
        private Person person;
        public Insert(Person person) {
            this.person = person;
        }
        @Override
        public String toSqlQuery() {
            return String.format(
                    "INSERT INTO %1$s(\"%2$s\", \"%3$s\", \"%4$s\") VALUES ('%5$s', %6$b, %7$d);",
                    PersonRepository.getTableName(),
                    PersonRepository.getNameFieldDB("name"),
                    PersonRepository.getNameFieldDB("active"),
                    PersonRepository.getNameFieldDB("userId"),
                    person.getName(),
                    person.getActive(),
                    person.getUserId()
            );
        }
    }


    /**
     * Update specification
     */
    public static class Update implements SqlSpecification {
        private Person person;
        public Update(Person person) {
            this.person = person;
        }
        @Override
        public String toSqlQuery() {
            return String.format(
                    "UPDATE %1$s SET \"%2$s\" = '%3$s', \"%3$s\" = %4$b WHERE \"%5$s\" = %6$d;",
                    PersonRepository.getTableName(),
                    PersonRepository.getNameFieldDB("name"),
                    person.getName(),
                    PersonRepository.getNameFieldDB("active"),
                    person.getActive(),
                    PersonRepository.getNameFieldDB("id"),
                    person.getId()
            );
        }
    }

    /**
     * Delete specification
     */
    public static class Delete implements SqlSpecification {
        private Person person;
        public Delete(Person person) {
            this.person = person;
        }
        @Override
        public String toSqlQuery() {
            return String.format(
                    "DELETE FROM %1$s WHERE \"%2$s\" = %3$d AND \"%4$s\" = %5$d;",
                    PersonRepository.getTableName(),
                    PersonRepository.getNameFieldDB("id"),
                    person.getId(),
                    PersonRepository.getNameFieldDB("userId"),
                    person.getUserId()
            );
        }
    }


    public String toSqlQuery() {
        return null;
    }
}
