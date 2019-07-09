package storage.userToken;

import storage.PropReader;

/**
 * Фабрика для интерфейса UserTokenDAO
 */
public class UserTokenFactory {
    /**
     * Вернет интерфейсе для UserTokenDAO
     *
     * @return Вернет интерфейсе для UserTokenDAO в зависимаости от настроек в config.properties
     */
    public UserTokenDAO factoryMethod() {

        String storageType = PropReader.getVal("storageType");

        if (storageType.equals("database")) return new UserTokenDataBase();
        else if (storageType.equals("txt")) return new UserTokenTxtFile();
        else return null;
    }
}
