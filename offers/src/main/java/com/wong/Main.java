package com.wong;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ListNode ans;
        ListNode head;
        ans = new ListNode(1);
        head = ans;
        head.next = new ListNode(3);
        head = new ListNode(2);
        head.next = new ListNode(4);
        System.out.println(ans);
    }
}