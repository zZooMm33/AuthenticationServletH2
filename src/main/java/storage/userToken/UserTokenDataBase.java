package storage.userToken;

import storage.ConnectionDataBase;

import java.sql.SQLException;
import java.sql.Statement;

public class UserTokenDataBase implements UserTokenImpl {
    @Override
    public boolean addUserToken(UserToken userToken) {
        String sqlInserUserToken = "INSERT INTO USER_TOKEN values ('" + userToken.getId() + "', '" + userToken.getIdUser() + "', '" + userToken.getToken() + "');\n";

        try {
            Statement statement = ConnectionDataBase.getConnection().createStatement();

            statement.execute(sqlInserUserToken);
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean updateTokenByIdUser(UserToken userToken) {
        String sql = "UPDATE USER_TOKEN SET TOKEN = '" + userToken.getToken() + "' WHERE ID_USER = '" + userToken.getIdUser() + "';\n";
        try {
            Statement statement = ConnectionDataBase.getConnection().createStatement();

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
            Statement statement = ConnectionDataBase.getConnection().createStatement();

            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
