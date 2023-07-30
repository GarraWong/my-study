package com.wong.listnode;

import com.wong.BuildUtil;
import com.wong.ListNode;

/**
 * 反转链表
 * AC
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/6/25 21:50
 */


public class JZ24 {
    public static ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        System.out.println(reverseList(BuildUtil.buildNode(10)));
    }

}