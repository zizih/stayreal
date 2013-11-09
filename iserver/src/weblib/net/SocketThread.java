package weblib.net;

import webapp.controller.Application;
import webapp.controller.BaseController;
import weblib.model.Header;
import weblib.util.FileUtil;
import weblib.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/6/13
 * Time: 1:57 PM
 * Desc: 每接受到一个socket请求就创建一个线程，并调用controller相应的路由控制。
 */
public class SocketThread extends Thread {

    private BaseController controller;
    private Socket client;

    public SocketThread(Socket client) {
        this.client = client;
        this.controller = new Application();
    }

    @Override
    public void run() {
        super.run();
        try {
            if (client == null) return;
            Header header = new Header(client.getInputStream());
            //暂时不处理/favicon.ico请求
            if (header.getRequestURI() == null || header.getRequestURI().equals("/favicon.ico")) {
                closeSocket(client);
                return;
            }
            Log.i("recieved one client: " + header.getHost());
            String uri = header.getRequestURI();

            //处理public资源请求,不需要暴露给controller处理
            if (uri.startsWith("/public/css")) {
                renderCSS(FileUtil.fileIS(uri));
            }
            if (uri.startsWith("/public/js")) {
                renderJS(FileUtil.fileIS(uri));
            }


            //处理controller控制的请求
            Class controllerClass = controller.getClass();
            Method[] methods = controllerClass.getDeclaredMethods();
            for (Method method : methods) {
                if (uri == null) {
                    closeSocket(client);
                    return;
                }
                try {
                    //匹配index路径
                    if (uri.equals("/") && method.getName().equals("index")) {
                        controller.setResponOS(client.getOutputStream());
                        method.invoke(controller);
                    }
                    //匹配含有get参数的路径
                    int qIndex = uri.indexOf("?");
                    String path = uri.substring(1, qIndex == -1 ? uri.length() : qIndex);
                    if (path.equals(method.getName())) {

                        //
                        if (qIndex == -1) {
                            controller.setResponOS(client.getOutputStream());
                            method.invoke(controller);
                            return;
                        }
                        String paramstr = uri.substring(qIndex + 1, uri.length());
                        String[] params = paramstr.split("&");

                        //长度以controller请求中的参数长度为准
                        int paramsLength = method.getParameterTypes().length;
                        String[] paramsValues = new String[paramsLength];
                        for (int i = 0; i < paramsLength; i++) {
                            if (params.length < i + 1) {
                                paramsValues[i] = null;
                            } else {
                                paramsValues[i] = params[i].substring(params[i].indexOf("=") + 1,
                                        params[i].length());
                            }
                        }
                        controller.setResponOS(client.getOutputStream());
                        method.invoke(controller, paramsValues);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
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
                controller.setResponOS(client.getOutputStream());
                controller.render404();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void renderJS(InputStream is) {
        try {
            controller.setResponOS(client.getOutputStream());
            controller.renderOs2Js(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.render404();
    }

    private void renderCSS(InputStream is) {
        try {
            controller.setResponOS(client.getOutputStream());
            controller.renderOs2Css(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.render404();
    }
}
