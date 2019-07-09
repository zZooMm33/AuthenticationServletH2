package storage.userPass;

public class UserPassTxtFile implements UserPassDAO {
    @Override
    public boolean addUserPass(UserPass userPass) {
        return false;
    }

    @Override
    public UserPass getPass(String name) {
        return null;
    }
}
