package com.wong.testformac.config;

import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.util.Utils;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.wong.testformac.config.properties.DruidProperties;
import com.wong.testformac.datasource.DynamicDataSource;
import com.wong.testformac.enums.DataSourceType;
import com.wong.testformac.util.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.servlet.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * druid 配置多数据源
 */
@Configuration
public class DruidConfig {


    private static final Logger logger = LoggerFactory.getLogger(DruidConfig.class);

    @Autowired
    private DruidProperties druidProperties;

    @Bean("mysqlDataSource")
    @ConfigurationProperties("spring.datasource.druid.mysql")
    public DataSource mysqlDataSource(Environment env) {
        String prefix = "spring.datasource.druid.mysql.";
        return getDataSource(env, prefix, DataSourceType.MYSQL.name());
    }

    @Bean("kingbaseDataSource")
    @ConfigurationProperties("spring.datasource.druid.kingbase")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.kingbase", name = "enabled", havingValue = "true")
    public DataSource kingbaseDataSource(Environment env) {
        String prefix = "spring.datasource.druid.kingbase.";
        return getDataSource(env, prefix, DataSourceType.KINGBASE.name());
    }

    protected DataSource getDataSource(Environment env, String prefix, String dataSourceName) {
        Properties prop = build(env, prefix);
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName(dataSourceName);
        ds.setXaProperties(prop);
        ds.setUniqueResourceName(dataSourceName);
        return ds;
    }

    protected Properties build(Environment env, String prefix) {
        Properties prop = new Properties();
        prop.put("url", env.getProperty(prefix + "url"));
        prop.put("username", env.getProperty(prefix + "username"));
        prop.put("password", env.getProperty(prefix + "password"));

        prop.put("initialSize", druidProperties.getInitialSize());
        prop.put("minIdle", druidProperties.getMinIdle());
        prop.put("maxActive", druidProperties.getMaxActive());
        prop.put("maxWait", druidProperties.getMaxWait());
        prop.put("timeBetweenEvictionRunsMillis", druidProperties.getTimeBetweenEvictionRunsMillis());
        prop.put("minEvictableIdleTimeMillis", druidProperties.getMinEvictableIdleTimeMillis());
        prop.put("maxEvictableIdleTimeMillis", druidProperties.getMaxEvictableIdleTimeMillis());
        prop.put("validationQuery", druidProperties.getValidationQuery());
        prop.put("testWhileIdle", druidProperties.isTestWhileIdle());
        prop.put("testOnBorrow", druidProperties.isTestOnBorrow());
        prop.put("testOnReturn", druidProperties.isTestOnReturn());
        return prop;
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(@Qualifier("mysqlDataSource") DataSource mysqlDatasource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MYSQL.name(), mysqlDatasource);
        setDataSource(targetDataSources, DataSourceType.KINGBASE.name(), "kingbaseDataSource");
        return new DynamicDataSource(mysqlDatasource, targetDataSources);
    }

    /**
     * 设置数据源
     *
     * @param targetDataSources 备选数据源集合
     * @param sourceName        数据源名称
     * @param beanName          bean名称
     */
    public void setDataSource(Map<Object, Object> targetDataSources, String sourceName, String beanName) {
        try {
            DataSource dataSource = SpringUtils.getBean(beanName);
            targetDataSources.put(sourceName, dataSource);
        } catch (Exception e) {
            logger.error("发生异常了", e);
        }
    }

    /**
     * 去除监控页面底部的广告
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    @ConditionalOnProperty(name = "spring.datasource.druid.statViewServlet.enabled", havingValue = "true")
    public FilterRegistrationBean removeDruidFilterRegistrationBean(DruidStatProperties properties) {
        // 获取web监控页面的参数
        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
        // 提取common.js的配置路径
        String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
        final String filePath = "support/http/resources/js/common.js";
        // 创建filter进行过滤
        Filter filter = new Filter() {
            @Override
            public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
            }

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws IOException, ServletException {
                chain.doFilter(request, response);
                // 重置缓冲区，响应头不会被重置
                response.resetBuffer();
                // 获取common.js
                String text = Utils.readFromResource(filePath);
                // 正则替换banner, 除去底部的广告信息
                text = text.replaceAll("<a.*?banner\"></a><br/>", "");
                text = text.replaceAll("powered.*?shrek.wang</a>", "");
                response.getWriter().write(text);
            }

            @Override
            public void destroy() {
            }
        };
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns(commonJsPattern);
        return registrationBean;
    }
}
