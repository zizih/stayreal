package hz.stayreal.ref.net;

import hz.stayreal.Controller;
import hz.stayreal.ref.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/5/13
 * Time: 10:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class HttpServer extends Thread {

    private ServerSocket serverSocket;
    private Controller controller;

    public HttpServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            Log.i("server is running on port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket client = serverSocket.accept();
                if (client != null) {
                    SocketThread clientThread = new SocketThread(client);
                    clientThread.setController(controller);
                    clientThread.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void closeSocket(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
