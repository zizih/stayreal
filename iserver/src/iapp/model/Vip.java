package iapp.model;

import ilib.db.iannotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/7/13
 * Time: 9:15 PM
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "vip")
public class Vip {

    //mysql 数据库中int(2),int(11)拿回来的时候都是Long类型,为了提高容错能力，设计成Long
    @Key(auto = true, name = "id")
    Long id;

    @Colum(name = "name")
    String name;

    @Colum(name = "desc")
    String desc;

    boolean humorous;

    public Vip() {
    }

    public Vip(String desc, Long id, String name) {
        this.desc = desc;
        this.id = id;
        this.name = name;
    }

    public void setHumorous(boolean boo) {
        this.humorous = boo;
    }

    public boolean getHumorous() {
        return humorous;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
