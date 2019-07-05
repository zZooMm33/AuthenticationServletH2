import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.MessageFormat;

@WebServlet(urlPatterns = "/reg")
public class RegPage extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        String out = MessageFormat.format("Reg page page.<br/>","");
        HttpSession session=req.getSession();
        out+=session.getAttribute("token").toString();

        resp.getWriter().println(out);//request.getCookies()[0].getValue()
        resp.setContentType("text/html");//200 302 404

    }
}