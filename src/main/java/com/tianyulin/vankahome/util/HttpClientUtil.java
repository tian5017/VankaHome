package com.tianyulin.vankahome.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

/**
 * Created by tianyulin on 2017/5/5.
 */
public class HttpClientUtil {

    public static String get(String url) {
        // 实例化连接对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 实例化一个get对象
        HttpGet httpGet = new HttpGet(url);
        // 发出请求
        HttpResponse httpResponse = null;
        String returnRes = "";
        try {
            httpResponse = httpClient.execute(httpGet);
            // 判断响应码:404,200,500
            System.out.println("响应码:" + httpResponse.getStatusLine().getStatusCode());
            returnRes = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return returnRes;
    }

    public static String post(String url, String param) {
        // 实例化连接对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 实例化一个post对象
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(param, "UTF-8");
        // 添加post的请求
        httpPost.setEntity(stringEntity);
        // 发出请求
        HttpResponse httpResponse = null;
        String returnRes = "";
        try {
            httpResponse = httpClient.execute(httpPost);
            // 判断响应码:404,200,500
            System.out.println("响应码:" + httpResponse.getStatusLine().getStatusCode());
            returnRes = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnRes;
    }
}
