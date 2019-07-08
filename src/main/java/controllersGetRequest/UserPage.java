package controllersGetRequest;



import clientInfo.ClientCookie;
import clientInfo.ClientSession;
import freeMarker.FreeM;
import storage.AuthDB;
import storage.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@WebServlet(urlPatterns = "/user")
public class UserPage extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        FreeM freeMarker=new FreeM("UserProfile.ftl",this);
        String webAddress=""+req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/AuthenticationServletH2/";
        freeMarker.initMap(webAddress,"user");

        ClientSession.setSessionIfNotSet(req.getSession());

        boolean haveUserInfo=false;
        if(ClientSession.checkName()) {

            freeMarker.addToMap("name",ClientSession.getFromSession("name"));
            freeMarker.addToMap("mail",ClientSession.getFromSession("mail"));
            freeMarker.addToMap("info",ClientSession.getFromSession("info"));

            ClientCookie.setCookie(resp,"token",ClientSession.getFromSession("token"));
            haveUserInfo=true;
        }
        else{

            try {
                Class.forName("org.h2.Driver");

                Statement statement= DBConnection.getStatement();
                ResultSet resultSetInfo= AuthDB.getInfoUserByToken(statement,ClientCookie.getCookieIfExist(req,"token"));

                resultSetInfo.next();
                freeMarker.addToMap("name",resultSetInfo.getString("NAME"));
                freeMarker.addToMap("mail",resultSetInfo.getString("MAIL"));
                freeMarker.addToMap("info",resultSetInfo.getString("INFO"));
                haveUserInfo=true;
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }


        }
        if(!haveUserInfo){
            ClientCookie.removeCookie(resp,"token");
            ClientSession.clearSession();

            resp.sendRedirect(webAddress+"authentication");
        }
        resp.getWriter().println(freeMarker);
        resp.setContentType("text/html");
    }
}
