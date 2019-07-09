package storage.userInfo;

import storage.PropReader;

/**
 * Фабрика для интерфейса UserInfoDAO
 */
public class UserInfoFactory {
    /**
     * Вернет интерфейсе для UserInfoDAO
     *
     * @return Вернет интерфейсе для UserInfoDAO в зависимаости от настроек в config.properties
     */
    public UserInfoDAO factoryMethod() {

        String storageType = PropReader.getVal("storageType");

        if (storageType.equals("database")) return new UserInfoDataBase();
        else if (storageType.equals("txt")) return new UserInfoTxtFile();
        else return null;
    }
}

