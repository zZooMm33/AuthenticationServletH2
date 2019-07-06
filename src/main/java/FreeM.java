import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class FreeM {

    Configuration cfgFreeMarker = null;
    Template temp=null;
    Writer outFreeMarker = null;

    Map<String, Object> freeMarkerMap = null;

    public FreeM(String templateName, Object servletClass){

        try {
            this.cfgFreeMarker = new Configuration(Configuration.VERSION_2_3_28);
            cfgFreeMarker.setClassForTemplateLoading(servletClass.getClass(), "/");
            this.temp = cfgFreeMarker.getTemplate(templateName);
            this.outFreeMarker = new StringWriter();
            this.freeMarkerMap=new HashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initMap(String webAddress){

        freeMarkerMap.put("name","");
        freeMarkerMap.put("webAddress",webAddress);
        freeMarkerMap.put("mail","");
        freeMarkerMap.put("info","");
        freeMarkerMap.put("error","");
        freeMarkerMap.put("authButton","");
    }

    public void addToMap(String key,String value){

        freeMarkerMap.put(key,value);

    }

    @Override
    public String toString() {
        try {
            temp.process(freeMarkerMap, outFreeMarker);
            return outFreeMarker.toString();
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
