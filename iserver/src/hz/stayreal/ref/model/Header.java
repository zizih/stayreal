package hz.stayreal.ref.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 11/6/13
 * Time: 2:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Header {

    private String method;// 请求方法
    private String protocol;// 协议版本
    private String requestURL;
    private String requestURI;//请求的URI地址  在HTTP请求的第一行的请求方法后面
    private String host;//请求的主机信息
    private String Connection;//Http请求连接状态信息 对应HTTP请求中的Connection
    private String agent;// 代理，用来标识代理的浏览器信息 ,对应HTTP请求中的User-Agent:
    private String language;//对应Accept-Language
    private String encoding;//请求的编码方式  对应HTTP请求中的Accept-Encoding
    private String charset;//请求的字符编码  对应HTTP请求中的Accept-Charset
    private String accept;// 对应HTTP请求中的Accept;


    public Header(InputStream is) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null) {
                parse(line);
                if (line.equals("") || line == "") break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parse(String s) {
        if (s.startsWith("GET")) {
            String method = "Get";
            setMethod(method);

            int index = s.indexOf("HTTP");
            String uri = s.substring(3 + 1, index - 1);// 用index-1可以去掉连接中的空格
            setRequestURI(uri);

            String protocol = s.substring(index);
            setProtocol(protocol);
        } else if (s.startsWith("POST")) {
            String method = "POST";
            setMethod(method);

            int index = s.indexOf("HTTP");
            String uri = s.substring(3 + 1, index - 1);// 用index-1可以去掉连接中的空格
            setRequestURI(uri);

            String protocol = s.substring(index);
            setProtocol(protocol);

        } else if (s.startsWith("Accept:")) {
            String accept = s.substring("Accept:".length() + 1);
            setAccept(accept);

        } else if (s.startsWith("User-Agent:")) {
            String agent = s.substring("User-Agent:".length() + 1);
            setAgent(agent);

        } else if (s.startsWith("Host:")) {
            String host = s.substring("Host:".length() + 1);
            setHost(host);

        } else if (s.startsWith("Accept-Language:")) {
            String language = s.substring("Accept-Language:".length() + 1);
            setLanguage(language);

        } else if (s.startsWith("Accept-Charset:")) {
            String charset = s.substring("Accept-Charset:".length() + 1);
            setCharset(charset);
        } else if (s.startsWith("Accept-Encoding:")) {
            String encoding = s.substring("Accept-Encoding:".length() + 1);
            setEncoding(encoding);

        } else if (s.startsWith("Connection:")) {
            String connection = s.substring("Connection:".length() + 1);
            setConnection(connection);
        }

    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void setConnection(String connection) {
        Connection = connection;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public String getAccept() {
        return accept;
    }

    public String getAgent() {
        return agent;
    }

    public String getCharset() {
        return charset;
    }

    public String getConnection() {
        return Connection;
    }

    public String getEncoding() {
        return encoding;
    }

    public String getHost() {
        return host;
    }

    public String getLanguage() {
        return language;
    }

    public String getMethod() {
        return method;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getRequestURL() {
        return requestURL;
    }
}
