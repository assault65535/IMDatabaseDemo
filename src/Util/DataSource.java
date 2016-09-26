package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Tnecesoc on 2016/9/6.
 */
public abstract class DataSource {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost/schemademo";
    private static final String username = "root";
    private static String password;

    private static boolean isPasswordConfirmed = false;

    private static Connection connection = null;

    protected static Statement statement = null;

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean isPasswordConfirmed() {
        return isPasswordConfirmed;
    }

    public static boolean setPassword(String password) {

        if (isPasswordConfirmed) {
            return true;
        }

        DataSource.password = password;

        try {

            initConnection();
            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        isPasswordConfirmed = true;

        return true;
    }

    protected DataSource() {}

    private static void initConnection() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement();
    }

    private static void closeConnection() throws SQLException {
        statement.close();
        connection.close();
    }

    protected static void doSQLAction(SQLAction action) {
        try {
            initConnection();
            action.act();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
