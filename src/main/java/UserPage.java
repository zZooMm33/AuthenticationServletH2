import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Objects;


@WebServlet(urlPatterns = "/user")
public class UserPage extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        FreeM freeMarker=new FreeM("UserProfile.ftl",this);
//        String out=this.getServletContext().getContextPath();
        String webAddress=""+req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/AuthenticationServletH2/";
        freeMarker.initMap(webAddress);

        HttpSession session=req.getSession();
        boolean haveUserInfo=false;
        if(session.getAttribute("name")!=null) {

            freeMarker.addToMap("name",session.getAttribute("name").toString());
            freeMarker.addToMap("mail",session.getAttribute("mail").toString());
            freeMarker.addToMap("info",session.getAttribute("info").toString());
            haveUserInfo=true;
            Cookie cook=new Cookie("token",session.getAttribute("token").toString());
            resp.addCookie(cook);

        }
        else{
            try {
                Cookie[] cookies=req.getCookies();
                for (int i = 0; i < cookies.length; i++) {
                    if(cookies[i].getName().equals("token")){
                        try {
                            Class.forName("org.h2.Driver");

                            Statement statement=DBConnection.getStatement();
                            ResultSet resultSetInfo=AuthDB.getInfoUserByToken(statement,cookies[i].getValue().toString());

                            resultSetInfo.next();
                            freeMarker.addToMap("name",resultSetInfo.getString("NAME"));
                            freeMarker.addToMap("mail",resultSetInfo.getString("MAIL"));
                            freeMarker.addToMap("info",resultSetInfo.getString("INFO"));
                            haveUserInfo=true;
                        } catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }

        }
        if(!haveUserInfo){
            URL obj = new URL(webAddress+"logout");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.flush();
            wr.close();
            resp.sendRedirect(webAddress+"authentication");
            //freeMarker.addToMap("authButton","true");
        }
        resp.getWriter().println(freeMarker);
        resp.setContentType("text/html");
    }
}
