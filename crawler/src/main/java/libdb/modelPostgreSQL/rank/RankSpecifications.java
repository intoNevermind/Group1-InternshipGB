package libdb.modelPostgreSQL.rank;

import libdb.SqlSpecification;
import libdb.entities.Keyword;
import libdb.entities.Person;

public class RankSpecifications implements SqlSpecification {
    /**
     * UserId specification
     *
     */
    public String selectByUserId(final Integer userId) {
        return String.format(
                "SELECT * FROM %1$s WHERE \"%2$s\" = %3$d ORDER BY \"%4$s\";",
                RankRepository.getTableName(),
                RankRepository.getNameFieldDB("userId"),
                userId,
                RankRepository.getNameFieldDB("name")
        );
    }

    /**
     * PersonID and UserId specification
     *
     */
    public String selectByPersonIdAndUserId(final Integer personId, final Integer userId) {
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

    /**
     * Delete specification
     *
     */
    public String delete(final Keyword keyword) {
        return String.format(
                "DELETE FROM %1$s WHERE \"%2$s\" = %3$d;",
                RankRepository.getTableName(),
                RankRepository.getNameFieldDB("id"),
                keyword.getId()
        );
    }

    /**
     * Delete PersonId specification
     *
     */
    public String deleteRanksByPersonId(final Person person) {
        return String.format(
                "DELETE FROM %1$s WHERE \"%2$s\" = %3$d;",
                RankRepository.getTableName(),
                RankRepository.getNameFieldDB("personId"),
                person.getId()
        );
    }

    public String toSqlQuery() {
        return null;
    }
}
