package com.wang.openfeign.api;


import com.wang.openfeign.api.factory.RemoteProviderFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * 这是类注释 完善它
 *
 * @author : wangyumou's Dell inspiration
 * @date : 2021/12/20 16:27
 */
@FeignClient(contextId = "remoteProviderService",name = "provider",fallbackFactory = RemoteProviderFallback.class)
public interface RemoteProviderService {

    /**
     * 获取用户名称
     * @param id 用户id
     * @return 对应的用户名称
     */
    @GetMapping("/provider/user/{id}")
    String getUser(@PathVariable("id") Integer id);
}
