package hz.stayreal.web.dao;

import hz.stayreal.ref.db.MysqlHelper;
import hz.stayreal.web.model.Vip;
import hz.stayreal.web.service.VipService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        MysqlHelper dbHelper = MysqlHelper.getInstance();
        ResultSet rs = dbHelper.execQuery("select * from vip");
        List list = null;
        try {
            list = new ArrayList<Vip>();
            Vip vip = null;
            while (rs.next()) {
                vip = new Vip();
                vip.setId(rs.getInt("id"));
                vip.setName(rs.getString("name"));
                vip.setDesc(rs.getString("desc"));
                list.add(vip);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
