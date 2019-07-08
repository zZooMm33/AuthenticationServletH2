package controllersPostRequest;

import encode.EncoderPass;
import storage.AuthDB;
import storage.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

/**
 * Сервлет реализующий запрос на регистрацию
 */
@WebServlet(urlPatterns = "/regPost")
public class Registration extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login =req.getParameter("login");
        String pass =req.getParameter("pass");
        String mail =req.getParameter("mail");
        String info =req.getParameter("info");
        boolean foundLoginMail=false;


        String realPassAuthDB="";
        Statement statement = DBConnection.getStatement();
        try {
            ResultSet resultSet= AuthDB.checkMail(statement,mail);
            while (resultSet.next()){
                resultSet.getString("NAME");
                resp.getWriter().append("mail");
                foundLoginMail=true;
            }

            resultSet= AuthDB.checkUserName(statement,login);
            while (resultSet.next()){
                resultSet.getString("NAME");
                resp.getWriter().append("login");
                foundLoginMail=true;
            }

        } catch (SQLException e){
            //e.printStackTrace();
        }
        try {
            if(!foundLoginMail)
            {
                AuthDB.insertUser(statement,login,mail,info);
                ResultSet userResult = AuthDB.getInfoUserByName(statement,login);
                userResult.next();
                String id=userResult.getString("ID");

                String encodedPass = EncoderPass.encode(pass);

                AuthDB.insertUserPass(statement,id,encodedPass);
                AuthDB.insertUserToken(statement,id,"nechevo");
            }
        } catch (SQLException ex) {
            //ex.printStackTrace();
            try {
                statement= DBConnection.getStatement();
                AuthDB.checkTables(statement);
                AuthDB.insertUser(statement,login,mail,info);
                ResultSet userResult = AuthDB.getInfoUserByName(statement,login);
                userResult.next();
                String id=userResult.getString("ID");

                String encodedPass = EncoderPass.encode(pass);
                AuthDB.insertUserPass(statement,id,encodedPass);
                AuthDB.insertUserToken(statement,id,"nechevo");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}