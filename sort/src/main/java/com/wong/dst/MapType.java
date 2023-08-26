package com.wong.dst;

import java.util.List;

/**
 * 地图类型class
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/8/27 0:35
 */
public class MapType {

    /**
     * 枚举地图
     */
    private Type type;
    /**
     * 钥匙类型.
     */
    private List<Key> keyList;
    /**
     * 锁类型.
     */
    private List<Lock> lockList;

    private class Lock{

    }


    private class Key{

    }

    /**
     * 地形枚举
     */
    private enum Type{
        /**
         * 小猪村
         */
        Befriend_the_pigs(1),
        /**
         * 蜘蛛矿区
         */
        Kill_the_spiders(1),
        /**
         * 杀人蜂走廊
         */
         Killer_bees(1),
        /**
         * 额外的陨石区
         */
         Make_a_Beehat(1),
        /**
         * 海象平原
         */
         The_hunters(1),
        /**
         * 池塘区
         */
         Magic_meadow(1),
        /**
         * 蜜蜂、青蛙和鼹鼠
         */
         Frogs_and_bugs(1),
        ;
        /**
         * 标志位，表示是必刷地形还是随机地形 0必刷地形 1随机地形
         */
        int typeFlag;

        Type(int typeFlag) {
            this.typeFlag = typeFlag;
        }

        public int getTypeFlag() {
            return typeFlag;
        }

        public void setTypeFlag(int typeFlag) {
            this.typeFlag = typeFlag;
        }
    }



}
