package ilib.util;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/9/13
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileUtil {

    private static String projPath;
    private static String viewsPath;

    static {
        projPath = System.getProperty("user.dir");
        viewsPath = projPath + "/iserver/src/iapp/views/";
//        viewsPath = projPath + "/";
    }

    /**
     * 读取一个文件的内容
     */
    public static String views(String fileName) {
        return fileStr(viewsPath + fileName);
    }

    public static String staticFile(String absPath) {
        System.out.println(projPath + absPath);
        return fileStr(projPath + absPath).toString();
    }

    /**
     * 读取一个文件的内容
     */
    public static String fileStr(String absPath) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(absPath)));
            String str = null;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取一个文件的内容
     */
    public static InputStream fileIS(String absPath) {
        try {
            return new FileInputStream(projPath + absPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
