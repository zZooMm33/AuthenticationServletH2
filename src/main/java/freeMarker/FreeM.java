package freeMarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс реализующий работу с freeMarker
 */
public class FreeM {

    private Configuration cfgFreeMarker = null;
    private Template temp=null;
    private Writer outFreeMarker = null;

    private Map<String, Object> freeMarkerMap = null;

    /**
     * Конструктор инициализирующий класс
     * @param templateName название файла шаблона
     * @param servletClass класс к которому будет вычисляться путь
     */
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

    /**
     * Задать первоначальные значения в Map
     * @param webAddress адрес сайта
     * @param pageName название текущей страницы
     */
    public void initMap(String webAddress,String pageName){
        switch (pageName){
            case "user":
                freeMarkerMap.put("name","");
                freeMarkerMap.put("mail","");
                freeMarkerMap.put("info","");
                break;
                default:break;
        }

        freeMarkerMap.put("webAddress",webAddress);
    }

    /**
     * Задать значение в Map только для адреса сайта
     * @param webAddress адрес сайта
     */
    public void initMap(String webAddress){
        freeMarkerMap.put("webAddress",webAddress);
    }

    /**
     * Добавить значение в Map
     * @param key ключ
     * @param value значение
     */
    public void addToMap(String key,String value){

        freeMarkerMap.put(key,value);

    }

    /**
     * Вывод шаблона в строку
     * @return вернет строку с шаблоном заполненную из Map или пустую строку в случае неудачи
     */
    @Override
    public String toString() {
        try {
            temp.process(freeMarkerMap, outFreeMarker);
            return outFreeMarker.toString();
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}
