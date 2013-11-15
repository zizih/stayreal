package iapp;

import live.hz.iserver.lib.net.HttpServer;
import live.hz.iserver.lib.util.Conf;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/6/13
 * Time: 11:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class Start {

    private static int PORT;

    public static void main(String[] args) {
        PORT = Integer.parseInt(Conf.getValue("http.port"));
        custom(args);
        HttpServer server = new HttpServer(PORT);
        server.start();
    }

    private static void custom(String[] args) {
        if (args.length == 1) {
            PORT = Integer.parseInt(args[0]);
            return;
        }
    }

}