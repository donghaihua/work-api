package com.tenmaker.express;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

public class HttpsUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpsUtils.class);

    private HttpsUtils() {
    }

    public static <T> T get(String requestUrl, TypeReference<?> typeReference) {
        String responseStr = makeRequest(requestUrl, "GET", null);
        if (responseStr == null)
            return null;
        return readValue(responseStr, typeReference);
    }

    public static String get(String requestUrl) {
        return makeRequest(requestUrl, "GET", null);
    }

    public static String post(String requestUrl) {
        return post(requestUrl, "");
    }

    public static String post(String url, String body) {
        return executePost(url, body);
    }

    private static String executePost(String url, String body) {
        logger.debug("executePost url={}", url);
        logger.debug("executePost body={}", body);
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(body, "UTF-8");
            httpPost.setHeader("Content-Type", "application/json"); //顺丰的要求；这个宜作为参数传入
            httpPost.setEntity(entity);
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httpPost);
            return new Utf8ResponseHandler().handleResponse(response);
        } catch (Exception e) {
            logger.error("Error in executePost:", e);
            return null;
        }
    }

    public static String makeRequest(String requestUrl, String requestMethod, String outputStr) {
        String result = null;
        StringBuilder builder = new StringBuilder();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                builder.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            result = builder.toString();

            logger.info("[makeRequest] url: {}, method: {}, params: {}, response string: {} ", requestUrl, requestMethod, outputStr, result);
            return result;
        }
        catch (ConnectException e) {
            logger.error("Connect error in makeRequest:", e);
        }
        catch (IOException e) {
            logger.error("IO error in makeRequest:", e);
        }
        catch (Exception e) {
            logger.error("Error in makeRequest:", e);
        }
        return null;
    }

    public static <T> T readValue(String responseString, TypeReference<?> typeReference) {
        try {
            return new ObjectMapper().readValue(responseString, typeReference);
        }
        catch (Exception e) {
            logger.error("转换对象异常:", e);
            return null;
        }
    }

    /**
     * utf-8编码
     */
    static class Utf8ResponseHandler implements ResponseHandler<String> {
        public String handleResponse(final HttpResponse response) throws IOException {
            final StatusLine statusLine = response.getStatusLine();
            final HttpEntity entity = response.getEntity();
            if (statusLine.getStatusCode() >= 300) {
                EntityUtils.consume(entity);
                throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
            }
            return entity == null ? null : EntityUtils.toString(entity, "UTF-8");
        }

    }
}
