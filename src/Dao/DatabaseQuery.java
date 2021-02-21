package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseQuery {
    //statement reference
    private static PreparedStatement statement;

    /**
     * This method creates the prepared statement for the database.
     * @param connection The connection reference to open connection to database.
     * @param sqlStatement The SQL statement that is passed into prepared statement.
     * @throws SQLException Throws an SQLException because database is called.
     */
    public static void setPreparedStatement(Connection connection, String sqlStatement) throws SQLException {
        statement = connection.prepareStatement(sqlStatement);
    }

    /**
     * This method retrieves the prepared statement.
     * @return Returns a prepared statement.
     */
    public static PreparedStatement getPreparedStatement() {
        return statement;
    }
}
