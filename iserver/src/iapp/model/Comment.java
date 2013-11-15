package iapp.model;

import live.hz.iserver.lib.db.iannotation.*;

import java.util.Date;

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
    private Long id;

    @Colum(name = "time", type = "DATETIME", format = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }

    public Date getTime() {
        return this.time;
    }
}
