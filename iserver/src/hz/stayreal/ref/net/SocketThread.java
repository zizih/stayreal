package hz.stayreal.ref.net;

import hz.stayreal.web.controller.Application;
import hz.stayreal.ref.model.Header;
import hz.stayreal.ref.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/6/13
 * Time: 1:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class SocketThread extends Thread {

    private Application controller;
    private Socket client;

    public SocketThread(Socket client) {
        this.client = client;
    }

    public void setController(Application controller) {
        this.controller = controller;
    }

    private PrintWriter getResponPrintWriter(OutputStream os) {
        PrintWriter out = new PrintWriter(os, true);
        out.println("HTTP/1.1 200 OK");
        return out;
    }

    @Override
    public void run() {
        super.run();
        try {
            if (client == null) return;
            if (controller == null) {
                closeSocket(client);
                return;
            }
            Header header = new Header(client.getInputStream());
            //暂时不处理/favicon.ico请求
            if (header.getRequestURI() == null || header.getRequestURI().equals("/favicon.ico")) {
                closeSocket(client);
                return;
            }
            Log.i("recieved one client: " + header.getHost());
            Class controllerClass = controller.getClass();
            Method[] methods = controllerClass.getDeclaredMethods();
            for (Method method : methods) {
                String uri = header.getRequestURI();
                if (uri == null) {
                    closeSocket(client);
                    return;
                }
                int qIndex = uri.indexOf("?");
                if (qIndex == -1) {
                    closeSocket(client);
                    return;
                }
                String path = uri.substring(1, qIndex);
                if (path.equals(method.getName())) {
                    try {
                        //长度以controller接受为准
                        int paramsLength = method.getParameterTypes().length;
                        String paramstr = uri.substring(qIndex + 1, uri.length());
                        String[] params = paramstr.split("&");
                        String[] paramsValues = new String[paramsLength];
                        for (int i = 0; i < paramsLength; i++) {
                            paramsValues[i] = params[i].substring(params[i].indexOf("=") + 1,
                                    params[i].length());
                        }
                        controller.setRespon(getResponPrintWriter(client.getOutputStream()));
                        method.invoke(controller, paramsValues);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeSocket(client);
    }

    public void closeSocket(Socket socket) {
        try {
            if (client != null && !client.isClosed() && controller != null) {
                controller.setRespon(getResponPrintWriter(client.getOutputStream()));
                controller.render404();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
