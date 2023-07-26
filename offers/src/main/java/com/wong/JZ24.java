package com.wong;

/**
 * 反转链表
 * AC
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/6/25 21:50
 */


public class JZ24 {
    public ListNode ReverseList (ListNode head) {
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


    public class ListNode {
        int val;
        ListNode next = null;
        public ListNode(int val) {
            this.val = val;
        }
    }
}