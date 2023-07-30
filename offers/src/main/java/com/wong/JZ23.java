package com.wong;

/**
 * JZ23 链表中环的入口结点
 *给一个长度为n链表，若其中包含环，请找出该链表的环的入口结点，否则，返回null。
 *
 * 例如，输入{1,2},{3,4,5}时，对应的环形链表如下图所示
 * 可以看到环的入口结点的结点值为3，所以返回结点值为3的结点。
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/7/30 23:35
 */
public class JZ23 {

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode head = pHead;
        ListNode second;
        if (head != null) {
            second = head;
            head = head.next;
        }



    }
}
