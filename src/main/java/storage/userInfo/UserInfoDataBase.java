package storage.userInfo;

import storage.ConnectionDataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserInfoDataBase implements UserInfoDAO {

    @Override
    public boolean addUserInfo(UserInfo userInfo) {

        try {

            String sqlInsertUserInfo = "INSERT INTO USER_INFO VALUES (?, ?, ?, ?);\n";

            ConnectionDataBase.getConnection().setAutoCommit(false);

            // Первая транзакция
            PreparedStatement addUserInfo = ConnectionDataBase.getConnection().prepareStatement(sqlInsertUserInfo);

            addUserInfo.setString(1, userInfo.getId());
            addUserInfo.setString(2, userInfo.getName());
            addUserInfo.setString(3, userInfo.getMail());
            addUserInfo.setString(4, userInfo.getInfo());
            addUserInfo.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public UserInfo getInfoUserByName(String name) {
        ResultSet resultSet = null;
        try {
            Statement statement = ConnectionDataBase.getConnection().createStatement();

            resultSet = statement.executeQuery("SELECT * FROM USER_INFO WHERE NAME = '" + name + "';\n");

            while (resultSet.next()) {
                return new UserInfo(resultSet.getString("ID"), resultSet.getString("NAME"), resultSet.getString("MAIL"), resultSet.getString("INFO"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    @Override
    public UserInfo getInfoUserByToken(String token) {
        ResultSet resultSet = null;
        try {
            Statement statement = ConnectionDataBase.getConnection().createStatement();

            resultSet = statement.executeQuery("SELECT * FROM USER_INFO UI, (SELECT * FROM USER_TOKEN WHERE TOKEN = '" + token + "') UT WHERE UT.ID_USER = UI.ID;\n");

            while (resultSet.next()) {
                return new UserInfo(resultSet.getString("ID"), resultSet.getString("NAME"), resultSet.getString("MAIL"), resultSet.getString("INFO"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    @Override
    public boolean checkUserName(String name) {
        try {
            Statement statement = ConnectionDataBase.getConnection().createStatement();

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
            Statement statement = ConnectionDataBase.getConnection().createStatement();

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
