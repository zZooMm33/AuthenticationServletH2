import org.h2.engine.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.UUID;

@WebServlet(urlPatterns = "/logout")
public class Logout extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/Auth", "sa", null);
            Statement statement = connection.createStatement();
            String token=session.getAttribute("token").toString();

           // AuthDB.updateTokenByToken(statement,token,"");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        Cookie ck=new Cookie("token","");
        ck.setMaxAge(0);
        resp.addCookie(ck);

        session.invalidate();
        String webAddress=""+req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/AuthenticationServletH2/";
        //   resp.sendRedirect(webAddress+"authentication");
    }
}