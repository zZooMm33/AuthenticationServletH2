package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Класс реализующий работу с куки
 */
public class ClientCookie
{
    /**
     * Выставить куку
     *
     * @param resp  ответ из сервлета
     * @param key   ключ
     * @param value значение
     */
    public static void setCookie(HttpServletResponse resp, String key, String value)
    {
        Cookie cook = new Cookie(key, value);
        resp.addCookie(cook);
    }

    /**
     * Удаление куки
     *
     * @param resp ответ из сервлета
     * @param key  ключ
     */
    public static void removeCookie(HttpServletResponse resp, String key)
    {
        Cookie cook = new Cookie(key, "");
        cook.setMaxAge(0);
        resp.addCookie(cook);
    }

    /**
     * Получение куки если она существует
     *
     * @param req запрос из сервлета
     * @param key ключ
     * @return значение куки
     */
    public static String getCookieIfExist(HttpServletRequest req, String key)
    {
        try
        {
            Cookie[] cookies = req.getCookies();
            for (int i = 0; i < cookies.length; i++)
            {
                if (cookies[i].getName().equals(key))
                {
                    return cookies[i].getValue();
                }
            }
        } catch (NullPointerException e)
        {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
