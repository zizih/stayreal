package iapp.stayreal.dao;

import iapp.model.Comment;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/9/13
 * Time: 9:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommentDao implements IDao<Comment>{


    @Override
    public List<Comment> get(int id) {
        return null;
    }

    @Override
    public List<Comment> all() {

        return null;  
    }

    @Override
    public List<Comment> all(int start, int limt) {
        return null;  
    }

    @Override
    public List<Comment> insert(Comment model) {
        return null;  
    }

    @Override
    public List<Comment> update(Comment model) {
        return null;  
    }

    @Override
    public List<Comment> delete(int id) {
        return null;  
    }
}
