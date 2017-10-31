package windowGUI.options.workSQL;

import java.sql.*;

public class ConnectionRepository {

    private static final String dbUrl = "jdbc:mysql://localhost:3306";
    private static final String user = "root";
    private static final String password = "0o9i8u7y6";

    public static Connection getConnectionToDB() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(dbUrl,user,password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
