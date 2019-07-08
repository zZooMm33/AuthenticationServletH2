package storage;

import java.sql.*;
import java.util.UUID;

/**
 * Интерфейс для Storage для работы с бд
 */
class StorageDataBase implements Storage {

    /**
     * Синглтон для подключения к БД
     */
    static private Connection connection = null;

    /**
     * Создает подключение к Бд
     *
     * @return Connection or null
     */
    public static Connection getConnection() {
        try {
            if (connection == null) {
                String host = PropReader.getVal("host"),
                        pass = PropReader.getVal("pass"),
                        user = PropReader.getVal("user");

                Class.forName("org.h2.Driver");

                if (pass.equals("null")) {
                    pass = null;
                }

                connection = DriverManager.getConnection(host, user, pass);
            }

            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean addUser(String userName, String userMail, String userInfo, String userPass, String userToken) {

        String newUserId = UUID.randomUUID().toString();
        String newUserPassId = UUID.randomUUID().toString();
        String newUserTokenId = UUID.randomUUID().toString();

        String sqlInsertUserInfo = "INSERT INTO USER_INFO VALUES ( '" + newUserId + "', '" + userName + "', '" + userMail + "', '" + userInfo + "');\n";
        String sqlInserUserPass = "INSERT INTO USER_PASS values ('" + newUserPassId + "', '" + newUserId + "', '" + userPass + "');\n";
        String sqlInserUserToken = "INSERT INTO USER_TOKEN values ('" + newUserTokenId + "', '" + newUserId + "', '" + userToken + "');\n";

        try {
            Statement statement = getConnection().createStatement();

            statement.execute(sqlInsertUserInfo);
            statement.execute(sqlInserUserPass);
            statement.execute(sqlInserUserToken);
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public UserInStorage getInfoUserByName(String name) {

        ResultSet resultSet = null;
        try {
            Statement statement = getConnection().createStatement();

            resultSet = statement.executeQuery("SELECT * FROM USER_INFO WHERE NAME = '" + name + "';\n");

            while (resultSet.next()) {
                return new UserInStorage(resultSet.getString("ID"), resultSet.getString("NAME"), resultSet.getString("MAIL"), resultSet.getString("INFO"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    @Override
    public UserInStorage getInfoUserByToken(String token) {
        ResultSet resultSet = null;
        try {
            Statement statement = getConnection().createStatement();

            resultSet = statement.executeQuery("SELECT ID_USER FROM USER_TOKEN WHERE token = '" + token + "';\n");

            while (resultSet.next()) {
                return new UserInStorage(resultSet.getString("ID"), resultSet.getString("NAME"), resultSet.getString("MAIL"), resultSet.getString("INFO"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    @Override
    public String getPass(String name) {

        ResultSet resultSet = null;
        try {
            Statement statement = getConnection().createStatement();

            resultSet = statement.executeQuery("SELECT up.PASS FROM (SELECT ID FROM USER_INFO WHERE NAME = '" + name + "') ui, (SELECT * FROM USER_PASS ) up WHERE ui.ID = up.ID_USER ;\n");
            resultSet.next();
            return resultSet.getString("PASS");

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateTokenByIdUser(String idUser, String token) {

        String sql = "UPDATE USER_TOKEN SET TOKEN = '" + token + "' WHERE ID_USER = '" + idUser + "';\n";
        try {
            Statement statement = getConnection().createStatement();

            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTokenByToken(String oldToken, String newToken) {
        String sql = "UPDATE USER_TOKEN SET TOKEN = '" + newToken + "' WHERE TOKEN = '" + oldToken + "';\n";
        try {
            Statement statement = getConnection().createStatement();

            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkUserName(String name) {

        try {
            Statement statement = getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM USER_INFO WHERE NAME = '" + name + "';\n");
            resultSet.next();
            if (resultSet.getString("ID") != null) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    @Override
    public boolean checkMail(String mail) {
        try {
            Statement statement = getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM USER_INFO WHERE MAIL = '" + mail + "';\n");
            resultSet.next();
            if (resultSet.getString("ID") != null) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }
}

