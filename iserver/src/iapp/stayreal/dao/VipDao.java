package iapp.stayreal.dao;

import iapp.model.Vip;
import ilib.db.BaseDao;
import ilib.app.IDao;
import ilib.db.iexception.SqlException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/7/13
 * Time: 9:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class VipDao extends BaseDao<Vip> implements IDao<Vip> {


    @Override
    public List<Vip> get(int id) throws SqlException, IllegalAccessException {
        return null;
    }

    @Override
    public List<Vip> all() throws SqlException, IllegalAccessException {
        return fetch(Vip.class);
    }

    @Override
    public List<Vip> all(int start, int limt) throws SqlException, IllegalAccessException {
        return null;
    }

    @Override
    public List<Vip> add(Vip model) throws SqlException, IllegalAccessException {
        return null;
    }

    @Override
    public List<Vip> update(Vip model) throws SqlException, IllegalAccessException {
        return null;
    }

    @Override
    public List<Vip> delete(int id) throws SqlException, IllegalAccessException {
        return null;
    }
}
