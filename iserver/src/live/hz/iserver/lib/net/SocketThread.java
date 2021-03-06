package live.hz.iserver.lib.net;

import app.controller.Application;
import live.hz.iserver.lib.app.BaseController;
import live.hz.iserver.lib.util.FileUtil;
import live.hz.iserver.lib.util.Log;

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
public class SocketThread implements Runnable {

    private BaseController controller;
    private Socket client;

    public SocketThread(Socket client) {
        this.client = client;
        this.controller = new Application();
    }

    @Override
    public void run() {
        try {
            if (client == null) return;
            Header header = new Header(client.getInputStream());
            //暂时不处理/favicon.ico请求
            if (header.getRequestURI() == null || header.getRequestURI().equals("/favicon.ico")) {
                closeSocket(client);
                return;
            }
            Log.i("request: " + header.getRequestURI());
            String uri = header.getRequestURI();

            //处理public资源请求,不需要暴露给controller处理
            if (uri.startsWith("/public/css")) {
                renderCSS(FileUtil.fileIS(uri));
                return;
            }
            if (uri.startsWith("/public/js")) {
                renderJS(FileUtil.fileIS(uri));
                return;
            }
            if (uri.startsWith("/public/images")) {
                renderImage(FileUtil.fileIS(uri));
                return;
            }
            if (uri.startsWith("/public/video")) {
                renderVideo(FileUtil.fileIS(uri));
                return;
            }
            if (uri.startsWith("/public/fonts")) {
                renderFont(FileUtil.fileIS(uri));
                return;
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
                    //匹配index路径 OR 其他页面请求
                    String template = uri.length() > 5 ? uri.substring(1, uri.length() - 5) : uri;
                    if ((uri.equals("/") && method.getName().equals("index"))
                            || (uri.endsWith(".html") && template.equals(method.getName()))) {
                        controller.setResponOS(client.getOutputStream());
                        method.invoke(controller);
                        return;
                    }

                    int qIndex = uri.indexOf("?");
                    String path = uri.substring(1, qIndex == -1 ? uri.length() : qIndex);
                    if (path.equals(method.getName())) {

                        //匹配get请求
                        if (header.getMethod().equals("Get")) {

                            //获取controller参数个数
                            int paramsLength = method.getParameterTypes().length;
                            String[] paramsValues = new String[paramsLength];

                            String[] params = null;
                            if (qIndex != -1) {
                                String paramstr = uri.substring(qIndex + 1, uri.length());
                                params = paramstr.split("&");
                            }

                            //长度以controller请求中的参数长度为准
                            for (int i = 0; i < paramsLength; i++) {
                                if (params == null || params.length < i + 1) {
                                    paramsValues[i] = null;
                                } else {
                                    paramsValues[i] = params[i].substring(params[i].indexOf("=") + 1,
                                            params[i].length());
                                }
                            }
                            controller.setResponOS(client.getOutputStream());
                            method.invoke(controller, paramsValues);
                            return;
                        }

                        //匹配Post请求
                        if (header.getMethod().equals("POST")) {
                        }
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
                controller.render500();
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
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.render404();
    }

    private void renderCSS(InputStream is) {
        try {
            controller.setResponOS(client.getOutputStream());
            controller.renderOs2Css(is);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.render404();
    }

    private void renderImage(InputStream is) {
        try {
            controller.setResponOS(client.getOutputStream());
            controller.renderOs2Image(is);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.render404();
    }

    private void renderVideo(InputStream is) {
        try {
            controller.setResponOS(client.getOutputStream());
            controller.renderOs2Video(is);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.render404();
    }

    private void renderFont(InputStream is) {
        try {
            controller.setResponOS(client.getOutputStream());
            controller.renderOs2Font(is);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.render404();
    }

}
