package com.wong.listnode;

import com.wong.ListNode;

/**
 * 删除链表中重复的结点
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表 1->2->3->3->4->4->5  处理后为 1->2->5
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/8/18 16:18
 */
public class JZ76 {

    public ListNode deleteDuplication(ListNode pHead) {
        ListNode head = new ListNode(1);
        head.next = pHead;
        ListNode pre = null;
        while (pHead.next != null) { //遍历一次
            ListNode cur = pHead;
            ListNode next = pHead.next;
            if (cur.val == next.val) {
                pHead = next;
            } else {

            }

        }


        return head;

    }
}
