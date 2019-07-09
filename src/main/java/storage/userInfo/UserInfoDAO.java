package storage.userInfo;

/**
 * Интерфейс для сущности UserInfo
 */
public interface UserInfoDAO {
    /**
     * Добавляет нового пользователя
     *
     * @param userInfo Информация пользователя
     * @return Удалось ли добавить пользователя
     */
    public boolean addUserInfo(UserInfo userInfo);

    /**
     * Вернет пользователя по его имени
     *
     * @param name Имя пользователя
     * @return Вернет true, если удалось добавить пользователя
     */
    public UserInfo getInfoUserByName(String name);

    /**
     * Вернет пользователя по его токену
     *
     * @param token токен пользователя
     * @return Вернет пользователя
     */
    public UserInfo getInfoUserByToken(String token);

    /**
     * Проверет занято ли имя пользователя
     *
     * @param name Имя пользователя
     * @return Вернет true, если имя найдено
     */
    public boolean checkUserName(String name);

    /**
     * Проверит занята ли почта
     *
     * @param mail Почта пользователя
     * @return Вернет true, если почта найдена
     */
    public boolean checkMail(String mail);
}
