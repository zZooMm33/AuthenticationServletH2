package controllersGetRequest;


import clientInfo.ClientCookie;
import clientInfo.ClientSession;
import freeMarker.FreeM;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

/**
 * Сервлет реализующий страницу авторизации
 */
@WebServlet(urlPatterns = "/authentication")
public class Authentication extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        FreeM freeMarker=new FreeM("Authentication.ftl",this);
        String webAddress=""+req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/AuthenticationServletH2/";
        freeMarker.initMap(webAddress);
        ClientSession.setSessionIfNotSet(req.getSession());

        boolean redirectToUser=false;
        if(ClientSession.checkToken()) {
            redirectToUser=true;
            ClientCookie.setCookie(resp,"token",ClientSession.getFromSession("token"));
        }
        else{
            if (!ClientCookie.getCookieIfExist(req, "token").equals(""))
                redirectToUser = true;
        }
        if(redirectToUser){
            resp.sendRedirect(webAddress+"user");
        }
        resp.getWriter().println(freeMarker);
        resp.setContentType("text/html");
    }
}
