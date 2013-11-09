package webapp.stayreal.service;

import webapp.stayreal.dao.VipDao;
import webapp.model.Vip;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/7/13
 * Time: 9:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class VipService {

    /**
     * 没有用依赖注入的思想，所以暂时先以static形式存在
     * @return
     */
    public static List<Vip> all() {
        return VipDao.all();
    }

}
