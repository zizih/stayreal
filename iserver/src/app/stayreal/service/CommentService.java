package app.stayreal.service;

import app.model.Comment;
import app.stayreal.dao.CommentDao;
import live.hz.iserver.lib.db.iexception.SqlException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/9/13
 * Time: 9:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommentService {

    //dao实例希望能够google guice吗？
    public static List<Comment> all() throws SqlException, IllegalAccessException {
        return new CommentDao().all();
    }

    public static Comment add(Comment comment) throws SqlException, IllegalAccessException {
        return new CommentDao().add(comment);
    }

}
