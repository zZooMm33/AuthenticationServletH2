import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

@WebServlet(urlPatterns = "/regPost")
public class Registration extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login =req.getParameter("login");
        String pass =req.getParameter("pass");
        String mail =req.getParameter("mail");
        String info =req.getParameter("info");
        boolean foundLoginMail=false;

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String realPassAuthDB="";
        Statement statement = DBConnection.getStatement();
        try {
            ResultSet resultSet= AuthDB.checkMail(statement,mail);
            while (resultSet.next()){
                resultSet.getString("NAME");
                resp.getWriter().append("mail");
                foundLoginMail=true;
            }

            resultSet=AuthDB.checkUserName(statement,login);
            while (resultSet.next()){
                resultSet.getString("NAME");
                resp.getWriter().append("login");
                foundLoginMail=true;
            }

        }
        catch (SQLException e){
            //e.printStackTrace();


        }
        try {
            if(!foundLoginMail)
            {
                AuthDB.insertUser(statement,login,mail,info);
                ResultSet userResult = AuthDB.getInfoUserByName(statement,login);
                userResult.next();
                String id=userResult.getString("ID");
                AuthDB.insertUserPass(statement,id,pass);
                AuthDB.insertUserToken(statement,id,"nechevo");
            }
        } catch (SQLException ex) {
            //ex.printStackTrace();
            try {
                statement= DBConnection.getStatement();
                AuthDB.checkTables(statement);
                AuthDB.insertUser(statement,login,mail,info);
                ResultSet userResult = AuthDB.getInfoUserByName(statement,login);
                userResult.next();
                String id=userResult.getString("ID");
                AuthDB.insertUserPass(statement,id,pass);

                AuthDB.insertUserToken(statement,id,"nechevo");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}