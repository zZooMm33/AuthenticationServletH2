import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet(urlPatterns = "/authentication")
public class Authentication extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        FreeM freeMarker=new FreeM("Authentication.ftl",this);
        String webAddress=""+req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/AuthenticationServletH2/";
        freeMarker.initMap(webAddress);

        HttpSession session=req.getSession();
        boolean redirectToUser=false;
        if(session.getAttribute("name")!=null) {

            freeMarker.addToMap("name",session.getAttribute("name").toString());
            freeMarker.addToMap("mail",session.getAttribute("mail").toString());
            freeMarker.addToMap("info",session.getAttribute("info").toString());
            redirectToUser=true;
            Cookie cook=new Cookie("token",session.getAttribute("token").toString());
            resp.addCookie(cook);

        }
        else{
            try {
                Cookie[] cookies=req.getCookies();
                for (int i = 0; i < cookies.length; i++) {
                    if(cookies[i].getName().equals("token")){
                            redirectToUser=true;
                    }
                }
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }

        }
        if(redirectToUser){
            resp.sendRedirect(webAddress+"user");
        }
        resp.getWriter().println(freeMarker);
        resp.setContentType("text/html");
    }
}
