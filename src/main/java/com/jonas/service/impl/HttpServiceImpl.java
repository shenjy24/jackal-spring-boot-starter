package com.jonas.service.impl;

import com.alibaba.fastjson.JSON;
import com.jonas.service.HttpService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * 【 OkHttp3服务工具类 】
 *
 * @author jonas 2018/07/04
 */
@Service
public class HttpServiceImpl<T> implements HttpService<T> {

    private OkHttpClient httpClient = new OkHttpClient();

    @Override
    public T post(String url, Map<String, Object> params, Class<T> clazz) {

        FormBody.Builder builder = new FormBody.Builder();
        if (null != params) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                builder.addEncoded(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .url(url).post(body).build();

        Call call = httpClient.newCall(request);
        try {
            Response response = call.execute();
            return JSON.parseObject(response.body().string(), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
