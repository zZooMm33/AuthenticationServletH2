import org.h2.engine.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.UUID;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login =req.getParameter("login");
        String pass =req.getParameter("pass");

        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/Auth", "sa", null);
            Statement statement = connection.createStatement();
            String realPassAuthDB =AuthDB.getPass(statement,login);
            if(pass==realPassAuthDB){
                HttpSession session=req.getSession();
                ResultSet resultSetInfo=AuthDB.getInfoUser(statement,);
                resultSetInfo.next();
                session.setAttribute("name",resultSetInfo.getString("NAME"));
                session.setAttribute("mail",resultSetInfo.getString("MAIL"));
                session.setAttribute("info",resultSetInfo.getString("INFO"));

                String token=UUID.randomUUID().toString().toUpperCase();
                session.setAttribute("token",token);
                AuthDB.updateToken(statement,resultSetInfo.getString("ID"),token);

                }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();

        }


        resp.sendRedirect("/authentication");
    }
}