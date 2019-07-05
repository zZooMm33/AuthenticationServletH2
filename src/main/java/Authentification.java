import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/authentification")
public class Authentification extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
        Configuration cfgFreeMarker = new Configuration(Configuration.VERSION_2_3_28);

        cfgFreeMarker.setClassForTemplateLoading(this.getClass(), "/");

        Map<String, Object> freeMarkerMap = new HashMap<>();
        freeMarkerMap.put("name", "Freemarker");
        Template temp = cfgFreeMarker.getTemplate("authentification.ftl");
        String out=this.getServletContext().getContextPath();
        Writer outFreeMarker = new StringWriter();

        String webAddress=""+req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/AuthenticationServletH2/";
        temp.process(freeMarkerMap, outFreeMarker);
        resp.getWriter().println(outFreeMarker.toString());//request.getCookies()[0].getValue()
        resp.setContentType("text/html");//200 302 404
        } catch (TemplateException e)
        {
            e.printStackTrace();
        }



    }
}
