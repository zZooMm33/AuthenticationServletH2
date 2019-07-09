package storage.userToken;

import storage.PropReader;

/**
 * Фабрика для интерфейса UserTokenImpl
 */
public class UserTokenFactory {
    /**
     * Вернет интерфейсе для UserTokenImpl
     *
     * @return Вернет интерфейсе для UserTokenImpl в зависимаости от настроек в config.properties
     */
    public UserTokenImpl factoryMethod() {

        String storageType = PropReader.getVal("storageType");

        if (storageType.equals("database")) return new UserTokenDataBase();
        else if (storageType.equals("txt")) return new UserTokenTxtFile();
        else return null;
    }
}
