package com.wong.listnode;

import com.wong.BuildUtil;
import com.wong.ListNode;

import java.util.ArrayList;

/**
 * 从尾到头打印链表
 * 输入一个链表的头节点，按链表从尾到头的顺序返回每个节点的值（用数组返回）。
 * 如输入{1,2,3}的链表如下图: 返回一个数组为[3,2,1]
 * 0 <= 链表长度 <= 10000
 * AC
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/6/25 21:13
 */
public class JZ6 {

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        while (listNode != null) {
            list.add(0, listNode.val);
            listNode = listNode.next;
        }
        return list;

    }

    public static void main(String[] args) {
        System.out.println(printListFromTailToHead(BuildUtil.buildNode(5)));
    }

}