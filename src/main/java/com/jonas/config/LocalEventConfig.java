package com.jonas.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 【 本地队列配置 】
 *
 * @author shenjy 2018/11/21
 */
@EnableAsync
@Configuration
public class LocalEventConfig {


    /**
     * 线程创建流程:
     * 1.当线程数量小于corePoolSize时，创建线程，不管线程是不是闲置的
     * 2.当线程数量大于等于corePoolSize时，把任务放到queueCapacity队列
     * 3.当queueCapacity满了，就创建新的线程来执行
     * 4.当线程数量大于等于maxPoolSize时，根据RejectedExecutionHandler设置的策略来处理新加入的任务
     *
     */
    @Bean
    @ConditionalOnMissingBean
    public Executor simpleExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("My_Executor_");
        executor.setKeepAliveSeconds(100);

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
