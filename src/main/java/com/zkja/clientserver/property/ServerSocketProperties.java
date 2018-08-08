package com.zkja.clientserver.property;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

/**
 * @author  zzr
 */

@Component
@ConfigurationProperties(prefix = "serverSocket")
@PropertySource("classpath:socket.properties")
public class ServerSocketProperties {

    /**
     * 端口
     */
    private String port;
    private Integer corePoolSize;
    private Integer maxPoolSize;
    
    private String smcUrl;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

	public String getSmcUrl() {
		return smcUrl;
	}

	public void setSmcUrl(String smcUrl) {
		this.smcUrl = smcUrl;
	}

	
    
}
