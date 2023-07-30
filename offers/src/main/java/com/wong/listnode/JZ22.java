package com.wong.listnode;

import com.wong.ListNode;

/**
 * JZ22 链表中倒数最后k个结点
 *输入一个长度为 n 的链表，设链表中的元素的值为 ai ，返回该链表中倒数第k个节点。
 * 如果该链表长度小于k，请返回一个长度为 0 的链表。
 *
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/7/31 5:24
 */
public class JZ22 {

    public int length(ListNode p) {
        int count = 0;
        while (p != null) {
            p = p.next;
            count++;
        }
        return count;
    }

    /**
     * 数学题解法 这是我在23-7-31直接想到的，倒数k个，总长n，则遍历n-k个返回即可.
     * 主要点:
     * 1.得到总长n 此遍历一次.
     * 2.判断n与k的关系.只有n >= k的输入才合理
     * 3. 从头遍历n-k次
     * 时间复杂度O(n) 空间复杂度O(1)
     * @param pHead
     * @param k
     * @return
     */
    public ListNode FindKthToTail (ListNode pHead, int k) {
        // write code here
        int length = length(pHead);
        if (length < k) {
            return null;
        }
        ListNode cur = pHead;
        for (int i = 0; i < length - k; i++) {
            cur = cur.next;
        }
        return cur;

    }

    /**
     * 双指针法  没想到怎么做
     * @param pHead
     * @param k
     * @return
     */
    public ListNode FindKthToTail2 (ListNode pHead, int k) {
        return null;
    }


    /**
     * 栈实现 太简单了 不想写
     * @param pHead
     * @param k
     * @return
     */
    public ListNode FindKthToTail3 (ListNode pHead, int k) {
        return null;
    }
}
