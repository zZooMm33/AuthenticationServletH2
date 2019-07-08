package controllersPostRequest;

import clientInfo.ClientCookie;
import clientInfo.ClientSession;
import encode.EncoderPass;
import storage.AuthDB;
import storage.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Base64;
import java.util.UUID;

/**
 * Сервлет реализующий запрос авторизации
 */
@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login =req.getParameter("login");
        String pass =req.getParameter("pass");
        boolean foundLogin=false;
        ClientSession.setSessionIfNotSet(req.getSession());
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String realPassAuthEncoded="";
        String encodedPass = EncoderPass.encode(pass);

        Statement statement = DBConnection.getStatement();
        try {
            realPassAuthEncoded = AuthDB.getPass(statement, login);
            //TODO norm proverky after yarik sdelaet db
            foundLogin = realPassAuthEncoded != null;
        } catch (SQLException e){
            resp.getWriter().append("loginFail");
        }
        try {
            if(encodedPass.equals(realPassAuthEncoded)){

                ResultSet resultSetInfo= AuthDB.getInfoUserByName(statement,login);
                resultSetInfo.next();

                ClientSession.addToSession("name",resultSetInfo.getString("NAME"));
                ClientSession.addToSession("mail",resultSetInfo.getString("MAIL"));
                ClientSession.addToSession("info",resultSetInfo.getString("INFO"));

                String token=UUID.randomUUID().toString().toUpperCase();
                ClientSession.addToSession("token",token);
                AuthDB.updateTokenByIdUser(statement,resultSetInfo.getString("ID"),token);

                ClientCookie.setCookie(resp,"token",token);

            } else {
                resp.getWriter().append("passFail");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().append("passFail");

        }
    }
}