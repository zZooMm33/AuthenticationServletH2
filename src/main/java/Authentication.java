import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/authentication")
public class Authentication extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {


        Configuration cfgFreeMarker = new Configuration(Configuration.VERSION_2_3_28);
        cfgFreeMarker.setClassForTemplateLoading(this.getClass(), "/");
        //jdbc:h2:tcp://localhost/~/Auth
        Template temp = cfgFreeMarker.getTemplate("authentication.ftl");
        String out=this.getServletContext().getContextPath();
        Writer outFreeMarker = new StringWriter();

        Map<String, Object> freeMarkerMap = new HashMap<>();


        String webAddress=""+req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/AuthenticationServletH2/";
        freeMarkerMap.put("name","");
        freeMarkerMap.put("webAddress",webAddress);
        freeMarkerMap.put("mail","");
        freeMarkerMap.put("info","");
        //freeMarkerMap.put("pass","");
        freeMarkerMap.put("error","");
        freeMarkerMap.put("authButton","");

        HttpSession session=req.getSession();
        boolean haveUserInfo=false;
        if(session.getAttribute("name")!=null) {

            freeMarkerMap.put("name",session.getAttribute("name").toString());
            freeMarkerMap.put("mail",session.getAttribute("mail").toString());
            freeMarkerMap.put("info",session.getAttribute("info").toString());
            haveUserInfo=true;
            Cookie cook=new Cookie("token",session.getAttribute("token").toString());//creating cookie object
            resp.addCookie(cook);

        }
        else{
           // freeMarkerMap.put("error","Username ravno null");
            Cookie[] cookies=req.getCookies();

            for (int i = 0; i < cookies.length; i++) {
                if(cookies[i].getName()=="token"){
                    //request from database by token

                    try {
                        Class.forName("org.h2.Driver");
                        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/Auth", "sa", null);
                        Statement statement = connection.createStatement();
                        ResultSet resultSetInfo=AuthDB.getInfoUserByToken(statement,cookies[i].getValue().toString());

                        resultSetInfo.next();
                        freeMarkerMap.put("name",resultSetInfo.getString("NAME"));
                        freeMarkerMap.put("mail",resultSetInfo.getString("MAIL"));
                        freeMarkerMap.put("info",resultSetInfo.getString("INFO"));
                        haveUserInfo=true;
                        //freeMarkerMap.put("pass",AuthDB.getPass(statement,"zZooMm"));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();

                    }

                }
            }

        }
        if(!haveUserInfo){
            //buttons with auth
            freeMarkerMap.put("authButton","true");
        }

        try
        {
        temp.process(freeMarkerMap, outFreeMarker);

        resp.getWriter().println(outFreeMarker.toString());
        resp.setContentType("text/html");
        } catch (TemplateException e)
        {
            e.printStackTrace();
        }



    }
}
