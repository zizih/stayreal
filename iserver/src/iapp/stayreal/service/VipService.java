package iapp.stayreal.service;

import iapp.stayreal.dao.VipDao;
import iapp.model.Vip;
import live.hz.iserver.lib.db.iexception.SqlException;

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
    public static List<Vip> all() throws SqlException, IllegalAccessException {
        return new VipDao().all();
    }

}
