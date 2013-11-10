package iapp.stayreal.service;

import iapp.model.Comment;
import iapp.stayreal.dao.CommentDao;
import ilib.db.iexception.SqlException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/9/13
 * Time: 9:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommentService {

    public static List<Comment> all() throws SqlException, IllegalAccessException {
        return new CommentDao().all();
    }

}
