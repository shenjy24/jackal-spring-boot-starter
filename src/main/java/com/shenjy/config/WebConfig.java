package com.shenjy.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2018/09/07
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

        FastJsonConfig jsonConfig = new FastJsonConfig();
//        SerializerFeature writeMapNullValue = SerializerFeature.WriteMapNullValue;
//        SerializerFeature WriteNullStringAsEmpty = SerializerFeature.WriteNullStringAsEmpty;
//		SerializerFeature WriteNullNumberAsZero = SerializerFeature.WriteNullNumberAsZero;
//        SerializerFeature WriteNullListAsEmpty = SerializerFeature.WriteNullListAsEmpty;
//        jsonConfig.setSerializerFeatures(writeMapNullValue, WriteNullStringAsEmpty, WriteNullListAsEmpty);

        converter.setFastJsonConfig(jsonConfig);

        converters.add(converter);
    }

}
