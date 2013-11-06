package hz.stayreal.ref.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/6/13
 * Time: 6:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class Log {

    public static void i(String info) {
        Logger logger = Logger.getLogger("");
        logger.log(Level.INFO, info);
    }
}
