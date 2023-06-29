package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection connection;

    private DBConnection(){}
    public static Connection getInstance() {

        String url = "jdbc:mysql://localhost:3306/baseball";
        String username = "root";
        String password = "password";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if(connection==null){
                connection= DriverManager.getConnection(url, username, password);
                return connection;
            }
            return connection;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
