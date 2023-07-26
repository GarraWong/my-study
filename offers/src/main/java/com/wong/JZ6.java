package com.wong;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 从尾到头打印链表
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/6/25 21:13
 */
public class JZ6 {

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while (listNode != null && listNode.next != null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        if (listNode != null) {
            stack.push(listNode.val);
        }
        while (stack != null && !stack.empty()) {
            list.add(stack.pop());
        }

        return list;
    }

    public static void main(String[] args) {
        printListFromTailToHead(null);
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


}