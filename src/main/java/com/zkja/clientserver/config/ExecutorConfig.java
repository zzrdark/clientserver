package com.zkja.clientserver.config;

import com.zkja.clientserver.property.ServerSocketProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @author zzr
 */
@Component
@EnableConfigurationProperties(ServerSocketProperties.class)
public class ExecutorConfig {

    @Autowired
    private  ServerSocketProperties serverSocketProperties;


    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor poolTaskExecutor  = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setCorePoolSize(serverSocketProperties.getCorePoolSize());
        poolTaskExecutor.setMaxPoolSize(serverSocketProperties.getMaxPoolSize());
        //线程池所使用的缓冲队列
        poolTaskExecutor.setQueueCapacity(200);
        //线程池维护线程所允许的空闲时间
        poolTaskExecutor.setKeepAliveSeconds(30000);
        poolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return poolTaskExecutor;

    }

}
