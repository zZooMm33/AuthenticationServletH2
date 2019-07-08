package utils;


import javax.servlet.http.HttpServletRequest;

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
        if (req.getSession().getAttribute("token") != null)
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
        if (req.getSession().getAttribute("name") != null)
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
        req.getSession().setAttribute(key, value);
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
        return req.getSession().getAttribute(key).toString();
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
        req.getSession().removeAttribute(key);
    }
}
