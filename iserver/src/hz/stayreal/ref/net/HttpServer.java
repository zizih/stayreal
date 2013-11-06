package hz.stayreal.ref.net;

import hz.stayreal.Controller;
import hz.stayreal.ref.model.Header;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/5/13
 * Time: 10:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class HttpServer implements Runnable {

    private ServerSocket serverSocket;
    private Controller controller;

    public HttpServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("server is running on port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (serverSocket == null) {
            System.exit(1);
        } else {
            new Thread(this).start();
        }

    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket client = serverSocket.accept();

                if (client != null) {
                    Header header = new Header(client.getInputStream());
                    Method[] methods = controller.getClass().getDeclaredMethods();
                    for (Method method : methods) {
                        String uri = header.getRequestURI();
                        if (uri != null) {
                            int qIndex = uri.indexOf("?");
                            if (qIndex != -1) {
                                String path = uri.substring(1, qIndex);
                                if (path.equals(method.getName())) {
                                    try {
                                        int paramsLength = method.getParameterTypes().length; //长度以controller接受为准
                                        String paramstr = uri.substring(qIndex + 1, uri.length());
                                        String[] params = paramstr.split("&");
                                        String[] paramsValues = new String[paramsLength];
                                        for (int i = 0; i < paramsLength; i++) {
                                            paramsValues[i] = params[i].substring(params[i].indexOf("=") + 1, params[i].length());
                                        }
                                        //begin of output stream
                                        controller.setRespon(getResponPrintWriter(client.getOutputStream()));
                                        method.invoke(controller, paramsValues);
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    } catch (InvocationTargetException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private PrintWriter getResponPrintWriter(OutputStream os) {
        PrintWriter out = new PrintWriter(os, true);
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type:text/html;charset=UTF8");
        out.println("aasfsadf");
        out.println();
        return out;
    }

    public void closeSocket() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
