package ilib.db;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
public class BaseDao<T> implements IBaseDao<T> {


    @Override
    public List<T> fetch(Class clzz) throws SqlException, IllegalAccessException {
        //获取表名
        Table table = (Table) clzz.getAnnotation(Table.class);
        String tableName = table.name();
        return fetch(clzz, "select * from " + tableName);
    }

    @Override
    public T fetch(Class clzz, int id) throws SqlException, IllegalAccessException {
        //获取表名
        Table table = (Table) clzz.getAnnotation(Table.class);
        String tableName = table.name();
        return fetch(clzz, "select * from " + tableName + "where id=" + id).get(0);
    }

    @Override
    public List<T> fetch(Class clzz, int limit, int start) throws SqlException, IllegalAccessException {
        //获取表名
        Table table = (Table) clzz.getAnnotation(Table.class);
        String tableName = table.name();
        return fetch(clzz, "select * from " + tableName + " limit " + limit + "," + start);
    }

    @Override
    public List<T> fetch(Class clzz, String sql) throws SqlException, IllegalAccessException {
        //获取表名
        Table table = (Table) clzz.getAnnotation(Table.class);
        String tableName = table.name();
        MysqlHelper dbHelper = MysqlHelper.getInstance();
        ResultSet rs = dbHelper.execQuery(sql);
        List<T> list = null;
        try {
            list = new ArrayList<T>();
            String[] colums = getColums(clzz);
            while (rs.next()) {
                T t = (T) clzz.newInstance();
                for (String filed : colums) {
                    //mysql 数据库中int(2),int(11)都会拿回来的时候都是Long类型，How can I do?
                    callSetter(t, filed, rs.getObject(rs.findColumn(filed)));
                }
                System.out.println(new Gson().toJson(t));
                list.add(t);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public T insert(T t) throws SqlException, IllegalAccessException {
        if (!t.getClass().isAnnotationPresent(Table.class)) {
            throw new SqlException(t, "对象不是实体类");
        }
        //获取表名
        String tableName = t.getClass().getAnnotation(Table.class).name();

        //遍历对象的属性和值
        StringBuilder colums = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        int kvSize = 0;
        String[] columsStr = getColums(t.getClass(), "id");
        Object[][] valuesObj = getValues(t, "id");
        if (columsStr.length != valuesObj.length) {
            throw new SqlException(t, "参数长度不对");
        }
        for (int i = 0; i < columsStr.length; i++) {
            if (columsStr[i] == null || columsStr[i] == "") {
                continue;
            }
            if (!(kvSize == 0)) {
                colums.append(",");
                values.append(",");
            } else {
                kvSize++;
            }
            colums.append("`").append(columsStr[i]).append("`");
            //这里要对值做不同的append。
            Object tmp = valuesObj[i][1];
            if (tmp.equals(Integer.class) || tmp.equals(int.class)) {
                values.append(valuesObj[i][0]);
            } else {
                values.append("'").append(valuesObj[i][0]).append("'");
            }
            System.out.println("value: " + valuesObj[i][0]);
            System.out.println("value: " + valuesObj[i][1]);

        }
        colums.append(") ");
        values.append(")");
        String sql = "insert into " + tableName + colums + "values" + values;

        //发送sql语句并执行
        MysqlHelper dbHelper = MysqlHelper.getInstance();
        dbHelper.execSQL(sql);
        Log.i("发送一条SQl语句: " + sql);
        return t;
    }

    private void callSetter(T t, String fieldName, Object value) {
        String setterName = "set" + fieldName.substring(0, 1).toUpperCase()
                + fieldName.substring(1, fieldName.length());
        try {
            Class clzz = t.getClass();
            Field field = clzz.getDeclaredField(fieldName);
            Class type = field.getType();
            //做更多基本类型检查
            if (type == int.class) {
                type = Integer.class;
            }
            Method method = clzz.getDeclaredMethod(setterName, type);
            method.invoke(t, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    //属性名都是String
    private String[] getColums(Class clzz, String... excludes) throws SqlException, IllegalAccessException {
        Field[] fields = clzz.getDeclaredFields();
        String[] colums = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            f.setAccessible(true);

            boolean ifContinue = false;
            for (String col : excludes) {
                if (f.getName().equals(col)) {
                    ifContinue = true;
                    break;
                }
            }
            if (ifContinue) continue;

            //认为非@transparent属性都是数据库字段
            if (!f.isAnnotationPresent(Transparent.class)) {
                colums[i] = f.getName();
            }
        }
        return colums;
    }

    //只是声明为Object,具体类型未知，sorry啊。
    private Object[][] getValues(T t, String... excludes) throws SqlException, IllegalAccessException {
        assert (isAutoKeyNull(t));
        if (!t.getClass().isAnnotationPresent(Table.class)) {
            throw new SqlException(t, "对象不是实体类");
        }
        Field[] fields = t.getClass().getDeclaredFields();
        Object[][] values = new Object[fields.length][2];
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            f.setAccessible(true);

            try {
                boolean ifContinue = false;
                for (String col : excludes) {
                    if (f.getName().equals(col)) {
                        ifContinue = true;
                        break;
                    }
                }
                if (ifContinue) continue;
                //认为非@transparent属性都是数据库字段
                if (!f.isAnnotationPresent(Transparent.class)) {
                    values[i][0] = f.get(t);
                    values[i][1] = f.getType();
                }

                if (f.isAnnotationPresent(Colum.class)) {
                    Colum colum = f.getAnnotation(Colum.class);
                    if (colum.type().equals("DATETIME")) {
                        SimpleDateFormat spdf = new SimpleDateFormat(colum.format());
                        values[i][0] = spdf.format(f.get(t));
                        values[i][1] = colum.type();
                    }
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return values;
    }

    //判断自增主键是否正确
    public boolean isAutoKeyNull(T t) throws SqlException, IllegalAccessException {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field f : fields) {
            try {
                if (f.isAnnotationPresent(Key.class) && f.get(t) == null) {
                    throw new SqlException(t, "非自增主键不能为空");
                }
            } catch (IllegalAccessException e) {
                throw e;
            }
        }
        return true;
    }

}
