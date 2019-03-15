package com.lynu.yzshopping.util;

import javax.net.ssl.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: data-interface
 * @description: 网络请求工具类
 * @author: houyu
 * @create: 2018-12-06 22:57
 */
public class HttpUtil {

    /**
     * 快速获取进行网络请求获取网页源码
     */
    public String getHtml(String url) {
        return doGet(url, null, null, null);
    }

    /**
     * GET请求
     */
    public String doGet(String url, Map<String, Object> header, Map<String, Object> proxy, String charset) {
        try {
            return toString(doURLConnectionGet(url, header, proxy), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * POST请求
     *      postType: PostType.FORM | PostType.JSON
     *      body:    如果是json数据,"JSON":"{key:value}"
     */
    public String doPost(String url, Map<String, Object> header, int postType, Map<String, Object> body, String bodyCharset, Map<String, Object> proxy, String htmlCharset) {
        try {
            return toString(doURLConnectionPost(url, header, postType, body, bodyCharset, proxy), htmlCharset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    public URLConnection getURLConnection(URL url, String method, Map<String, Object> proxy) {
        URLConnection connection = null;
        try {
            if (proxy == null) {                                //不设置代理
                connection = url.openConnection();
            } else if (isNotEmpty(proxy.get("username"))) {     // 设置代理服务器
                //System.getProperties().put("proxySet", "true");
                //// System.getProperties().put("proxyHost", proxy.get("host"));
                //// System.getProperties().put("proxyPort", proxy.get("port"));
                java.net.Proxy javaNetProxy = new java.net.Proxy(java.net.Proxy.Type.HTTP, new java.net.InetSocketAddress(proxy.get("host").toString(), Integer.parseInt(proxy.get("port").toString())));
                connection = url.openConnection(javaNetProxy);
                String authString = proxy.get("username") + ":" + proxy.get("password");
                String auth = "Basic " + Base64.getEncoder().encodeToString(authString.getBytes());// "UTF-8"
                connection.setRequestProperty("Proxy-Authorization", auth);
                // connection = url.openConnection(javaNetProxy);
            } else if (isNotEmpty(proxy.get("host"))) {         //设置代理主机和端口
                java.net.Proxy javaNetProxy = new java.net.Proxy(java.net.Proxy.Type.HTTP, new java.net.InetSocketAddress(proxy.get("host").toString(), Integer.parseInt(proxy.get("port").toString())));
                connection = url.openConnection(javaNetProxy);
            } else {                                            //不设置代理
                connection = url.openConnection();
            }

            String protocol = url.getProtocol();
            if ("https".equalsIgnoreCase(protocol)) {
                HttpsURLConnection https = (HttpsURLConnection) connection;
                trustAllHosts(https);
                https.getHostnameVerifier();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                https.setRequestMethod(method);
                return https;
            }
            ((HttpURLConnection) connection).setRequestMethod(method);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public URLConnection doURLConnectionGet(String urlString, Map<String, Object> header, Map<String, Object> proxy) {
        URLConnection connection = null;
        try {
            URL url = new URL(urlString);
            connection = getURLConnection(url, "GET", proxy);                      // 得到connection对象
            setURLConnectionTimeout(connection);                                            // 设置连接时间
            setURLConnectionRequestHeader(header, connection, url);                         // 设置请求头参数
            connection.connect();                                                           // 连接
            // connection.setInstanceFollowRedirects(false);                                // 设置重定向地址不跳转到本地
            // Map<String, List<String>> headerFields = connection.getHeaderFields();       // 获取响应头参数
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeURLConnection(connection);
        }
        return connection;
    }

    public URLConnection doURLConnectionPost(String urlString, Map<String, Object> header, int postType, Map<String, Object> body, String bodyCharset, Map<String, Object> proxy) {

        if (header == null){
            header = new HashMap<>();
        }

        String requestBodyString = "";
        if (PostType.FORM == postType) {                                        // 设置参数类型是FORM_DATA(表单)格式
            header.put("Content-Type", "application/x-www-form-urlencoded");
            requestBodyString = postFromDataHandle(body);
        } else if (PostType.JSON == postType) {                                 // 设置参数类型是JSON
            header.put("Content-Type", "application/json");
            requestBodyString = body.get("JSON").toString();
        }

        URLConnection connection = null;
        OutputStream outputStream;
        try {
            URL url = new URL(urlString);
            connection = getURLConnection(url, "POST", proxy);                      // 得到connection对象。
            setURLConnectionPostParam(connection);                                           // 设置POST请求的一些特殊参数
            setURLConnectionTimeout(connection);                                             // 设置超时
            setURLConnectionRequestHeader(header, connection, url);                          // 获取请求头
            outputStream = connection.getOutputStream();
            outputStream.write(requestBodyString.getBytes(bodyCharset == null ? "UTF-8" : bodyCharset));
            outputStream.flush();
            outputStream.close();
            connection.connect();                                                            // 连接
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeURLConnection(connection);
        }
        return connection;
    }

    // 1
    private void setURLConnectionTimeout(URLConnection connection) {
        connection.setConnectTimeout(1000 * 30);
        connection.setReadTimeout(1000 * 30);
    }

    // 2
    private Map<String, Object> getDefaultHeaderMap() {
        Map<String, Object> defaultHeader = new HashMap<>();
        defaultHeader.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        defaultHeader.put("Accept-Language", "zh-CN,zh;q=0.9");
        defaultHeader.put("Cache-Control", "no-cache");
        defaultHeader.put("Connection", "keep-alive");
        // defaultHeaders.put("Host", "www.baidu.com");
        defaultHeader.put("Pragma", "no-cache");
        defaultHeader.put("Referer", "https://www.baidu.com/");
        defaultHeader.put("Upgrade-Insecure-Requests", "1");
        defaultHeader.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
        return defaultHeader;
    }

    // 3
    private void setURLConnectionRequestHeader(Map<String, Object> requestHeader, URLConnection connection, URL url) {
        Map<String, Object> defaultHeaderMap = getDefaultHeaderMap();       // 获取一个默认的请求头map
        if (requestHeader != null) {
            defaultHeaderMap.putAll(requestHeader);                         // 拼接组合请求头
        }
        if ("https://www.baidu.com/".equals(defaultHeaderMap.get("Referer"))) {
            // requestHeader.put("Host", url.getHost());
            defaultHeaderMap.put("Referer", String.format("%s://%s", url.getProtocol(), url.getHost()));
        }
        defaultHeaderMap.remove("Content-Length");                     // 移除个别不需要的请求头参数
        defaultHeaderMap.remove("Accept-Encoding");

        for (Map.Entry<String, Object> entry : defaultHeaderMap.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue().toString());
        }
    }

    // 4
    private void setURLConnectionPostParam(URLConnection connection) throws ProtocolException {
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
    }

    // 5 user=admin&pwd=admin
    private String postFromDataHandle(Map<String, Object> requestBody) {
        String fromDataString = "";
        if (requestBody != null) {
            StringBuilder stringBuilder = new StringBuilder();
            Set<Map.Entry<String, Object>> entrySet = requestBody.entrySet();
            for (Map.Entry<String, Object> entry : entrySet) {
                stringBuilder.append(String.format("%s=%s&", entry.getKey(), entry.getValue()));
            }
            fromDataString = stringBuilder.toString();
            fromDataString = fromDataString.substring(0, fromDataString.length() - 1);
        }
        return fromDataString;
    }

    /**
     * HttpURLConnection设置覆盖java默认的证书验证
     */
    private final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) {}

        public void checkServerTrusted(X509Certificate[] chain, String authType) {}
    }};

    /**
     * HttpURLConnection设置不验证主机
     */
    private final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * HttpURLConnection设置信任所有证书
     *
     * @param connection HttpsURLConnection
     * @return
     */
    private SSLSocketFactory trustAllHosts(HttpsURLConnection connection) {
        SSLSocketFactory oldFactory = connection.getSSLSocketFactory();
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            SSLSocketFactory newFactory = sc.getSocketFactory();
            connection.setSSLSocketFactory(newFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oldFactory;
    }

    /**
     * 关闭URLConnection
     */
    private void closeURLConnection(URLConnection connection) {
        if (connection instanceof HttpURLConnection) {
            ((HttpURLConnection) connection).disconnect();
        } else if (connection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) connection).disconnect();
        }
    }

    /**
     * 自动识别网页编码
     */
    public String detectCharset(String contentType) throws IOException {
        String charset = "";
        Pattern patternForCharset = Pattern.compile("charset\\s*=\\s*['\"]*([^\\s;'\"]*)", Pattern.CASE_INSENSITIVE);        // encoding in http header Content-Type
        Matcher matcher = patternForCharset.matcher(contentType);
        if (matcher.find()) {
            charset = matcher.group(1);
            if (!Charset.isSupported(charset)) {
                charset = "";
            }
        }
        return charset;
    }

    public String toString(URLConnection urlConnection, String charset) throws IOException {
        InputStream inputStream = urlConnection.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte[] bytes = new byte[1024 * 3];
        int i;
        while ((i = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, i);
            outputStream.flush();
        }
        byte[] dataArray = outputStream.toByteArray();
        inputStream.close();
        outputStream.close();

        if (isNotEmpty(charset)) {                                  // 不为空,说明有设置编码方式了,使用自定义的编码方式
            return new String(dataArray, charset);
        } else {
            charset = detectCharset(urlConnection.getContentType());
            return new String(dataArray, isEmpty(charset) ? "UTF-8" : charset);
        }
    }

    public boolean isEmpty(Object o){
        if (null == o) return true;
        return o instanceof String ? ((String) o).length() == 0 : false;
    }

    public boolean isNotEmpty(Object o){
        return !isEmpty(o);
    }

    // POST参数类型
    public static abstract class PostType {
        // Form 表单数据
        public static final int FORM = 1;
        // JSON 数据
        public static final int JSON = 2;
    }

    /* ---------------------------------------单例模式---------------------------------------*/
    private HttpUtil() {}

    private static class SingletonHolder {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }

    public static HttpUtil get() {
        return SingletonHolder.INSTANCE;
    }
    /* ---------------------------------------单例模式---------------------------------------*/

}