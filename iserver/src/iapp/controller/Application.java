package iapp.controller;

import iapp.model.Comment;
import iapp.stayreal.service.CommentService;
import ilib.app.BaseController;
import ilib.db.iexception.SqlException;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/6/13
 * Time: 3:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Application extends BaseController {

    public void index() {
        render("index.html");
//        renderFile("index.html");
    }

    /**
     * 首页comments列表
     */
    public void fetchComments() {
        List<Comment> comments = null;
        try {
            comments = CommentService.all();
        } catch (SqlException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        renderJSON(comments);
    }

    public void insertComment(String content) {
        Comment comment = new Comment();
        comment.setTime(new Date());
        comment.setContent(content);
        try {
            CommentService.add(comment);
            renderJSON("Insert OK");
            return;
        } catch (SqlException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        renderJSON("Insert Err");
    }

    public void insert(String name, String jack) {
        List<Comment> comments = null;
        try {
            comments = CommentService.all();
        } catch (SqlException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        renderJSON(comments);
    }

}
