package controllersPostRequest;

import storage.StorageSingleton;
import storage.userInfo.UserInfo;
import storage.userPass.UserPass;
import storage.userToken.UserToken;
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
//kek
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        boolean foundLogin = false;
        String realPassAuthEncoded = "";
        String encodedPass = EncoderPass.encode(pass);


        UserPass userPass = StorageSingleton.getUserPassSingleton().getPass(login);
        realPassAuthEncoded = userPass != null ? userPass.getPass() : null;
        if (realPassAuthEncoded == null)
        {
            foundLogin = false;
            resp.getWriter().append("loginFail");

        } else if (encodedPass != null)
        {
            if (encodedPass.equals(realPassAuthEncoded))
            {
                UserInfo user = StorageSingleton.getUserInfoSingleton().getInfoUserByName(login);
                if (user != null)
                {
                    ClientSession.addToSession(req, "name", user.getName());
                    ClientSession.addToSession(req, "mail", user.getMail());
                    ClientSession.addToSession(req, "info", user.getInfo());

                    String token = UUID.randomUUID().toString().toUpperCase();
                    ClientSession.addToSession(req, "token", token);
                    StorageSingleton.getUserTokenSingleton().updateTokenByIdUser(new UserToken(user.getId(), token));
                    ClientCookie.setCookie(resp, "token", token);

                }


            } else
            {
                resp.getWriter().append("passFail");
            }
        }


    }
}