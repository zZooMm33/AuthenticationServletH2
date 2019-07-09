package storage.userInfo;

public class UserInfoTxtFile implements UserInfoDAO {
    @Override
    public boolean addUserInfo(UserInfo userInfo) {
        return false;
    }

    @Override
    public UserInfo getInfoUserByName(String name) {
        return null;
    }

    @Override
    public UserInfo getInfoUserByToken(String token) {
        return null;
    }

    @Override
    public boolean checkUserName(String name) {
        return false;
    }

    @Override
    public boolean checkMail(String mail) {
        return false;
    }
}
