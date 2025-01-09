package com.wong;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2025/1/9 10:53
 */
public class Slb {
    public static void main(String[] args) {
        double totalMoney = 77713.22;
        double price1 = 10.09;
        double price2 = 49.86;

        findBestCombination(totalMoney, price1, price2);
    }

    public static void findBestCombination(double totalMoney, double price1, double price2) {
        double minRemaining = totalMoney;
        int bestQuantity1 = 0;
        int bestQuantity2 = 0;

        // 遍历可能的商品2（昂贵商品）数量，以10为步长
        for (int quantity2 = 0; quantity2 * price2 <= totalMoney; quantity2 += 10) {
            double remainingMoney = totalMoney - quantity2 * price2;
            // 计算可以购买的商品1（便宜商品）的最大数量
            int quantity1 = (int) (remainingMoney / price1 / 10) * 10; //确保是10的倍数

            double currentTotal = quantity1 * price1 + quantity2 * price2;
            double currentRemaining = totalMoney - currentTotal;

            // 如果当前剩余金额更少，则更新最佳方案
            if (currentRemaining >= 0 && currentRemaining < minRemaining) {
                minRemaining = currentRemaining;
                bestQuantity1 = quantity1;
                bestQuantity2 = quantity2;
            }

            //考虑到可能存在除不尽的情况，向上取10的倍数再计算一次
            quantity1 = (int) Math.ceil(remainingMoney / price1 / 10) * 10;
            currentTotal = quantity1 * price1 + quantity2 * price2;
            currentRemaining = totalMoney - currentTotal;
            if (currentRemaining >= 0 && currentRemaining < minRemaining) {
                minRemaining = currentRemaining;
                bestQuantity1 = quantity1;
                bestQuantity2 = quantity2;
            }
        }

        System.out.println("最佳购买方案：");
        System.out.println("商品1（单价 " + price1 + " 元）：" + bestQuantity1 + " 个");
        System.out.println("商品2（单价 " + price2 + " 元）：" + bestQuantity2 + " 个");
        System.out.println("总花费：" + (bestQuantity1 * price1 + bestQuantity2 * price2) + " 元");
        System.out.println("剩余金额：" + minRemaining + " 元");
    }
}
