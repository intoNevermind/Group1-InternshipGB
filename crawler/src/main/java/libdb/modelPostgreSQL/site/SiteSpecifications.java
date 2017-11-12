package libdb.modelPostgreSQL.site;

import libdb.SqlSpecification;
import libdb.entities.Site;
import libdb.modelPostgreSQL.person.PersonRepository;

public class SiteSpecifications implements SqlSpecification {

    /**
     * Select all active sites specification
     */
    public static class SelectOnlyActive implements SqlSpecification {
        @Override
        public String toSqlQuery() {
            return String.format(
                    "SELECT * FROM %1$s WHERE \"%2$s\" = %3$b ORDER BY \"%4$s\";",
                    SiteRepository.getTableName(),
                    SiteRepository.getNameFieldDB("active"),
                    true,
                    SiteRepository.getNameFieldDB("name")
            );
        }
    }

    /**
     * Select all sites specification
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
                        SiteRepository.getTableName(),
                        SiteRepository.getNameFieldDB("name")
                );
        }
    }

    /**
     * Select all active by UserId specification
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
                    SiteRepository.getTableName(),
                    SiteRepository.getNameFieldDB("userId"),
                    userId,
                    SiteRepository.getNameFieldDB("active"),
                    true,
                    SiteRepository.getNameFieldDB("name")
            );
        }
    }


    /**
     * Select all by UserId specification
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
                        SiteRepository.getTableName(),
                        SiteRepository.getNameFieldDB("userId"),
                        userId,
                        SiteRepository.getNameFieldDB("name")
                );
        }
    }


    /**
     * Insert specification
     */
    public static class Insert implements SqlSpecification {
        private Site site;
        public Insert(Site site) {
            this.site = site;
        }
        @Override
        public String toSqlQuery() {
            return String.format(
                    "INSERT INTO %1$s(\"%2$s\", \"%3$s\", \"%4$s\", \"%5$s\") VALUES ('%6$s', '%7$s', %8$b, %9$d);",
                    SiteRepository.getTableName(),
                    SiteRepository.getNameFieldDB("name"),
                    SiteRepository.getNameFieldDB("url"),
                    SiteRepository.getNameFieldDB("active"),
                    SiteRepository.getNameFieldDB("userId"),
                    site.getName(),
                    site.getUrl(),
                    site.getActive(),
                    site.getUserId()
            );
        }
    }

    /**
     * Update specification
     */
    public static class Update implements SqlSpecification {
        private Site site;
        public Update(Site site) {
            this.site = site;
        }
        @Override
        public String toSqlQuery() {
            return String.format(
                    "UPDATE %1$s SET \"%2$s\" = '%3$s', \"%4$s\" = '%5$s', \"%6$s\" = %7$b WHERE \"%8$s\" = %9$d;",
                    SiteRepository.getTableName(),
                    SiteRepository.getNameFieldDB("name"),
                    site.getName(),
                    SiteRepository.getNameFieldDB("url"),
                    site.getUrl(),
                    SiteRepository.getNameFieldDB("active"),
                    site.getActive(),
                    SiteRepository.getNameFieldDB("id"),
                    site.getId()
            );
        }
    }


    /**
     * Delete specification
     */
    public static class Delete implements SqlSpecification {
        private Site site;
        public Delete(Site site) {
            this.site = site;
        }
        @Override
        public String toSqlQuery() {
            return String.format(
                    "DELETE FROM %1$s WHERE \"%2$s\" = %3$d AND \"%4$s\" = %5$d;",
                    SiteRepository.getTableName(),
                    SiteRepository.getNameFieldDB("id"),
                    site.getId(),
                    SiteRepository.getNameFieldDB("userId"),
                    site.getUserId()
            );
        }
    }

    public String toSqlQuery() {
        return null;
    }
}
