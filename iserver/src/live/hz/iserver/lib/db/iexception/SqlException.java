package live.hz.iserver.lib.db.iexception;

import com.google.gson.Gson;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/10/13
 * Time: 2:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class SqlException extends Exception {

    public SqlException(Object obj, String desc) {
        super("\n来自iserver DB 操作封装处理抛出的异常"
                + "\n刚操作了这个对象，检查是否有错："
                + new Gson().toJson(obj)
                + " 提示："
                + desc);
    }
}
