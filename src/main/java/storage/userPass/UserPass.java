package storage.userPass;

import java.util.UUID;

/**
 * Таблица с паролями пользователей
 */
public class UserPass
{
    /**
     * id в таблице
     */
    private String id;
    /**
     * id пользователя
     */
    private String idUser;
    /**
     * Пароль пользователя
     */
    private String pass;

    /**
     * Конструктор для таблицы UserPass с созданием id
     *
     * @param idUser id пользователя
     * @param pass   Пароль пользователя
     */
    public UserPass(String idUser, String pass)
    {
        this.id = UUID.randomUUID().toString();
        this.idUser = idUser;
        this.pass = pass;
    }

    /**
     * Конструктор для таблицы UserPass с 3 параметрами
     *
     * @param id     id в таблице
     * @param idUser id пользователя
     * @param pass   Пароль в таблице
     */
    public UserPass(String id, String idUser, String pass)
    {
        this.id = id;
        this.idUser = idUser;
        this.pass = pass;
    }

    /**
     * @return Вернет id из таблицы UserPass
     */
    public String getId()
    {
        return id;
    }

    /**
     * Установит новый id
     *
     * @param id id из таблицы UserPass
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @return Вернет id пользователя из таблицы UserPass
     */
    public String getIdUser()
    {
        return idUser;
    }

    /**
     * Установит новый id пользователя
     *
     * @param idUser id пользователя из таблицы UserPass
     */
    public void setIdUser(String idUser)
    {
        this.idUser = idUser;
    }

    /**
     * @return Вернет пароль пользователя
     */
    public String getPass()
    {
        return pass;
    }

    /**
     * Установит новый пароль пользователя
     *
     * @param pass Новый пароль
     */
    public void setPass(String pass)
    {
        this.pass = pass;
    }
}
