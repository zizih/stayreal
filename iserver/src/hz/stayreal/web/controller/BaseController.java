package hz.stayreal.web.controller;

import com.google.gson.Gson;
import hz.stayreal.ref.model.Header;

import java.io.PrintWriter;

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

    protected void render(String template) {
        respon.println("Content-Type:text/html;charset=UTF8");
        respon.println();
        respon.println(template);
        respon.flush();
        respon.close();
    }

    protected void renderJSON(Object object) {
        respon.println("Content-Type:text/json;charset=UTF8");
        respon.println();
        respon.println(new Gson().toJson(object));
        respon.flush();
        respon.close();
    }

    public void render404() {
        respon.println("Content-Type:text/html;charset=UTF8");
        respon.println();
        respon.println("<404> iServer Sorry!");
        respon.flush();
        respon.close();
    }

    public void setRespon(PrintWriter respon) {
        this.respon = respon;
    }

    public void setHeader(Header header) {
        this.header = header;
    }
}
