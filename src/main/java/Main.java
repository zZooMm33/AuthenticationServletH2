import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void  main(String[] args)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/Auth", "sa", null);
            if (connection == null)
                System.out.println("FAIL");
            else
            {
                Statement statement = connection.createStatement();
                System.out.println("Eeee");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
