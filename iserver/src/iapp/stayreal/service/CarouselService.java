package iapp.stayreal.service;

import iapp.model.Carousel;
import iapp.stayreal.dao.CarouselDao;
import ilib.db.iexception.SqlException;

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
