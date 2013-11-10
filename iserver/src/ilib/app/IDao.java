package ilib.app;

import ilib.db.iexception.SqlException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/9/13
 * Time: 9:49 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IDao<T> {

    public abstract List<T> get(int id) throws SqlException, IllegalAccessException;

    public abstract List<T> all() throws SqlException, IllegalAccessException;

    public abstract List<T> all(int start, int limt) throws SqlException, IllegalAccessException;

    public abstract List<T> add(T model) throws SqlException, IllegalAccessException;

    public abstract List<T> update(T model) throws SqlException, IllegalAccessException;

    public abstract List<T> delete(int id) throws SqlException, IllegalAccessException;

}
