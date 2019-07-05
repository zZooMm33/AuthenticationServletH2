import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Objects;


@WebServlet(urlPatterns = "/user")
public class UserPage extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        String out = MessageFormat.format("User page.<br/>{0}",this.getServletContext().getContextPath());
        //out+="<br/>"+
        final File folder = new File(this.getServletContext().getContextPath());
        resp.getWriter().println(out);//request.getCookies()[0].getValue()
        Cookie ck=new Cookie("token","");
        ck.setMaxAge(0);
        resp.addCookie(ck);

        resp.setContentType("text/html");//200 302 404

    }
}
