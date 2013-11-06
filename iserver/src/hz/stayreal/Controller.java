package hz.stayreal;

import hz.stayreal.ref.BaseController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/6/13
 * Time: 3:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Controller extends BaseController {

    public void insert(String name, String jack) {
        List tmp =new ArrayList<String>();
        tmp.add("rain");
        tmp.add("jack");
        tmp.add("dad");
        tmp.add("arron");
        renderJSON(tmp);
    }


}
