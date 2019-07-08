package clientInfo;

import javax.servlet.http.HttpSession;

/**
 * Класс реализующий работу с сессией
 */
public class ClientSession
{
    private static HttpSession session;

    public static void setSessionIfNotSet(HttpSession ses){
        if(session==null){
            session=ses;
        }
    }


    public static boolean checkToken(){
        if(session.getAttribute("token")!=null){
            return true;
        }
        return false;
    }

    public static boolean checkName(){
        if(session.getAttribute("name")!=null){
            return true;
        }
        return false;
    }

    public static void addToSession(String key, String value){

    }

    public static String getFromSession(String key){
        return session.getAttribute(key).toString();
    }

    public static void clearSession(){
        if(session!=null){
            removeAttrFromSession("mail");
            removeAttrFromSession("name");
            removeAttrFromSession("pass");
            removeAttrFromSession("token");
        }
    }

    public static void removeAttrFromSession(String key){
        session.removeAttribute(key);
    }
}
