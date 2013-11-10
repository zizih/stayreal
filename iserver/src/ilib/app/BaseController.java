package ilib.app;

import com.google.gson.Gson;
import ilib.net.Header;
import ilib.util.FileUtil;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/6/13
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseController {

    private Header header;
    private PrintWriter respon;
    private OutputStream responOS;

    public void render(String templateName) {
        respon.println("HTTP/1.1 200 OK");
        renderUtil("text/html", template(templateName));
    }

    public void renderJSON(Object object) {
        respon.println("HTTP/1.1 202 OK");
        renderUtil("text/json", new Gson().toJson(object));
    }

    public void render404() {
        respon.println("HTTP/1.1 404 Not Found");
        renderUtil("text/html", "<404> iServer Sorry!");
    }

    public void renderOs2Js(InputStream is) {
        respon.println("HTTP/1.1 200 OK");
        respon.println("Content-Type:text/javascript;charset=UTF8");
        respon.println();
        renderOS(is);
        respon.close();
    }

    public void renderOs2Css(InputStream is) {
        respon.println("HTTP/1.1 200 OK");
        respon.println("Content-Type:text/css;charset=UTF8");
        respon.println();
        renderOS(is);
        respon.close();
    }

    public void renderOs2Image(InputStream is) {
        respon.println("HTTP/1.1 200 OK");
        respon.println("Content-Type:image/jpeg;charset=UTF8");
        respon.println();
        renderOS(is);
        respon.close();
    }

    public void renderOs2Font(InputStream is) {
        respon.println("HTTP/1.1 200 OK");
        respon.println("Content-Type:font/opentype;charset=UTF8");
        respon.println();
        renderOS(is);
        respon.close();
    }

    private void renderOS(InputStream is) {
        byte[] buffer = new byte[2048];
        int len;
        try {
            while ((len = is.read(buffer)) != -1) {
                responOS.write(buffer, 0, len);
            }
            responOS.flush();
            is.close();
            responOS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按MIME类型返回文本
     *
     * @param contentType
     * @param str
     */
    private void renderUtil(String contentType, String str) {
        respon.println("Content-Type:" + contentType + ";charset=UTF8");
        respon.println();
        respon.println(str);
        respon.flush();
        respon.close();
    }

    /**
     * 读取一个文件的内容并以二进制文件形式返回
     */
    public void renderFile(String fileName) {

        try {
            PrintStream out = new PrintStream(responOS, true);
            File fileToSend = new File(fileName);
            System.out.println("exist: " + fileToSend.exists());
            if (fileToSend.exists() && !fileToSend.isDirectory()) {
                out.println("HTTP/1.0 200 OK");//返回应答消息,并结束应答
                out.println("Content-Type:application/binary");
                out.println("Content-Length:" + fileToSend.length());// 返回内容字节数
                out.println();// 根据 HTTP 协议, 空行将结束头信息

                FileInputStream fis = new FileInputStream(fileToSend);
                byte data[] = new byte[fis.available()];
                fis.read(data);
                out.write(data);
                System.out.println(data);
                out.close();
                fis.close();
            }
        } catch (Exception e) {
            System.out.println("传送文件时出错:" + e.getLocalizedMessage());
        }
    }

    private String template(String templateName) {
        return FileUtil.views(templateName).toString();
    }

    /**
     * 共socket处理线程回调设置controller参数
     */
    public void setResponOS(OutputStream responOS) {
        this.responOS = responOS;
        respon = new PrintWriter(responOS, true);
    }

    /**
     * 给Socket Server处理http请求时设置Header（未完成）
     */
    public void setHeader(Header header) {
        this.header = header;
    }

}
