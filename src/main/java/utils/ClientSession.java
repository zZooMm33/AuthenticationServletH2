package utils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Класс реализующий работу с сессией
 */
public class ClientSession
{

    /**
     * Проверка наличия токена в сессии
     *
     * @param req запрос из сервлета
     * @return true/false
     */
    public static boolean checkToken(HttpServletRequest req)
    {
        HttpSession session = req.getSession();
        if (session != null)
            if (session.getAttribute("token") != null)
            {
                return true;
            }
        return false;
    }


    /**
     * Проверка наличия имени в сессии
     *
     * @param req запрос из сервлета
     * @return true/false
     */
    public static boolean checkName(HttpServletRequest req)
    {
        HttpSession session = req.getSession();
        if (session != null)
            if (session.getAttribute("name") != null)
            {
                return true;
            }
        return false;
    }

    /**
     * Добавляет значение по ключу в сессию
     *
     * @param req   запрос из сервлета
     * @param key   ключ
     * @param value значение
     */

    public static void addToSession(HttpServletRequest req, String key, String value)
    {
        HttpSession session = req.getSession();
        if (session != null)
            session.setAttribute(key, value);
    }

    /**
     * Получить значение из сессии
     *
     * @param req запрос из сервлета
     * @param key ключ
     * @return значение из сессии
     */
    public static String getFromSession(HttpServletRequest req, String key)
    {
        HttpSession session = req.getSession();
        if (session != null)
            return session.getAttribute(key).toString();
        else return null;
    }

    /**
     * @param req запрос из сервлета
     *            Метод удаления основных значений из сессии
     */
    public static void clearSession(HttpServletRequest req)
    {

        removeAttrFromSession(req, "mail");
        removeAttrFromSession(req, "name");
        removeAttrFromSession(req, "pass");
        removeAttrFromSession(req, "token");

    }

    /**
     * Метод удаляющий значение по ключу
     *
     * @param req запрос из сервлета
     * @param key ключ
     */
    public static void removeAttrFromSession(HttpServletRequest req, String key)
    {
        HttpSession session = req.getSession();
        if (session != null)
            session.removeAttribute(key);
    }
}
