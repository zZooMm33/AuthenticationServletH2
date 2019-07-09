package storage.userPass;

import storage.PropReader;

/**
 * Фабрика для интерфейса UserPassImpl
 */
public class UserPassFactory {
    /**
     * Вернет интерфейсе для UserPassImpl
     *
     * @return Вернет интерфейсе для UserPassImpl в зависимаости от настроек в config.properties
     */
    public UserPassImpl factoryMethod() {

        String storageType = PropReader.getVal("storageType");

        if (storageType.equals("database")) return new UserPassDataBase();
        else if (storageType.equals("txt")) return new UserPassTxtFile();
        else return null;
    }
}
