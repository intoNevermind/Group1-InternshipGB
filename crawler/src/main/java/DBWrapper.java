import java.sql.*;
import java.util.ArrayList;

/**
 * Created by ferney on 12.11.17.
 */
public class DBWrapper {

    final int BUCKET_TYPE_SITES = 1;
    final int BUCKET_TYPE_PAGES = 2;

    final int HOURS_BEFORE_UPDATE = 23;

    private Connection connection = null;

    public DBWrapper() {
        // TODO: move DB connection parameters to a config
        try {
            connection =
                    DriverManager.getConnection(
                            "jdbc:postgresql://54.154.158.193:5432/scanner",
                            "scanner",
                            "Gvyq4lA06VkpHAb2WxFu2gZib2ORpHGv");

            connection.setAutoCommit(false);
            //connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getAllSites() {
        return (getListFromDB("SELECT \"URL\" FROM sites"));
    }

    private String getBucketlistSql(int bucketType, int size) {
        if (bucketType == BUCKET_TYPE_SITES) {
            return "SELECT \"ID\", \"URL\" FROM sites s " +
                    "WHERE " +
                    "(SELECT count(1) FROM pages p WHERE p.\"SiteID\" = s.\"ID\") = 0 " +
                    "AND \"InProgress\" = false " +
                    "AND \"LastUpdated\" < NOW() - INTERVAL '" + HOURS_BEFORE_UPDATE + " hours' " +
                    "LIMIT " + size + " FOR UPDATE";
        } else if (bucketType == BUCKET_TYPE_PAGES) {
            return "SELECT \"ID\", \"URL\" FROM pages " +
                    "WHERE " +
                    "\"LastScanDate < NOW() - INTERVAL '" + HOURS_BEFORE_UPDATE + " hours' \"" +
                    "AND \"InProgress\" = false" +
                    "ORDER BY \"LastScanDate\"" +
                    "LIMIT " + size + " FOR UPDATE";
        } else {
            // Unknown bucket type!
            return "";
        }
    }

    private String getBucketlockSql(int bucketType, int size) {
        if (bucketType == BUCKET_TYPE_SITES) {
            return "UPDATE sites SET \"InProgress\" = true WHERE \"ID\" = ?";
        }
        if (bucketType == BUCKET_TYPE_PAGES) {
            return "UPDATE sites SET \"InProgress\" = true WHERE \"ID\" = ?";
        } else {
            // Unknown bucket type!
            return "";
        }
    }

    public ArrayList<String> getBucketFromDB(int size, int bucketType) {

        String listSql = getBucketlistSql(bucketType, size);
        String lockSql = getBucketlockSql(bucketType, size);

        Statement listStatement = null;
        PreparedStatement lockStatement = null;

        ResultSet listResultSet = null;

        ArrayList<String> result = new ArrayList<>();

        try {

            listStatement = connection.createStatement();
            listStatement.executeQuery(listSql);

            listResultSet = listStatement.getResultSet();
            lockStatement = connection.prepareStatement(lockSql);

            while (listResultSet.next()) {
                lockStatement.setInt(1, listResultSet.getInt("ID"));
                lockStatement.execute();
                result.add(listResultSet.getString("URL"));
            }

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                listResultSet.close();
                listStatement.close();
                lockStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return (result);
    }

    public ArrayList<String> getSiteBucketFromDB(int size) {
        return getBucketFromDB(size, BUCKET_TYPE_SITES);
    }

    public ArrayList<String> getPageBucketFromDB(int size) {
        return getBucketFromDB(size, BUCKET_TYPE_PAGES);
    }

    private String getBucketItemUnlockSql(int bucketType) {
        if (bucketType == BUCKET_TYPE_SITES) {
            return "UPDATE sites SET \"InProgress\" = false WHERE \"URL\" = ?";
        } else if (bucketType == BUCKET_TYPE_PAGES) {
            return "UPDATE pages SET \"InProgress\" = false WHERE \"URL\" = ?";
        } else {
            // Unknown bucket type!
            return "";
        }
    }


    public void unlockBucketItem(String url, int bucketType) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(getBucketItemUnlockSql(bucketType));
            preparedStatement.setString(1, url);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void addSitePage(String siteUrl, String pageUrl) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO pages (\"SiteID\", \"URL\", \"FoundDateTime\") " +
                                                            "VALUES ((SELECT \"ID\" FROM sites WHERE \"URL\" = ?), ?, NOW())");
            preparedStatement.setString(1, siteUrl);
            preparedStatement.setString(2, pageUrl);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // For pages
    public void setSiteScanDate(String url) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE sites SET \"LastUpdated\" = NOW() WHERE \"URL\" = ?");
            preparedStatement.setString(1, url);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void unlockSite(String url) {
        setSiteScanDate(url);
        unlockBucketItem(url, BUCKET_TYPE_SITES);
    }

    public void unlockPage(String url) {
        unlockBucketItem(url, BUCKET_TYPE_PAGES);
        // update LastScanDate in DB
    }

    public ArrayList<String> getListFromDB(String sql) {

        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> result = new ArrayList<>();

        try {

            statement = connection.createStatement();
            statement.execute(sql);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next()) {
                result.add(resultSet.getString("URL"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return (result);
    }

}
