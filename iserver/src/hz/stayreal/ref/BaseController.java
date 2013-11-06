package hz.stayreal.ref;

import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/6/13
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseController {

    private PrintWriter respon;

    protected void render(String template) {
        respon.println(template);
        respon.flush();
        respon.close();
    }

    public void setRespon(PrintWriter respon) {
        this.respon = respon;
    }
}
