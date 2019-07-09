package controllersGetRequest;


import storage.StorageSingleton;
import storage.userInfo.UserInfo;
import utils.ClientCookie;
import utils.ClientSession;
import utils.FreeM;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет реализующий страницу пользователя
 */
@WebServlet(urlPatterns = "/user")
public class UserPage extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        FreeM freeMarker = new FreeM("UserProfile.ftl", this);
        String webAddress = "" + req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/AuthenticationServletH2/";
        freeMarker.initMap(webAddress, "user");


        boolean haveUserInfo = false;
        if (ClientSession.checkName(req))
        {

            freeMarker.addToMap("name", ClientSession.getFromSession(req, "name"));
            freeMarker.addToMap("mail", ClientSession.getFromSession(req, "mail"));
            freeMarker.addToMap("info", ClientSession.getFromSession(req, "info"));

            ClientCookie.setCookie(resp, "token", ClientSession.getFromSession(req, "token"));
            haveUserInfo = true;
        } else
        {
            String tok = ClientCookie.getCookieIfExist(req, "token");

            if (tok != null)
            {
                UserInfo user = StorageSingleton.getUserInfoSingleton().getInfoUserByToken(tok);
                if (user != null)
                {

                    freeMarker.addToMap("name", user.getName());
                    freeMarker.addToMap("mail", user.getMail());
                    freeMarker.addToMap("info", user.getInfo());
                    haveUserInfo = true;
                }
            }

        }
        if (!haveUserInfo)
        {

            ClientCookie.removeCookie(resp, "token");
            ClientSession.clearSession(req);

            resp.sendRedirect(webAddress + "authentication");
        }
        resp.getWriter().println(freeMarker);
        resp.setContentType("text/html");
    }
}
