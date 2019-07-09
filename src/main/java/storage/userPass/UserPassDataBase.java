package storage.userPass;

import storage.ConnectionDataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserPassDataBase implements UserPassDAO {
    @Override
    public boolean addUserPass(UserPass userPass) {


        try {

            String sqlInserUserPass = "INSERT INTO USER_PASS values (?, ?, ?);\n";

            PreparedStatement addUserPass = ConnectionDataBase.getConnection().prepareStatement(sqlInserUserPass);

            addUserPass.setString(1, userPass.getId());
            addUserPass.setString(2, userPass.getIdUser());
            addUserPass.setString(3, userPass.getPass());
            addUserPass.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public UserPass getPass(String name) {
        ResultSet resultSet = null;
        try {
            Statement statement = ConnectionDataBase.getConnection().createStatement();

            resultSet = statement.executeQuery("SELECT * FROM (SELECT ID FROM USER_INFO WHERE NAME = '" + name + "') ui, (SELECT * FROM USER_PASS ) up WHERE ui.ID = up.ID_USER ;\n");
            resultSet.next();
            return new UserPass(resultSet.getString("ID"), resultSet.getString("ID_USER"), resultSet.getString("PASS"));

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
