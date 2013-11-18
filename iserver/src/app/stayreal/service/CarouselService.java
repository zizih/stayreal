package app.stayreal.service;

import app.model.Carousel;
import app.stayreal.dao.CarouselDao;
import live.hz.iserver.lib.db.iexception.SqlException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/12/13
 * Time: 10:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class CarouselService {

    public static List<Carousel> get(String key, Object value) throws SqlException, IllegalAccessException {
        return new CarouselDao().get(key, value);
    }

}
