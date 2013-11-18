package live.hz.iserver.lib.net;

import live.hz.iserver.lib.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/5/13
 * Time: 10:36 PM
 * HttpServer means SocketServer
 */
public class HttpServer extends Thread {

    private ExecutorService cachePool = Executors.newCachedThreadPool();
    private ServerSocket serverSocket;

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
                    cachePool.execute(new SocketThread(client));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeSocket(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
