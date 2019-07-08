package storage.entities;

import java.util.UUID;

/**
 * Таблица с паролями пользователей
 */
public class UserPass {
    private String id;
    private String idUser;
    private String pass;

    public UserPass(String idUser, String pass) {
        this.id = UUID.randomUUID().toString();
        this.idUser = idUser;
        this.pass = pass;
    }

    public UserPass(String id, String idUser, String pass) {
        this.id = id;
        this.idUser = idUser;
        this.pass = pass;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
