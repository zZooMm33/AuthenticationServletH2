package controllersPostRequest;

import utils.ClientCookie;
import utils.ClientSession;
import storage.StorageSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Сервлет реализующий запрос выхода пользователя из сети
 */
@WebServlet(urlPatterns = "/logout")
public class Logout extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String token = ClientCookie.getCookieIfExist(req, "token");
        StorageSingleton.getStorageSingleton().updateTokenByToken(token, "");

        ClientCookie.removeCookie(resp, "token");

        ClientSession.clearSession(req);
    }
}