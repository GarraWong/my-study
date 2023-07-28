package com.wong;

import cn.hutool.core.util.RandomUtil;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/7/29 1:32
 */
public class BuildUtil {

    private static final int MAX_VALUE = 10000;

    public static ListNode buildNode(int size) {
        return buildNode(size, true);
    }

    public static ListNode buildNode(int size, int maxValue) {
        return buildNode(size, maxValue, true);
    }

    public static ListNode buildNode(int size, boolean sorted) {
        return buildNode(size, MAX_VALUE, sorted);
    }

    /**
     *
     * @param size 生成个数
     * @param maxValue 最大值
     * @param sorted 是否从小到大的顺序
     * @return
     */
    public static ListNode buildNode(int size, int maxValue, boolean sorted) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        for (int i = 0; i < size; i++) {
            int val;
            if (sorted) {   //有序的
                val = RandomUtil.randomInt(cur.val + 1 == maxValue ? cur.val : cur.val + 1, maxValue);
            } else {    //无序的
                val = RandomUtil.randomInt(maxValue);
            }
            cur.next = new ListNode(val);
            cur = cur.next;
        }
        System.out.println("自动构建的链表:" + head.next);
        return head.next;
    }

}
