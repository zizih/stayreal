package iapp.model;

import ilib.db.iannotation.Key;
import ilib.db.iannotation.Table;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/12/13
 * Time: 10:13 PM
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "album")
public class Album {

    @Key(auto = true, name = "TAGID")
    private Long tagid;

    private String tagname;

    private boolean select;

    public boolean getSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public void setTagid(Long tagid) {
        this.tagid = tagid;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public Long getTagid() {
        return tagid;
    }

    public String getTagname() {
        return tagname;
    }
}
