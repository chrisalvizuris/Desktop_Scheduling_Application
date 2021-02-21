package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


    //setting up jdbc url with strings
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com/WJ05zKd";

    //jdbc url
    private static final String jdbcURL = protocol + vendorName + ipAddress;

    //driver and connection interface reference
    private static final String MYSQLJDBCDriver = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;

    //username
    private static final String username = "U05zKd";

    //password
    private static final String password = "53688653713";

    /**
     * This method starts the connection to the database.
     * @return
     */
    public static Connection beginConnection() {
        try {
            Class.forName(MYSQLJDBCDriver);
            conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection Successful");
        }   catch (ClassNotFoundException | SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return conn;
    }

    /**
     * This method closes the database connection.
     */
    public static void closeConnection() {
        try {
            conn.close();
            System.out.println("Connection closed");
        }   catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
