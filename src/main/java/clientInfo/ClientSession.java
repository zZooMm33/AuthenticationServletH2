package clientInfo;

import javax.servlet.http.HttpSession;

/**
 * Класс реализующий работу с сессией
 */
public class ClientSession
{
    private static HttpSession session;

    /**
     * Выбор сессии если ее нет
     *
     * @param ses сессия
     */
    public static void setSessionIfNotSet(HttpSession ses){
        if(session==null){
            session=ses;
        }
    }

    /**
     * Проверка наличия токена в сессии
     * @return true/false
     */
    public static boolean checkToken(){
        if(session.getAttribute("token")!=null){
            return true;
        }
        return false;
    }


    /**
     * Проверка наличия имени в сессии
     * @return true/false
     */
    public static boolean checkName(){
        if(session.getAttribute("name")!=null){
            return true;
        }
        return false;
    }

    /**
     * Добавляет значение по ключу в сессию
     * @param key ключ
     * @param value значение
     */
    public static void addToSession(String key, String value){

    }

    /**
     * Получить значение из сессии
     * @param key ключ
     * @return значение из сессии
     */
    public static String getFromSession(String key){
        return session.getAttribute(key).toString();
    }

    /**
     * Метод удаления основных значений из сессии
     */
    public static void clearSession(){
        if(session!=null){
            removeAttrFromSession("mail");
            removeAttrFromSession("name");
            removeAttrFromSession("pass");
            removeAttrFromSession("token");
        }
    }

    /**
     * Метод удаляющий значение по ключу
     * @param key ключ
     */
    public static void removeAttrFromSession(String key){
        session.removeAttribute(key);
    }
}
