package com.jonas.config;

import com.alibaba.fastjson.JSON;
import com.jonas.common.BizException;
import com.jonas.common.JsonResult;
import com.jonas.common.SystemCode;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * 【 feign自定义response解码器 】
 *
 * @author shenjy 2019/04/30
 */
@Configuration
@ConditionalOnMissingClass
public class FeignDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        InputStream inputStream = response.body().asInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while (-1 != (len = inputStream.read(buffer))) {
            outputStream.write(buffer, 0, len);
        }
        String content = outputStream.toString("UTF-8");

        JsonResult result = JSON.parseObject(content, JsonResult.class);

        if (SystemCode.SUCCESS.getCode().equals(result.getCode())) {
            //正常返回
            return null != result.getData() ? result.getData() : null;
        } else {
            //异常返回
            throw new BizException(result.getCode(), result.getMessage());
        }
    }
}
