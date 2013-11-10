package ilib.db;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/7/13
 * Time: 7:12 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IDBHelper {

    public Connection createConnection();

    public boolean execSQL(String sql);

    public ResultSet execQuery(String sql);

    public void closeConn();
}
