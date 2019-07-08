package storage;

import storage.UserInStorage;

public interface Storage {
    public boolean addUser(String userName, String userMail, String userInfo, String userPass, String userToken);
    public UserInStorage getInfoUserByName(String name);
    public UserInStorage getInfoUserByToken(String token);
    public String getPass(String name);
    public boolean updateTokenByIdUser(String idUser, String token);
    public boolean updateTokenByToken(String oldToken, String newToken);
    public boolean checkUserName(String name);
    public boolean checkMail(String mail);
}

