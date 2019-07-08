package storage;

import storage.userInfo.UserInfoFactory;
import storage.userInfo.UserInfoImpl;
import storage.userPass.UserPassFactory;
import storage.userPass.UserPassImpl;
import storage.userToken.UserTokenFactory;
import storage.userToken.UserTokenImpl;

/**
 * Класс для работы с сущностями
 */
public class StorageSingleton {

    /**
     * Синглтон интерфейса UserInfoImpl
     */
    private static UserInfoImpl userInfoSingleton = null;
    /**
     * Синглтон интерфейса UserPassImpl
     */
    private static UserPassImpl userPassSingleton = null;
    /**
     * Синглтон интерфейса UserTokenImpl
     */
    private static UserTokenImpl userTokenSingleton = null;

    /**
     * @return Вернет UserInfoImpl, если его нет, то создаст
     */
    public static UserInfoImpl getUserInfoSingleton() {
        if (userInfoSingleton == null) {
            userInfoSingleton = new UserInfoFactory().factoryMethod();
        }

        return userInfoSingleton;
    }

    /**
     * @return Вернет UserPassImpl, если его нет, то создаст
     */
    public static UserPassImpl getUserPassSingleton() {
        if (userPassSingleton == null) {
            userPassSingleton = new UserPassFactory().factoryMethod();
        }

        return userPassSingleton;
    }

    /**
     * @return Вернет UserTokenImpl, если его нет, то создаст
     */
    public static UserTokenImpl getUserTokenSingleton() {
        if (userTokenSingleton == null) {
            userTokenSingleton = new UserTokenFactory().factoryMethod();
        }

        return userTokenSingleton;
    }
}
