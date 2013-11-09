package webapp.controller;

import webapp.controller.BaseController;
import webapp.service.VipService;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/6/13
 * Time: 3:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Application extends BaseController {

    public void insert(String name, String jack) {
        System.out.println(name + "   " + jack);
        renderJSON(VipService.all());
    }


}
