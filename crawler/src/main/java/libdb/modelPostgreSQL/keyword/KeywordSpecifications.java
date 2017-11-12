package libdb.modelPostgreSQL.keyword;

import libdb.SqlSpecification;
import libdb.entities.Keyword;
import libdb.entities.Person;
import libdb.modelPostgreSQL.person.PersonRepository;

public class KeywordSpecifications implements SqlSpecification {
    /**
     * All specification
     */
    public static class SelectAll implements SqlSpecification {
        @Override
        public String toSqlQuery() {
            return String.format(
                    "SELECT * FROM %1$s ORDER BY \"%2$s\";",
                    KeywordRepository.getTableName(),
                    KeywordRepository.getNameFieldDB("name")
            );
        }
    }

    /**
     * PersonID specification
     */
    public static class SelectByPersonID implements SqlSpecification {
        private Long personId;
        public SelectByPersonID(Long personId) {
            this.personId = personId;
        }
        @Override
        public String toSqlQuery() {
            return String.format(
                    "SELECT * FROM %1$s WHERE \"%2$s\" = %3$d ORDER BY \"%4$s\";",
                    KeywordRepository.getTableName(),
                    KeywordRepository.getNameFieldDB("personId"),
                    personId,
                    KeywordRepository.getNameFieldDB("name")
            );
        }
    }

    /**
     * Insert specification
     */
    public static class Insert implements SqlSpecification {
        private Keyword keyword;
        public Insert(Keyword keyword) {
            this.keyword = keyword;
        }
        @Override
        public String toSqlQuery() {
            return String.format(
                    "INSERT INTO %1$s(\"%2$s\", \"%3$s\") VALUES ('%5$s', %6$d);",
                    KeywordRepository.getTableName(),
                    KeywordRepository.getNameFieldDB("name"),
                    KeywordRepository.getNameFieldDB("personId"),
                    keyword.getName(),
                    keyword.getPersonId()
            );
        }
    }


    /**
     * Update specification
     */
    public static class Update implements SqlSpecification {
        private Keyword keyword;
        public Update(Keyword keyword) {
            this.keyword = keyword;
        }
        @Override
        public String toSqlQuery() {
            return String.format(
                    "UPDATE %1$s SET \"%2$s\" = '%3$s' WHERE \"%5$s\" = %6$d;",
                    KeywordRepository.getTableName(),
                    KeywordRepository.getNameFieldDB("name"),
                    keyword.getName(),
                    KeywordRepository.getNameFieldDB("id"),
                    keyword.getId()
            );
        }
    }


    /**
     * Delete PersonId specification
     */
    public static class DeleteByPersonId implements SqlSpecification {
        private Person person;
        public DeleteByPersonId(Person person) {
            this.person = person;
        }
        @Override
        public String toSqlQuery() {
            return String.format(
                    "DELETE FROM %1$s WHERE \"%2$s\" = %3$d;",
                    KeywordRepository.getTableName(),
                    KeywordRepository.getNameFieldDB("personId"),
                    person.getId()
            );
        }
    }


    /**
     * Delete Keyword specification
     */
    public static class Delete implements SqlSpecification {
        private Keyword keyword;
        public Delete(Keyword keyword) {
            this.keyword = keyword;
        }
        @Override
        public String toSqlQuery() {
            return String.format(
                    "DELETE FROM %1$s WHERE \"%2$s\" = %3$d;",
                    KeywordRepository.getTableName(),
                    KeywordRepository.getNameFieldDB("id"),
                    keyword.getId()
            );
        }
    }

    public String toSqlQuery() {
        return null;
    }
}
