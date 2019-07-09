package storage.userToken;

/**
 * Интерфейс для сущности UserToken
 */
public interface UserTokenImpl {
    /**
     * Добавляет токен для пользователя
     *
     * @param userToken Информация пользователя
     * @return Удалось ли добавить пользователя
     */
    public boolean addUserToken(UserToken userToken);

    /**
     * Изменит токен по сущности UserToken
     *
     * @param userToken id пользователя
     * @return Вернет true, если удалось ли изменить токен
     */
    public boolean updateTokenByIdUser(UserToken userToken);

    /**
     * Изменит токен по старому токену
     *
     * @param oldToken Старый токен
     * @param newToken Новый токен
     * @return Вернет true, если удалось ли изменить токен
     */
    public boolean updateTokenByToken(String oldToken, String newToken);
}
