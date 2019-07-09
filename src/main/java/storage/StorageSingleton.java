package storage;

import storage.userInfo.UserInfoFactory;
import storage.userInfo.UserInfoDAO;
import storage.userPass.UserPassDAO;
import storage.userPass.UserPassFactory;
import storage.userToken.UserTokenFactory;
import storage.userToken.UserTokenDAO;

/**
 * Класс для работы с сущностями
 */
public class StorageSingleton {

    /**
     * Синглтон интерфейса UserInfoDAO
     */
    private static UserInfoDAO userInfoSingleton = null;
    /**
     * Синглтон интерфейса UserPassDAO
     */
    private static UserPassDAO userPassSingleton = null;
    /**
     * Синглтон интерфейса UserTokenDAO
     */
    private static UserTokenDAO userTokenSingleton = null;

    /**
     * @return Вернет UserInfoDAO, если его нет, то создаст
     */
    public static UserInfoDAO getUserInfoSingleton() {
        if (userInfoSingleton == null) {
            userInfoSingleton = new UserInfoFactory().factoryMethod();
        }

        return userInfoSingleton;
    }

    /**
     * @return Вернет UserPassDAO, если его нет, то создаст
     */
    public static UserPassDAO getUserPassSingleton() {
        if (userPassSingleton == null) {
            userPassSingleton = new UserPassFactory().factoryMethod();
        }

        return userPassSingleton;
    }

    /**
     * @return Вернет UserTokenDAO, если его нет, то создаст
     */
    public static UserTokenDAO getUserTokenSingleton() {
        if (userTokenSingleton == null) {
            userTokenSingleton = new UserTokenFactory().factoryMethod();
        }

        return userTokenSingleton;
    }
}
