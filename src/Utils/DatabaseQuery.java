package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseQuery {
    //statement reference
    private static PreparedStatement statement;

    //create statement object
    public static void setPreparedStatement(Connection connection, String sqlStatement) throws SQLException {
        statement = connection.prepareStatement(sqlStatement);
    }

    public static PreparedStatement getPreparedStatement() {
        return statement;
    }
}
