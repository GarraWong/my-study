package com.wong;

import cn.hutool.core.util.RandomUtil;

import java.util.List;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/7/29 1:32
 */
public class Util {

    public static ListNode buildSortedNode(int size) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        for (int i = 0; i < size; i++) {
            cur.next = new ListNode(RandomUtil.randomInt(cur.val + 1, 10000));
            cur = cur.next;
        }
        System.out.println("自动构建的链表:" + head.next);
        return head.next;
    }

}
