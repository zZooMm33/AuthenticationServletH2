package storage.userInfo;

import storage.PropReader;

/**
 * Фабрика для интерфейса UserInfoImpl
 */
public class UserInfoFactory {
    /**
     * Вернет интерфейсе для UserInfoImpl
     *
     * @return Вернет интерфейсе для UserInfoImpl в зависимаости от настроек в config.properties
     */
    public UserInfoImpl factoryMethod() {

        String storageType = PropReader.getVal("storageType");

        if (storageType.equals("database")) return new UserInfoDataBase();
        else if (storageType.equals("txt")) return new UserInfoTxtFile();
        else return null;
    }
}

