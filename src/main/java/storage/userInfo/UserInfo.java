package storage.userInfo;

import java.util.UUID;

/**
 * Пользователь
 */
public class UserInfo
{
    /**
     * id пользователя
     */
    private String id;
    /**
     * Имя пользователя
     */
    private String name;
    /**
     * mail пользователя
     */
    private String mail;
    /**
     * info пользователя
     */
    private String info;

    /**
     * Конструктор для создания пользователя
     *
     * @param name Имя пользователя
     * @param mail Почта пользователя
     * @param info Информация пользователя
     */
    public UserInfo(String name, String mail, String info)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.mail = mail;
        this.info = info;
    }

    /**
     * Конструктор для создания пользователя
     *
     * @param id   id пользователя
     * @param name Имя пользователя
     * @param mail Почта пользователя
     * @param info Информация пользователя
     */
    public UserInfo(String id, String name, String mail, String info)
    {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.info = info;
    }

    /**
     * @return Вернет id пользователя
     */
    public String getId()
    {
        return id;
    }

    /**
     * Изменит id пользователя
     *
     * @param id Новый id пользователя
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @return Вернет имя пользователя
     */
    public String getName()
    {
        return name;
    }

    /**
     * Изменит имя пользователя
     *
     * @param name Новоя имя пользователя
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return Вернет почту пользователя
     */
    public String getMail()
    {
        return mail;
    }

    /**
     * Изменит почту пользователя
     *
     * @param mail Новоя почта пользователя
     */
    public void setMail(String mail)
    {
        this.mail = mail;
    }

    /**
     * @return Вернет информацию о пользователе
     */
    public String getInfo()
    {
        return info;
    }

    /**
     * Изменит информацию о пользователе
     *
     * @param info Новая информация о пользователе
     */
    public void setInfo(String info)
    {
        this.info = info;
    }
}
