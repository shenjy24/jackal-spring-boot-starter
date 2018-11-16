package com.jonas;

import com.google.common.collect.Maps;
import com.jonas.dto.WXSession;
import com.jonas.service.HttpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * Unit test for simple WebApplication.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {

    private static Logger logger = LoggerFactory.getLogger(ApplicationTest.class);

    @Autowired
    private HttpService<WXSession> httpService;

    @Value("${wx.appid}")
    private String appId;

    @Value("${wx.secret}")
    private String secret;

    @Value("${wx.url}")
    private String wxUrl;

    @Test
    public void testLog() {
        logger.debug("debug");
        logger.warn("玉石");
    }

    @Test
    public void testHttp() {

        String code = "0337JnMo1VEwGq0FkOMo1u5oMo17JnM9";

        Map<String, Object> params = Maps.newHashMap();
        params.put("appid", appId);
        params.put("secret", secret);
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");

        WXSession session = httpService.post(wxUrl, params, WXSession.class);
        System.out.println(session);
    }
}
