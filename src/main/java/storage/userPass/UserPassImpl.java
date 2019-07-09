package storage.userPass;

/**
 * Интерфейс для сущности UserPass
 */
public interface UserPassImpl {

    /**
     * Добавляет пароль для пользователя
     *
     * @param userPass Информация пользователя
     * @return true, если удалось добавить пароль
     */
    public boolean addUserPass(UserPass userPass);

    /**
     * Вернет пароль пользователя
     *
     * @param name Имя пользователя
     * @return Вернет сущность UserPass
     */
    public UserPass getPass(String name);
}
