package com.wong;

import static com.wong.BuildUtil.*;
/**
 * 输入两个递增的链表，单个链表的长度为n，合并这两个链表并使新链表中的节点仍然是递增排序的。
 * 数据范围 0≤n≤1000  −1000≤节点值≤1000
 * 要求:空间复杂度O(1)，时间复杂度O(n)
 *
 * 从这里可以学到：
 * 虚拟头结点 ListNode result = new ListNode(-1);
 * 遍历的时候加上当前遍历节点：        ListNode cur = result;
 * 双指针： pHead2 = pHead2.next; pHead1 = pHead1.next;
 * 哨兵节点：也可以叫做dummy
 * A sentinel is a dummy object that allows us to simplify boundary conditions.
 * 哨兵是用来简化边界问题的虚设对象
 *
 * 还有用递归实现的，后面想想怎么实现
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/7/29 0:14
 */
public class JZ25 {

    public static ListNode Merge(ListNode pHead1, ListNode pHead2) {
        // write code here
        ListNode result = new ListNode(-1);
        ListNode cur = result;
        while (pHead1 != null && pHead2 != null) {
            if (pHead1.val >= pHead2.val) {
                cur.next = pHead2;
                pHead2 = pHead2.next;
            } else {
                cur.next = pHead1;
                pHead1 = pHead1.next;
            }
            cur = cur.next;
        }
        if (pHead1 != null) {
            cur.next = pHead1;
        } else {
            cur.next = pHead2;
        }
        return result.next;
    }

    /**
     * 测试用例
     * @param args
     */
    public static void main(String[] args) {
        ListNode merge = Merge(buildNode(3), buildNode(6));
        System.out.println(merge);

    }
}
