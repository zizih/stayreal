package iapp.stayreal.service;

import iapp.model.Carousel;
import iapp.model.Comment;
import iapp.stayreal.dao.CarouselDao;
import ilib.db.BaseDao;
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

    public static Carousel get(int id) throws SqlException, IllegalAccessException {
        return new CarouselDao().get(id);
    }


    public static List<Carousel> all() throws SqlException, IllegalAccessException {
        return new CarouselDao().all();
    }
}
