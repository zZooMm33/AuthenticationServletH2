package storage.Impl;

/**
 * Класс для работы с Storage
 */
class StorageSingleton {
    /**
     * Storage с которым надо работать
     */
    private static Storage storageSingleton = null;

    /**
     * Создает Storage если его нет или вернет, если он есть
     * @return Вернет Storage
     */
    public Storage getStorageSingleton(){
        if (this.storageSingleton == null){
            storageSingleton = new ConcreteCreatorStorage().factoryMethod();
        }
        return storageSingleton;
    }
}
