package controllersGetRequest;

import storage.StorageSingleton;
import storage.UserInStorage;
import utils.ClientCookie;
import utils.ClientSession;
import utils.EncoderPass;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String mail = req.getParameter("mail");
        String info = req.getParameter("info");
        boolean foundLoginMail = false;


        String realPassAuthDB = "";
        UserInStorage user = StorageSingleton.getStorageSingleton().getInfoUserByToken(ClientCookie.getCookieIfExist(req, "token"));


        if (StorageSingleton.getStorageSingleton().checkMail(mail))
        {
            foundLoginMail = true;
            resp.getWriter().append("mail");
        }

        if (StorageSingleton.getStorageSingleton().checkUserName(login))
        {
            foundLoginMail = true;
            resp.getWriter().append("login");
        }

        if (!foundLoginMail)
        {
            String encodedPass = EncoderPass.encode(pass);
            StorageSingleton.getStorageSingleton().addUser(login, mail, info, encodedPass, "nechevo");
        }


    }
}