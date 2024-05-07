package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private Connection connection = null;
    private static final String URL = "jdbc:postgresql://localhost:5432/rentacar";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "123456789";
    private static DB instance = null;

    private DB() {
        try {
            this.connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new DB();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return instance.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }


}
