import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class AuthDB {

    /**
     * Удаление таблиц, создание таблиц и тестовых пользователей
     * @param statement Подключение к БД
     * @throws SQLException
     */
    public static void createTablesUsers(Statement statement) throws SQLException {

        final String dropTableUser = "DROP TABLE if exists USER_INFO;\n";
        final String dropTablePass = "DROP TABLE if exists USER_PASS;\n";
        final String dropTableToken = "DROP TABLE if exists USER_TOKEN;\n";


        final String createTableUser = "CREATE TABLE USER_INFO(ID UUID PRIMARY KEY, NAME VARCHAR(255), MAIL VARCHAR(255), INFO VARCHAR(255));\n";
        final String createTablePass = "CREATE TABLE USER_PASS(ID UUID PRIMARY KEY,ID_USER UUID ,PASS VARCHAR(255));\n";
        final String createTableToken = "CREATE TABLE USER_TOKEN(ID UUID PRIMARY KEY, ID_USER UUID, TOKEN VARCHAR(255));\n";

// Удаление табл перед созданием
        statement.execute(dropTableUser);
        statement.execute(dropTablePass);
        statement.execute(dropTableToken);

// Создание табл
        statement.executeUpdate(createTableUser);
        statement.executeUpdate(createTablePass);
        statement.executeUpdate(createTableToken);

// Создаем 2 птелей для проверки
        String idUser1 = insertUser(statement, "zZooMm", "zoom@mail.ru", "ЧТО ТО");
        insertUserPass(statement, idUser1, "Hasdasd1");
        insertUserToken(statement, idUser1, "token131231");

        String idUser2 = insertUser(statement, "bezumnuixleb", "bezumnuixleb@mail.ru", "ЧТО то про юзера");
        insertUserPass(statement, idUser2, "SSSSSSS");
        insertUserToken(statement, idUser2, "token999999");

    }


    /**
     * Создание таблиц бд если их нет
     * @param statement Подключение к БД
     * @throws SQLException
     */
    public static void checkTables(Statement statement) throws SQLException {

        final String createTableUser = "CREATE TABLE if not exists USER_INFO(ID UUID PRIMARY KEY, NAME VARCHAR(255), MAIL VARCHAR(255), INFO VARCHAR(255));\n";
        final String createTablePass = "CREATE TABLE if not exists USER_PASS(ID UUID PRIMARY KEY,ID_USER UUID ,PASS VARCHAR(255));\n";
        final String createTableToken = "CREATE TABLE if not exists USER_TOKEN(ID UUID PRIMARY KEY, ID_USER UUID, TOKEN VARCHAR(255));\n";

        // Создание табл
        statement.executeUpdate(createTableUser);
        statement.executeUpdate(createTablePass);
        statement.executeUpdate(createTableToken);
    }

    /**
     * @param statement Подключение к БД
     * @param userName Имя пользователя (логин)
     * @param userMail Почта пользователя
     * @param userInfo Информация о пользователе
     * @return id пользователя
     * @throws SQLException
     */
    public static String insertUser(Statement statement, String userName, String userMail, String userInfo) throws SQLException {
        String newUserId = UUID.randomUUID().toString();
        String sql = "INSERT INTO USER_INFO VALUES ( '" + newUserId + "', '" + userName + "', '" + userMail + "', '" + userInfo + "');\n";
        statement.execute(sql);
        return newUserId;
    }

    /**
     * @param statement Подключение к БД
     * @param userId id пользователя
     * @param pass Пароль пользователя
     * @return id добавленной строки в БД
     * @throws SQLException
     */
    public static String insertUserPass(Statement statement, String userId, String pass) throws SQLException {
        String newUserPassId = UUID.randomUUID().toString();
        String sql = "INSERT INTO USER_PASS values ('" + newUserPassId + "', '" + userId + "', '" + pass + "');\n";
        statement.execute(sql);
        return newUserPassId;
    }

    /**
     * @param statement Подключение к БД
     * @param userId id пользователя
     * @param token Новый токен пользователя
     * @return id добавленной строки в БД
     * @throws SQLException
     */
    public static String insertUserToken(Statement statement, String userId, String token) throws SQLException {
        String newUserTokenId = UUID.randomUUID().toString();
        String sql = "INSERT INTO USER_TOKEN values ('" + newUserTokenId + "', '" + userId + "', '" + token + "');\n";
        statement.execute(sql);
        return newUserTokenId;
    }

    /**
     * @param statement Подключение к БД
     * @return Вернет всех пользователей (ID, INFO, MAIL, NAME)
     * @throws SQLException
     */
    public static ResultSet selectUsers(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM USER_INFO;\n");
        return resultSet;
    }

    /**
     * @param statement Подключение к БД
     * @param token Токен пользователя
     * @return id пользователя
     * @throws SQLException
     */
    public static String getIdUserByToken(Statement statement, String token) throws SQLException {

        ResultSet resultSet = statement.executeQuery("SELECT ID_USER FROM USER_TOKEN WHERE token = '" + token +  "';\n");

        resultSet.next();

        return resultSet.getString("ID_USER");
    }

    /**
     * @param statement Подключение к БД
     * @param name Имя пользователя
     * @return Информация о пользователе
     * @throws SQLException
     */
    public static ResultSet getInfoUserByName(Statement statement, String name) throws SQLException {

        ResultSet resultSet = statement.executeQuery("SELECT * FROM USER_INFO WHERE NAME = '" + name + "';\n");

        return resultSet;
    }

    /**
     * @param statement Подключение к БД
     * @param token Токен пользователя
     * @return Вся информация польззователя (ID, INFO, MAIL, NAME)
     * @throws SQLException
     */
    public static ResultSet getInfoUserByToken(Statement statement, String token) throws SQLException {

        // SELECT ui.ID, ui.INFO, ui.MAIL, ui.NAME FROM (SELECT * FROM USER_INFO) ui, (SELECT * FROM USER_TOKEN WHERE TOKEN = 'token999999') ut WHERE ui.ID = ut.ID_USER;
        ResultSet resultSet = statement.executeQuery("SELECT ui.ID, ui.INFO, ui.MAIL, ui.NAME FROM (SELECT * FROM USER_INFO) ui, (SELECT * FROM USER_TOKEN WHERE TOKEN = '" + token + "') ut WHERE ui.ID = ut.ID_USER;");

        return resultSet;
    }

    /**
     * @param statement Подключение к БД
     * @param name Имя пользователя
     * @return Пароль пользователя по его имени
     * @throws SQLException
     */
    public static String getPass(Statement statement, String name) throws SQLException {

        //SELECT up.PASS FROM (SELECT ID FROM USER_INFO WHERE NAME = 'bezumnuixleb') ui, (SELECT * FROM USER_PASS ) up WHERE ui.ID = up.ID_USER ;
        ResultSet resultSet = statement.executeQuery("SELECT up.PASS FROM (SELECT ID FROM USER_INFO WHERE NAME = '" + name +  "') ui, (SELECT * FROM USER_PASS ) up WHERE ui.ID = up.ID_USER ;\n");
        resultSet.next();
        return resultSet.getString("PASS");
    }

    /**
     * @param statement Подключение к БД
     * @param idUser id пользователя у которого меняется токен
     * @param token Новый токен пользователя
     * @throws SQLException
     */
    public static void updateTokenByIdUser(Statement statement, String idUser, String token) throws SQLException {

        //String sql = "UPDATE " + nameTable +  " SET FIRST_NAME = '" + student.getFirstName() + "', SECOND_NAME = '" + student.getSecondName() + "', AGE = " + student.getAge() + " WHERE ID = '" + student.getId() + "'";
        String sql = "UPDATE USER_TOKEN SET TOKEN = '" + token +"' WHERE ID_USER = '" + idUser +  "';\n";
        statement.execute(sql);
    }

    /**
     * @param statement Подключение к БД
     * @param oldToken Старый токен пользователя
     * @param newToken Новый токен пользователя
     * @throws SQLException
     */
    public static void updateTokenByIdToken(Statement statement, String oldToken, String newToken) throws SQLException {

        //String sql = "UPDATE " + nameTable +  " SET FIRST_NAME = '" + student.getFirstName() + "', SECOND_NAME = '" + student.getSecondName() + "', AGE = " + student.getAge() + " WHERE ID = '" + student.getId() + "'";
        String sql = "UPDATE USER_TOKEN SET TOKEN = '" + newToken +"' WHERE TOKEN = '" + oldToken +  "';\n";
        statement.execute(sql);
    }
}
