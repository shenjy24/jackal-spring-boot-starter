package com.jonas.service;

import java.util.Map;

/**
 * 【 OkHttp3服务工具类 】
 *
 * @author jonas 2018/07/04
 */
public interface HttpService<T> {

    /**
     * 同步post请求
     * @param url
     * @param params
     * @return
     */
    T post(String url, Map<String, Object> params, Class<T> clazz);
}
