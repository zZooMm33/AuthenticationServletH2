package controllersPostRequest;

import clientInfo.ClientCookie;
import clientInfo.ClientSession;
import storage.AuthDB;
import storage.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/logout")
public class Logout extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Class.forName("org.h2.Driver");
            Statement statement = DBConnection.getStatement();
            Cookie[] cookies = req.getCookies();
            String token=ClientCookie.getCookieIfExist(req,"token");

            AuthDB.updateTokenByToken(statement,token,"");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        ClientCookie.removeCookie(resp,"token");
        ClientSession.setSessionIfNotSet(req.getSession());
        ClientSession.clearSession();
    }
}