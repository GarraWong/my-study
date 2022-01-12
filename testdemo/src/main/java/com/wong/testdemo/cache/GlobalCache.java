package com.wong.testdemo.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类注释 完善它
 *
 * @author : wangyumou
 * @date : 2021/9/23 16:28
 */
public class GlobalCache {

    private static final Map<String, Integer> VEHICLE_STATUS = new ConcurrentHashMap<>();

    public static Map<String, Integer> getVehicleStatus() {
        return VEHICLE_STATUS;
    }
}
