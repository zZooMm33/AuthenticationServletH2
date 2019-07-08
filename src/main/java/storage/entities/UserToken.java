package storage.entities;

/**
 * Таблица токенов пользователей
 */
public class UserToken {
    private String id;
    private String idUser;
    private String token;


    public UserToken(String idUser, String token) {
        this.idUser = idUser;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
