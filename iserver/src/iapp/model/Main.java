package iapp.model;

import ilib.db.BaseDao;
import ilib.db.iannotation.*;
import ilib.db.iexception.SqlException;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/10/13
 * Time: 12:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {
        Person p = new Person();
        p.setId(11);
        p.setName("hezi");
        p.setDesc("一个人很伤心。。。");
        p.setAnimal(true);
//        Field[] fields = p.getClass().getDeclaredFields();
//
//        boolean b = p.getClass().isAnnotationPresent(Table.class);
//        if (b) {
//            Table table = p.getClass().getAnnotation(Table.class);
//            for (Field f : fields) {
//                try {
//                    System.out.println(f.get(p));
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                }
//                if (f.isAnnotationPresent(Colum.class)) {
//                    try {
//                        Object obj = f.get(p);
//                        Colum colum = f.getAnnotation(Colum.class);
//                        System.out.println("name: " + colum.name());
//                        System.out.println("value: " + obj.toString());
//                        System.out.println();
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (f.isAnnotationPresent(Key.class)) {
//                    System.out.println(f.getAnnotation(Key.class).auto());
//                }
//            }
//        }
//
        Vip me = new Vip();
        me.setName("rain");
        me.setDesc("Sorry,I miss you! Aaron!!");

        BaseDao<Vip> dao = new BaseDao<Vip>();
        try {
//            idao.insert(me);
//            idao.fetch(Vip.class, 8);
            dao.fetch(Vip.class, 1, 2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SqlException e) {
            e.printStackTrace();
        }

    }

}

@Table(name = "person")
class Person {

    @Key(auto = true, name = "id")
    int id;

    @Colum(name = "name")
    String name;

    @Transparent
    @Colum(name = "desc")
    String desc;

    @Colum(name = "isanimal")
    boolean isAnimal;

    public void setAnimal(boolean animal) {
        isAnimal = animal;
    }

    public boolean isAnimal() {
        return isAnimal;
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
