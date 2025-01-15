package db_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {
    private static DB_Connection db_connection;
    private Connection connection;

    private DB_Connection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root","1234");
    }

    public static DB_Connection getDbConnection() throws SQLException {
        return db_connection==null?db_connection =new DB_Connection():db_connection;
    }

    public Connection getConnection(){
        return connection;
    }
}
