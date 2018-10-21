package com.merit.utils;


import com.merit.constant.Constant;
import com.merit.model.PublicData;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * <p>方法 HttpUtils : <p>
 * <p>说明:Http 操作的客户端，作为前台页面同后台的对接入口</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/21 21:09
 * </pre>
 */
public class HttpUtils {

    public static String doPost(PublicData data, String charset, boolean pretty) throws IOException  {
        String url = Constant.URL_HOST + data.getMethod() + Constant.INTERCEPT_SUFFIX;
        return doPost(url, JsonUtil.jsonToMap(JsonUtil.toJson(data)), charset, pretty);
    }

    /**
     * <p>方法:doPost Post 方式访问HTTP URL </p>
     * <ul>
     * <li> @param url 访问的路径地址</li>
     * <li> @param params 访问的参数</li>
     * <li> @param charset 编码方式</li>
     * <li> @param pretty 是否读一行换行</li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/18 22:29  </li>
     * </ul>
     */
    public static String doPost(String url, Map<String, Object> params,
                                String charset, boolean pretty) throws IOException {
        System.out.println(url);
        StringBuffer response = new StringBuffer();
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);

        post.setRequestHeader(new Header("Content-type",
                "application/x-www-form-urlencoded;charset=UTF-8"));
        post.setRequestHeader(new Header(
                "User-Agent",
                "Mozilla/5.0 /Windows; U; Windows NT 4.1; de; rv:1.9.1.5) Gecko/20091102 Firefox/3.0"));

        // 设置Http Post数据
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                post.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        try {
            client.executeMethod(post);
        } catch (IOException e) {
           throw e;
        }

        makeResponse(response, post, charset, pretty);
        return response.toString();
    }


    public static String doGet(String url, Map<String, Object> params,
                               String charset, boolean pretty) {
//        System.out.println(url);
        StringBuffer response = new StringBuffer();
        HttpClient client = new HttpClient();
        StringBuilder sb = new StringBuilder();
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append("&");
            }
        }
        if (sb.length() > 0)
            url = url + "?" + sb.toString().substring(0, sb.toString().length() - 1);
        GetMethod get = new GetMethod(url);
        get.setRequestHeader(new Header("Content-type",
                "application/x-www-form-urlencoded;charset=gbk"));
        get.setRequestHeader(new Header(
                "User-Agent",
                "Mozilla/5.0 /Windows; U; Windows NT 4.1; de; rv:1.9.1.5) Gecko/20091102 Firefox/3.0"));

        try {
            client.executeMethod(get);
        } catch (IOException e) {
            e.printStackTrace();
        }

        makeResponse(response, get, charset, pretty);

        return response.toString();
    }

    private static void makeResponse(StringBuffer response, HttpMethodBase method, String charset, boolean pretty) {

        if (method.getStatusCode() == HttpStatus.SC_OK) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(
                        new InputStreamReader(method.getResponseBodyAsStream(),
                                charset));

                String line;
                while ((line = reader.readLine()) != null) {
                    if (pretty)
                        response.append(line).append(System.getProperty("line.separator"));
                    else
                        response.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                method.releaseConnection();
            }

        }
    }

}
