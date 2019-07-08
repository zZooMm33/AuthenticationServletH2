package storage;

/**
 * Класс для работы с Storage
 */
public class StorageSingleton {
    /**
     * Storage с которым надо работать
     */
    private static Storage storageSingleton = null;

    /**
     * Создает Storage если его нет или вернет, если он есть
     *
     * @return Вернет Storage
     */
    public static Storage getStorageSingleton() {
        if (storageSingleton == null) {
            storageSingleton = new FactoryStorage().factoryMethod();
        }

        return storageSingleton;
    }
}
