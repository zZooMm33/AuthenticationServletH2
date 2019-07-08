package controllersPostRequest;

import storage.StorageSingleton;
import storage.UserInStorage;
import utils.ClientCookie;
import utils.ClientSession;
import utils.EncoderPass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Сервлет реализующий запрос авторизации
 */
@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        boolean foundLogin = false;
        String realPassAuthEncoded = "";
        String encodedPass = EncoderPass.encode(pass);

        realPassAuthEncoded = StorageSingleton.getStorageSingleton().getPass(login);

        if (realPassAuthEncoded == null)
        {
            foundLogin = false;
            resp.getWriter().append("loginFail");

        } else if (encodedPass != null)
        {
            if (encodedPass.equals(realPassAuthEncoded))
            {
                UserInStorage user = StorageSingleton.getStorageSingleton().getInfoUserByName(login);
                if (user != null)
                {
                    ClientSession.addToSession(req, "name", user.getName());
                    ClientSession.addToSession(req, "mail", user.getMail());
                    ClientSession.addToSession(req, "info", user.getInfo());

                    String token = UUID.randomUUID().toString().toUpperCase();
                    ClientSession.addToSession(req, "token", token);
                    StorageSingleton.getStorageSingleton().updateTokenByIdUser(user.getId(), token);

                    ClientCookie.setCookie(resp, "token", token);

                }


            } else
            {
                System.out.println("1" + encodedPass + "\n" + "2" + realPassAuthEncoded);
                resp.getWriter().append("passFail");

            }
        }


    }
}