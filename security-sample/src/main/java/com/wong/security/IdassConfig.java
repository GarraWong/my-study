package com.wong.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "idass")
public class IdassConfig implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(IdassConfig.class);
    private Boolean dev;    //是否开发模式
    private String idassServer; //idass服务地址


    @Override
    public void afterPropertiesSet() throws Exception {
        if (dev == null) {
            dev = Boolean.TRUE;
        }
        logger.info("IdassConfig dev属性为 {}", dev);
    }

    @Override
    public String toString() {
        return "IdassConfig{" +
                "dev=" + dev +
                ", idassServer='" + idassServer + '\'' +
                '}';
    }

    public Boolean getDev() {
        return dev;
    }

    public void setDev(Boolean dev) {
        this.dev = dev;
    }

    public String getIdassServer() {
        return idassServer;
    }

    public void setIdassServer(String idassServer) {
        this.idassServer = idassServer;
    }
}
