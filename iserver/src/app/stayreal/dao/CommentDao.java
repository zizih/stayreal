package app.stayreal.dao;

import app.model.Comment;
import live.hz.iserver.lib.db.BaseDao;
import live.hz.iserver.lib.app.IDao;
import live.hz.iserver.lib.db.iexception.SqlException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/9/13
 * Time: 9:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommentDao extends BaseDao<Comment> implements IDao<Comment> {


    @Override
    public List<Comment> get(int id) throws SqlException, IllegalAccessException {
        return null;
    }

    @Override
    public List<Comment> all() throws SqlException, IllegalAccessException {
        return fetch(Comment.class);
    }

    @Override
    public List<Comment> all(int start, int limt) throws SqlException, IllegalAccessException {
        return null;
    }

    @Override
    public Comment add(Comment model) throws SqlException, IllegalAccessException {
        return insert(model);
    }

    @Override
    public List<Comment> update(Comment model) throws SqlException, IllegalAccessException {
        return null;
    }

    @Override
    public List<Comment> delete(int id) throws SqlException, IllegalAccessException {
        return null;
    }
}
