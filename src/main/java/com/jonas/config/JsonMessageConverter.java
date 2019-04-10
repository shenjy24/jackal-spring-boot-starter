package com.jonas.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

/**
 * 将默认HttpMessageConverter替换为FastJsonHttpMessageConverter
 *
 * @author yangjh  05/09/2018.
 */
@Configuration
public class JsonMessageConverter {

    @Bean
    @ConditionalOnMissingBean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {

        FastJsonHttpMessageConverter jsonConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        jsonConverter.setFastJsonConfig(fastJsonConfig);

        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        jsonConverter.setSupportedMediaTypes(mediaTypes);

        return jsonConverter;
    }
}
