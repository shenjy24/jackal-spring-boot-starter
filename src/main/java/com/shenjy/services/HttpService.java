package com.shenjy.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2018/07/04
 */
public interface HttpService {

    void download(HttpServletRequest request, HttpServletResponse response, String fileUrl);
}
