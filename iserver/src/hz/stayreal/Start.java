package hz.stayreal;

import hz.stayreal.ref.net.HttpServer;
import hz.stayreal.web.controller.Application;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/6/13
 * Time: 11:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class Start {

    private static int PORT = 9600;

    public static void main(String[] args) {
        custom(args);
        HttpServer server = new HttpServer(PORT);
        server.setController(new Application());
        server.start();
    }

    private static void custom(String[] args) {
        if (args.length == 1) {
            PORT = Integer.parseInt(args[0]);
            return;
        }
    }

}