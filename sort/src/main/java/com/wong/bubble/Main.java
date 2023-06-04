package com.wong.bubble;

import java.util.Arrays;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/4/27 20:29
 */
public class Main {

    public static void main(String[] args) {
        int[] array = new int[]{3, 5, 1, 2, 10, 8};
        System.out.println(Arrays.toString(selectionSort(array)));

    }

    public static int[] bubbleSort(int[] src) {
        if (src == null) {
            throw new IllegalArgumentException("wrong array");
        }
        if (src.length == 0) {
            return src;
        }
        for (int i = 1; i < src.length; i++) {

            for (int j = 0; j < src.length - i; j++) {
                if (src[j] > src[j + 1]) {
                    final int temp = src[j];
                    src[j] = src[j + 1];
                    src[j + 1] = temp;
                }
            }
        }
        return src;
    }

    public static int[] selectionSort(int[] src) {
        for (int i = 0; i < src.length - 1; i++) {  //排序次数
            int minIndex = i;
            for (int j = i + 1; j < src.length; j++) {
                if (src[j] < src[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = src[i];
                src[i] = src[minIndex];
                src[minIndex] = temp;
            }
        }
        return src;
    }
}
