import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {
    static String host = null;
    static String user = null;
    static String pass = null;
    static Connection connection=null;
    public static Connection getConnection()  {
        try {
            if(connection==null )
            {
                Class.forName("org.h2.Driver");
                if(host==null){
                    Properties prop=new Properties();
                    String propFileName= "config.properties";
                    InputStream inputStream= DBConnection.class.getClassLoader().getResourceAsStream(propFileName);
                    if(inputStream!=null){
                        try
                        {
                            prop.load(inputStream);
                            if(prop.getProperty("pass").equals("null")){
                                pass=null;
                            }
                            host=prop.getProperty("host");
                            user=prop.getProperty("user");
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }

                }
                connection = DriverManager.getConnection(host, user, pass);
            }

            return connection;
        } catch (SQLException | ClassNotFoundException e) {
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
