package app.stayreal.dao;

import app.model.Carousel;
import live.hz.iserver.lib.db.BaseDao;
import live.hz.iserver.lib.db.iexception.SqlException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/12/13
 * Time: 10:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class CarouselDao extends BaseDao<Carousel> {


    public Carousel get(int id) throws SqlException, IllegalAccessException {
        return fetch(Carousel.class, id);
    }

    public List<Carousel> get(String key, Object value) throws SqlException, IllegalAccessException {
        return fetch(Carousel.class, key, value);
    }

    public List<Carousel> all() throws SqlException, IllegalAccessException {
        return fetch(Carousel.class);
    }

}
