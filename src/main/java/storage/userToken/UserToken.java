package storage.userToken;

import java.util.UUID;

/**
 * Таблица токенов пользователей
 */
public class UserToken {
    /**
     * id в таблице UserToken
     */
    private String id;
    /**
     * id пользователя
     */
    private String idUser;
    /**
     * Токен в таблице UserToken
     */
    private String token;

    /**
     * Пустой конструктор для билдера
     */
    public UserToken() {
        this.id = UUID.randomUUID().toString();
    }

    /**
     * @param id     id в таблице UserToken
     * @param idUser id пользователя
     * @param token  Токен пользователя
     */
    public UserToken(String id, String idUser, String token) {
        this.id = id;
        this.idUser = idUser;
        this.token = token;
    }

    /**
     * @param idUser id пользователя
     * @param token  Токен пользователя
     */
    public UserToken(String idUser, String token) {
        this.id = UUID.randomUUID().toString();
        this.idUser = idUser;
        this.token = token;
    }

    /**
     * @return Вернет id из таблицы UserToken
     */
    public String getId() {
        return id;
    }

    /**
     * Установит новый id в таблице UserToken
     *
     * @param id новый id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return Вернет id пользователя
     */
    public String getIdUser() {
        return idUser;
    }


    /**
     * @param idUser Новый id пользователя
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
     * @return Вернет токен пользователя
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token Новый токен
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Метод для создания билдера
     *
     * @return
     */

    public static UserToken.Builder newBuilder() {
        return new UserToken().new Builder();
    }

    /**
     * Паттерн билдер
     */
    public class Builder {
        public void setToken(String token) {
            UserToken.this.token = token;
        }

        public void setId(String id) {
            UserToken.this.id = id;
        }

        public void setIdUser(String idUser) {
            UserToken.this.idUser = idUser;
        }

        public UserToken Build() {
            UserToken userToken = new UserToken();

            userToken.setId(UserToken.this.id);
            userToken.setIdUser(UserToken.this.idUser);
            userToken.setToken(UserToken.this.token);

            return userToken;
        }
    }
}
