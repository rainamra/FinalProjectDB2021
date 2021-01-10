package Controller;

import java.sql.*;

public class DatabaseConnection {
    private static Connection con = null;

    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/kinada_local?"
                + "zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC ";
        String user = "root";
        String password = "";
        if (con == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, user, password);
                return con;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return con;
    }
}
