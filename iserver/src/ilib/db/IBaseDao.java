package ilib.db;

import ilib.db.iexception.SqlException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/9/13
 * Time: 9:52 PM
 * 数据库的基本操作封装，由关系型操作到对象操作
 */
public interface IBaseDao<T> {


    /**
     * 获取表所有记录，并封装成对象列表。
     *
     * @param clzz
     * @return
     * @throws ilib.db.iexception.SqlException
     *
     * @throws IllegalAccessException
     */
    public List<T> fetch(Class clzz) throws SqlException, IllegalAccessException;

    /**
     * 根据ID获取一条记录，并封装成对象
     *
     * @param clzz
     * @param id
     * @return
     * @throws ilib.db.iexception.SqlException
     *
     * @throws IllegalAccessException
     */
    public T fetch(Class clzz, int id) throws SqlException, IllegalAccessException;

    /**
     * 分页获取对象列表
     *
     * @param clzz
     * @param limit
     * @param start
     * @return
     * @throws ilib.db.iexception.SqlException
     *
     * @throws IllegalAccessException
     */
    public List<T> fetch(Class clzz, int limit, int start) throws SqlException, IllegalAccessException;

    /**
     * 可以自己发送sql语句哦
     *
     * @param clzz
     * @param sql
     * @return
     * @throws ilib.db.iexception.SqlException
     *
     * @throws IllegalAccessException
     */
    public List<T> fetch(Class clzz, String sql) throws SqlException, IllegalAccessException;

    public T insert(T t) throws SqlException, IllegalAccessException;

}
