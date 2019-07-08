package storage;

/**
 * Класс для фабрики для Storage
 */
class FactoryStorage
{
    /**
     * Вернет интерфейсе для Storage
     *
     * @return Вернет интерфейсе для Storage
     */
    public Storage factoryMethod()
    {

        String storageType = PropReader.getVal("storageType");

        if (storageType.equals("database")) return new StorageDataBase();
        else if (storageType.equals("txt")) return new StorageTxtFile();
        else return null;
    }
}
