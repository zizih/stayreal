package iapp.stayreal.dao;

import iapp.model.Album;
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
public class AlbumDao extends BaseDao<Album> {

    public List<Album> all() throws SqlException, IllegalAccessException {
        return fetch(Album.class);
    }
}
