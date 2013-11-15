package iapp.stayreal.service;

import iapp.model.Album;
import iapp.stayreal.dao.AlbumDao;
import live.hz.iserver.lib.db.iexception.SqlException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/12/13
 * Time: 10:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class AlbumService {

    public static  List<Album> all() throws SqlException, IllegalAccessException {
        return new AlbumDao().all();
    }
}
