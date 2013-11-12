package ilib.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/11/13
 * Time: 6:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Conf {

    private static Properties pro;
    private static String projPath;
    private static String appConfPath;

    static {
        projPath = System.getProperty("user.dir");
        appConfPath = projPath + "/iserver/conf/app.conf";
        try {
            pro = new Properties();
            FileInputStream fis = new FileInputStream(appConfPath);
            pro.load(fis);
            fis.close(); //测试了下读完就可以关了，放在内存了？！
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return pro.getProperty(key);
    }

    public static void setKV(String key, String value) {
        try {
            FileOutputStream fos = new FileOutputStream(appConfPath);
            pro.setProperty(key,value);
            pro.store(fos,"modified by iserver app");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
