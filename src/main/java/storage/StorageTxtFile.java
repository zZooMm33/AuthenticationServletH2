package storage;


/**
 * Интерфейс для Storage для работы с txt файлом
 */
class StorageTxtFile implements Storage
{

    @Override
    public boolean addUser(String userName, String userMail, String userInfo, String userPass, String userToken)
    {
        return false;
    }

    @Override
    public UserInStorage getInfoUserByName(String name)
    {
        return null;
    }

    @Override
    public UserInStorage getInfoUserByToken(String token)
    {
        return null;
    }

    @Override
    public String getPass(String name)
    {
        return null;
    }

    @Override
    public boolean updateTokenByIdUser(String idUser, String token)
    {
        return false;
    }

    @Override
    public boolean updateTokenByToken(String oldToken, String newToken)
    {
        return false;
    }

    @Override
    public boolean checkUserName(String name)
    {
        return false;
    }

    @Override
    public boolean checkMail(String mail)
    {
        return false;
    }
}
