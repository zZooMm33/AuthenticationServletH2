import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    static final String host = "jdbc:h2:tcp://localhost/~/Auth";
    static final String user = "sa";
    static final String pass = null;
    public static Connection getConnection()  {
        try {
            Connection connection = DriverManager.getConnection(host, user, pass);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    public static Statement getStatement()  {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
