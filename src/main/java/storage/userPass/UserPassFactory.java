package storage.userPass;

import storage.PropReader;

/**
 * Фабрика для интерфейса UserPassDAO
 */
public class UserPassFactory
{
    /**
     * Вернет интерфейсе для UserPassDAO
     *
     * @return Вернет интерфейсе для UserPassDAO в зависимаости от настроек в config.properties
     */
    public UserPassDAO factoryMethod()
    {

        String storageType = PropReader.getVal("storageType");

        if (storageType.equals("database")) return new UserPassDataBase();
        else if (storageType.equals("txt")) return new UserPassTxtFile();
        else return null;
    }
}
