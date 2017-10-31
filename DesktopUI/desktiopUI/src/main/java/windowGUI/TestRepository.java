package windowGUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestRepository {

    private static String dbUrl = "jdbc:mysql://localhost:3306";
    private static String user = "root";
    private static String password = "0o9i8u7y6";

    public TestRepository(){
        try {
            connectionDB(dbUrl, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void connectionDB(String dbUrl, String user, String password) throws SQLException{
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection connection = DriverManager.getConnection(dbUrl,user,password);
        if(connection != null){
            System.out.println("Подключение к базе " + dbUrl + " прошло успешно");
        }else{
            System.out.println("не получилось подключиться к базе данных ");
        }
    }
}
