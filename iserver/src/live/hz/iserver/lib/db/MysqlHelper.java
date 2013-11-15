package live.hz.iserver.lib.db;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/7/13
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class MysqlHelper implements IDBHelper {

    private static MysqlHelper instance;
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/stayreal";
    private String user = "root";
    private String passwd = "hk";
    private Connection conn;

    public MysqlHelper() {
        createConnection();
    }

    public static MysqlHelper getInstance() {
        if (instance == null) {
            instance = new MysqlHelper();
        }
        return instance;
    }

    @Override
    public Connection createConnection() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, passwd);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean execSQL(String sql) {
        try {
            Statement statment = conn.createStatement();
            boolean b = statment.execute(sql);
            statment.close();
            return b;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ResultSet execQuery(String sql) {
        try {
            return conn.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void closeConn() {
        try {
            this.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
