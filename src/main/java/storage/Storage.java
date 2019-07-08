package storage;

import storage.entities.UserInfo;
import storage.entities.UserPass;
import storage.entities.UserToken;

/**
 * Интерфейс для работы с БД/txt...
 */
public interface Storage {
    /**
     * Добавляет нового пользователя
     *
     * @param userInfo  Информация пользователя
     * @param userPass  Пароль пользователя
     * @param userToken Токен пользователя
     * @return Удалось ли добавить пользователя
     */
    public boolean addUser(UserInfo userInfo, UserPass userPass, UserToken userToken);

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
     * Вернет пароль пользователя
     *
     * @param name Имя пользователя
     * @return Вернет сущность UserPass
     */
    public UserPass getPass(String name);

    /**
     * Изменит токен по имени пользователя
     *
     * @param idUser id пользователя
     * @param token  токен пользователя
     * @return Вернет true, если удалось ли изменить токен
     */
    public boolean updateTokenByIdUser(String idUser, String token);

    /**
     * Изменит токен по старому токену
     *
     * @param oldToken Старый токен
     * @param newToken Новый токен
     * @return Вернет true, если удалось ли изменить токен
     */
    public boolean updateTokenByToken(String oldToken, String newToken);

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

