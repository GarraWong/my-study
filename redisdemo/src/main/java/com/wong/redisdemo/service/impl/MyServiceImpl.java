package com.wong.redisdemo.service.impl;

import com.wong.redisdemo.cache.GlobalCache;
import com.wong.redisdemo.model.NettyDemo;
import com.wong.redisdemo.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 类注释 完善它
 *
 * @author : wangyumou
 * @date : 2021/9/23 16:30
 */
public class MyServiceImpl implements MyService {

    private static final Logger logger = LoggerFactory.getLogger(MyServiceImpl.class);

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
