import org.h2.engine.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
            if(pass.equals(realPassAuthDB)){
                HttpSession session=req.getSession();
                ResultSet resultSetInfo=AuthDB.getInfoUserByName(statement,login);
                resultSetInfo.next();
                session.setAttribute("name",resultSetInfo.getString("NAME"));
                session.setAttribute("mail",resultSetInfo.getString("MAIL"));
                session.setAttribute("info",resultSetInfo.getString("INFO"));

                String token=UUID.randomUUID().toString().toUpperCase();
                session.setAttribute("token",token);
                AuthDB.updateToken(statement,resultSetInfo.getString("ID"),token);
                Cookie ck=new Cookie("token",token);//creating cookie object
                resp.addCookie(ck);//adding cookie in the response
                }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        String webAddress=""+req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/AuthenticationServletH2/";
     //   resp.sendRedirect(webAddress+"authentication");
    }
}