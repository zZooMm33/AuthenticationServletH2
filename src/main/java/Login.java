import org.h2.engine.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.UUID;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login =req.getParameter("login");
        String pass =req.getParameter("pass");
        boolean foundLogin=false;

        try {
            Class.forName("org.h2.Driver");

            Statement statement = DBConnection.getStatement();
            String realPassAuthDB =AuthDB.getPass(statement,login);
            foundLogin = realPassAuthDB != null;

            if(pass.equals(realPassAuthDB)){
                HttpSession session=req.getSession();
                ResultSet resultSetInfo=AuthDB.getInfoUserByName(statement,login);
                resultSetInfo.next();

                session.setAttribute("name",resultSetInfo.getString("NAME"));
                session.setAttribute("mail",resultSetInfo.getString("MAIL"));
                session.setAttribute("info",resultSetInfo.getString("INFO"));

                String token=UUID.randomUUID().toString().toUpperCase();
                session.setAttribute("token",token);
                AuthDB.updateTokenByIdUser(statement,resultSetInfo.getString("ID"),token);

                Cookie ck=new Cookie("token",token);
                resp.addCookie(ck);
                }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            PrintWriter writer = resp.getWriter();
            if(foundLogin)writer.append("loginFail");
            else
            writer.append("fail");
        }

        String webAddress=""+req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/AuthenticationServletH2/";
        resp.sendRedirect(webAddress+"authentication");
    }
}