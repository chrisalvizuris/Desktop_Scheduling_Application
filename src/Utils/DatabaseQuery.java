package Utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseQuery {
    //statement reference
    private static Statement statement;

    //create statement object
    public static void setStatement(Connection connection) throws SQLException {
        statement = connection.createStatement();
    }

    public static Statement getStatement() {
        return statement;
    }
}
