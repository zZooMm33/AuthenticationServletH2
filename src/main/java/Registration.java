import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

@WebServlet(urlPatterns = "/reg")
public class Registration extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login =req.getParameter("login");
        String pass =req.getParameter("pass");
        String mail =req.getParameter("mail");
        String info =req.getParameter("info");
        boolean foundLogin=false;

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String realPassAuthDB="";
        Statement statement = DBConnection.getStatement();
        try {
            AuthDB.checkUserName(statement,mail);
            resp.getWriter().append("mail");
            AuthDB.checkUserName(statement,login);
            resp.getWriter().append("login");
        }
        catch (SQLException e){
            try {
                AuthDB.insertUser(statement,login,mail,info);
                ResultSet userResult = AuthDB.getInfoUserByName(statement,login);
                userResult.next();
//                    toPrint+=resultSet.getInt("ID")+"-id,"+resultSet.getString("STREET")+","+resultSet.getInt("HOMENUM");
                AuthDB.insertUserPass(statement,userResult.getInt("ID")+"",pass);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }
}