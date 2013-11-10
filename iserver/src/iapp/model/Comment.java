package iapp.model;

import ilib.db.iannotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/9/13
 * Time: 9:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "comment")
public class Comment {

    @Key(auto = true, name = "id")
    Long id;

    String time;

    String content;

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }

    public String getTime() {
        return time;
    }
}
