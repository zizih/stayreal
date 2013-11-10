package iapp.stayreal.dao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/9/13
 * Time: 9:49 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IDao<T> {

    public abstract List<T> get(int id);

    public abstract List<T> all();

    public abstract List<T> all(int start, int limt);

    public abstract List<T> insert(T model);

    public abstract List<T> update(T model);

    public abstract List<T> delete(int id);

}
