package iapp.stayreal.dao;

import ilib.db.MysqlHelper;

import java.lang.reflect.Field;

import ilib.db.iannotation.*;
import ilib.db.iexception.SqlException;
import ilib.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/9/13
 * Time: 9:52 PM
 * 数据库的基本操作封装，由关系型操作到对象操作
 */
public class BaseDao<T> {

    public T insert(T t) throws SqlException {
        if (!t.getClass().isAnnotationPresent(Table.class)) {
            throw new SqlException(t, "对象不是实体类");
        }
        //获取表名
        String tableName = t.getClass().getAnnotation(Table.class).name();

        //遍历对象的属性和值
        StringBuilder colums = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        Field[] fields = t.getClass().getDeclaredFields();
        int kvSize = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.isAnnotationPresent(Key.class)) {
                    Key key = field.getAnnotation(Key.class);
                    if (!key.auto()) {
                        Object obj = field.get(t);
                        if (obj == null) {
                            throw new SqlException(t, "非自增主键不能为空");
                        }
                        if (kvSize == 0) {
                            colums.append("`").append(key.name()).append("`");
                            values.append("'").append(obj.toString()).append("'");
                            kvSize++;
                        } else {
                            colums.append(",`").append(key.name()).append("`");
                            values.append(",'").append(obj.toString()).append("'");
                        }
                    }
                } else if (field.isAnnotationPresent(Colum.class) &&
                        !field.isAnnotationPresent(Transparent.class)) {

                    //判断对象属性是否有赋值
                    Object obj = field.get(t);
                    if (obj != null) {
                        String columName = field.getAnnotation(Colum.class).name();
                        String value = field.get(t).toString();
                        if (kvSize == 0) {
                            colums.append("`").append(columName).append("`");
                            values.append("'").append(value).append("'");
                            kvSize++;
                        } else {
                            colums.append(",`").append(columName).append("`");
                            values.append(",'").append(value).append("'");
                        }
                    }
                } else {
                    throw new SqlException(t, "对象不包含需要持久化的属性，即数据库字段");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        colums.append(") ");
        values.append(")");
        String sql = "insert into " + tableName + colums + "values" + values;

        MysqlHelper dbHelper = MysqlHelper.getInstance();
        dbHelper.execSQL(sql);
        Log.i("发送一条SQl语句: " + sql);
        return t;
    }

}
