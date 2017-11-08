package libdb.modelPostgreSQL.rank;

import libdb.SqlSpecification;
import libdb.entities.Keyword;
import libdb.entities.Person;

public class RankSpecifications implements SqlSpecification {
    /**
     * UserId specification
     *
     */
    public static class ByUserId implements SqlSpecification {
        private Integer userId;

        public ByUserId(final Integer userId) {
            this.userId = userId;
        }

        public String toSqlQuery() {
            return String.format(
                    "SELECT * FROM %1$s WHERE \"%2$s\" = %3$d ORDER BY \"%4$s\";",
                    RankRepository.getTableName(),
                    RankRepository.getNameFieldDB("userId"),
                    userId,
                    RankRepository.getNameFieldDB("name")
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

        public String toSqlQuery() {
            return String.format(
                    "SELECT * FROM %1$s WHERE \"%2$s\" = %3$d AND \"%4$s\" = %5$d ORDER BY \"%4$s\";",
                    RankRepository.getTableName(),
                    RankRepository.getNameFieldDB("personId"),
                    personId,
                    RankRepository.getNameFieldDB("userId"),
                    userId,
                    RankRepository.getNameFieldDB("name")
            );
        }
    }

    /**
     * DELETE specification
     *
     */
    public static class DeleteRank implements SqlSpecification {
        private Keyword keyword;

        public DeleteRank(final Keyword keyword) {
            this.keyword = keyword;
        }

        public String toSqlQuery() {
            return String.format(
                    "DELETE FROM %1$s WHERE \"%2$s\" = %3$d;",
                    RankRepository.getTableName(),
                    RankRepository.getNameFieldDB("id"),
                    keyword.getId()
            );
        }
    }

    /**
     * DELETE PersonId specification
     *
     */
    public static class DeleteRanksByPersonId implements SqlSpecification {
        private Person person;

        public DeleteRanksByPersonId(final Person person) {
            this.person = person;
        }

        public String toSqlQuery() {
            return String.format(
                    "DELETE FROM %1$s WHERE \"%2$s\" = %3$d;",
                    RankRepository.getTableName(),
                    RankRepository.getNameFieldDB("personId"),
                    person.getId()
            );
        }
    }

    public String toSqlQuery() {
        return null;
    }
}
