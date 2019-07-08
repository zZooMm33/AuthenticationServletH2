package clientInfo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Класс реализующий работу с куки
 */
public class ClientCookie
{
    public static void setCookie(HttpServletResponse resp,String key,String value)
    {
        Cookie cook=new Cookie(key,value);
        resp.addCookie(cook);
    }

    public static void removeCookie(HttpServletResponse resp, String key){
        Cookie cook=new Cookie(key,"");
        cook.setMaxAge(0);
        resp.addCookie(cook);
    }

    public static String getCookieIfExist(HttpServletRequest req,String key){
        try {
            Cookie[] cookies=req.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                if(cookies[i].getName().equals(key)){
                    return cookies[i].getValue();
                }
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return "";
        }
        return "";
    }
}
