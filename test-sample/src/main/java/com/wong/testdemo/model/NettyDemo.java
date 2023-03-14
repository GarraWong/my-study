package com.wong.testdemo.model;

import com.wong.testdemo.cache.GlobalCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * 类注释 完善它
 *
 * @author : wangyumou
 * @date : 2021/9/23 16:29
 */
public class NettyDemo implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(NettyDemo.class);

    private String value;

    public NettyDemo(String value) {
        this.value = value;
    }

    public void handler() {
        logger.info("天线开启,扫描车辆");
        logger.info("过车,obu扫描完成");
        logger.info("模拟与后台交互获取真实金额");
        try {
            Thread.sleep(3000); //获取后台金额 2s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("netty业务全部完成");
        Random r = new Random();
        // 通过对象方法获取随机数
        int data2 = r.nextInt(3);
        GlobalCache.getVehicleStatus().put(value, data2 + 1);
    }

    @Override
    public void run() {
        handler();
    }
}
