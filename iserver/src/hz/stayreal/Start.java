package hz.stayreal;

import hz.stayreal.ref.net.HttpServer;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/6/13
 * Time: 11:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class Start {

    public static void main(String[] args) {

        HttpServer server = new HttpServer(9600);
        server.setController(new Controller());

    }

}