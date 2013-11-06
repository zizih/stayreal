package hz.stayreal.ref.net;

import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/6/13
 * Time: 1:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class SocketThread extends Thread {

    private Socket socket;

    public SocketThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();

    }
}
