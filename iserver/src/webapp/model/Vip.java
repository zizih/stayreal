package webapp.model;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/7/13
 * Time: 9:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Vip {

    int id;

    String name;

    String desc;

    public Vip() {
    }

    public Vip(String desc, int id, String name) {
        this.desc = desc;
        this.id = id;
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
