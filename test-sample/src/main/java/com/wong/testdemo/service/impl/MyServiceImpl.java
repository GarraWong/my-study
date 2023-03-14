package com.wong.testdemo.service.impl;

import com.wong.testdemo.cache.GlobalCache;
import com.wong.testdemo.model.NettyDemo;
import com.wong.testdemo.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * 类注释 完善它
 *
 * @author : wangyumou
 * @date : 2021/9/23 16:30
 */
@Service
public class MyServiceImpl implements MyService {

    private static final Logger logger = LoggerFactory.getLogger(MyServiceImpl.class);

    @PostConstruct
    public void init() {
        logger.info("MyServiceImpl实例化开始");
        logger.info("MyServiceImpl实例化完成");
    }


    @Override
    public Integer result(String plate) {
        logger.info("入参车牌号:{}", plate);
        Map<String, Integer> map = GlobalCache.getVehicleStatus();
        map.put(plate, 0);
        new Thread(new NettyDemo(plate)).start();
        Integer data;
        logger.info("执行轮询前的map数量:{}", map.size());
        while (true) {
            data = GlobalCache.getVehicleStatus().get(plate);
            if (data != 0) {
                logger.info("执行完成了");
                logger.info("最后netty的结果是这个:{}", data);
                map.remove(plate);
                break;
            } else {
            }
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        logger.info("完成任务的map数量:{}", map.size());
        return data;
    }
}
