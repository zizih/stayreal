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
@Table(name = "carousel")
public class Carousel {

    @Key(auto = true, name = "id")
    private Long id;

    private Integer albumid;

    private boolean isfirst;

    private String imgurl;

    private String imgalt;

    private String href;

    private String caption;

    public boolean getIsfirst() {
        return isfirst;
    }

    public void setIsfirst(boolean isfirst) {
        this.isfirst = isfirst;
    }

    public void setAlbumid(Integer albumid) {
        this.albumid = albumid;
    }

    public Integer getAlbumid() {
        return albumid;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImgalt(String imgalt) {
        this.imgalt = imgalt;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getCaption() {
        return caption;
    }

    public String getHref() {
        return href;
    }

    public Long getId() {
        return id;
    }

    public String getImgalt() {
        return imgalt;
    }

    public String getImgurl() {
        return imgurl;
    }
}
