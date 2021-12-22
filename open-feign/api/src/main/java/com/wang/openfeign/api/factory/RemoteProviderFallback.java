package com.wang.openfeign.api.factory;

import com.wang.openfeign.api.RemoteProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 这是类注释 完善它
 *
 * @author : wangyumou's Dell inspiration
 * @date : 2021/12/20 16:27
 */
@Component
public class RemoteProviderFallback implements FallbackFactory<RemoteProviderService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteProviderFallback.class);


    @Override
    public RemoteProviderService create(Throwable cause) {
        log.error("服务调用失败:{}", cause.getMessage());
        return new RemoteProviderService() {
            @Override
            public String getUser(Integer id) {
                return "大哥 查询不到啊啊啊啊";
            }

            @Override
            public int hashCode() {
                return super.hashCode();
            }
        };
    }
}
