package storage.userToken;

public class UserTokenTxtFile implements UserTokenImpl {
    @Override
    public boolean addUserToken(UserToken userToken) {
        return false;
    }

    @Override
    public boolean updateTokenByIdUser(UserToken userToken) {
        return false;
    }

    @Override
    public boolean updateTokenByToken(String oldToken, String newToken) {
        return false;
    }
}
