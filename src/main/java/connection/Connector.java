package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Connector implements AutoCloseable {


    private Connection cn = null;
    private Statement st = null;
    private static Connector connector = null;


    private Connector() {
        this.cn = getConnection();
        this.st = getStatement();
    }

    public static Connector getInstance() {
        if (connector != null) {
            return connector;
        }
        return new Connector();
    }

    public Connection getConnection() {
        if (cn != null) {
            return cn;
        }
        ResourceBundle res = ResourceBundle.getBundle(connection.ConstConn.DATA_BASE_PROPIRTIES_FILE_NAME);

        String driver = res.getString(connection.ConstConn.DRIVER_NAME);

        String url = res.getString(connection.ConstConn.URL);
        String userName = res.getString(connection.ConstConn.USER_NAME);
        String password = res.getString(connection.ConstConn.PASSWORD);


        try {
            Class.forName(driver).newInstance();
        } catch (InstantiationException e) {
            System.out.println(connection.ConstConn.MESSAGE_BAD_DRIVER);
            e.printStackTrace();

        } catch (IllegalAccessException e) {
            System.out.println(connection.ConstConn.MESSAGE_BAD_DRIVER);
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            System.out.println(connection.ConstConn.MESSAGE_BAD_DRIVER);
            e.printStackTrace();
        }

        try {
            cn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException throwables) {
            System.out.println(connection.ConstConn.MESSAGE_INVALID_DATA_BASE_PROPERTIES);
            throwables.printStackTrace();
        }
        return cn;
    }

    public Statement getStatement() {
        if (st != null) {
            return st;
        }
        try {
            st = getConnection().createStatement();
        } catch (SQLException throwables) {
            System.out.println(connection.ConstConn.MESSAGE_STATEMENT_DO_NOT_CREATED);
            throwables.printStackTrace();
        }
        return st;
    }

    public void close() throws SQLException {
        if (st != null) {
            st.close();
        }
        if (cn != null) {
            cn.close();
        }
    }
}
