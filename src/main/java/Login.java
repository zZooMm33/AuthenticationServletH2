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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String realPassAuthDB="";
        Statement statement = DBConnection.getStatement();
            try {
                realPassAuthDB = AuthDB.getPass(statement, login);
                foundLogin = realPassAuthDB != null;
            }
            catch (SQLException e){
                resp.getWriter().append("loginFail");
            }
            try {
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
            else {
                resp.getWriter().append("passFail");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            PrintWriter writer = resp.getWriter();
            resp.getWriter().append("passFail");

        }
    }
}