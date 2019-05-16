package com.jonas.config;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.jonas.common.BizException;
import com.jonas.common.JsonResult;
import com.jonas.common.SystemCode;
import com.jonas.util.logging.JacLogger;
import feign.codec.DecodeException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 【 全局异常捕获 】
 *
 * @author shenjy 2019/04/30
 */
@ControllerAdvice
@ConditionalOnMissingClass
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public JsonResult handleException(Exception ex) {
        if (ex instanceof BizException) {
            Iterable iterable = Splitter.on(":").trimResults().omitEmptyStrings().split(ex.getMessage());
            List<String> items = Lists.newArrayList(iterable);
            return new JsonResult(items.get(1), items.get(2), null);
        } else if (ex instanceof DecodeException) {
            if (ex.getMessage().startsWith("BizException")) {
                Iterable iterable = Splitter.on(":").trimResults().omitEmptyStrings().split(ex.getMessage());
                List<String> items = Lists.newArrayList(iterable);
                return new JsonResult(items.get(1), items.get(2), null);
            }
        }

        JacLogger.error(ex, "handle exception");
        return new JsonResult(SystemCode.SERVER_ERROR);
    }
}
