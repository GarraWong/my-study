package com.wong.testdemo.enums;

/**
 * 输入类描述
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/30 14:11
 */
public enum PlateColor {
    BLUE(0, "蓝色"),
    YELLOW(1, "黄色"),
    BLACK(2, "黑色"),
    WHITE(3, "白色"),
    GRADIENT_GREEN(4, "渐变绿"),
    YELLOW_GREEN(5, "黄绿双拼"),
    BLUE_WHITE(6, "蓝白渐变"),
    GREEN(11, "绿色"),
    RED(12, "红色"),
    ;
    private int code;
    private String color;

    PlateColor(int code, String color) {
        this.code = code;
        this.color = color;
    }

    public static boolean isColorful(Integer code) {
        if (code == null) {
            return false;
        }
        int parser = Integer.valueOf(code);
        PlateColor[] values = PlateColor.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].code == parser) {
                return true;
            }
        }
        return false;
    }
}
