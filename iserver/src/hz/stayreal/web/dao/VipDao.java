package hz.stayreal.web.dao;

import hz.stayreal.web.model.Vip;
import hz.stayreal.web.service.VipService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/7/13
 * Time: 9:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class VipDao {

    public static List<Vip> all() {
        return VipService.all();
    }

}
