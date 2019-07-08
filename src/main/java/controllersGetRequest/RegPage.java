package controllersGetRequest;

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
 * Сервлет реализующий страницу регистрации
 */
@WebServlet(urlPatterns = "/reg")
public class RegPage extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        FreeM freeMarker = new FreeM("Registration.ftl", this);
        String webAddress = "" + req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/AuthenticationServletH2/";
        freeMarker.initMap(webAddress);

        boolean redirectToUser = false;
        if (ClientSession.checkToken(req))
        {
            redirectToUser = true;
            ClientCookie.setCookie(resp, "token", ClientSession.getFromSession(req, "token"));
        } else
        {
            if (ClientCookie.getCookieIfExist(req, "token") != null)
                redirectToUser = true;
        }
        if (redirectToUser)
        {
            resp.sendRedirect(webAddress + "user");
        }
        resp.getWriter().println(freeMarker);
        resp.setContentType("text/html");
    }
}