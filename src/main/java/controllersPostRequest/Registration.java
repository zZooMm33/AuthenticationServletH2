package controllersPostRequest;

import utils.ClientCookie;
import utils.EncoderPass;
import storage.StorageSingleton;
import storage.UserInStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет реализующий запрос на регистрацию
 */
@WebServlet(urlPatterns = "/regPost")
public class Registration extends HttpServlet
{
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