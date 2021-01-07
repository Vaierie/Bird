package com.neutech.constant;

/**
 * 常量类
 */
public class Constant {
    // 常量必须大写，多个单词用下划线连接
    // 窗口宽度
    public static final int WINDOW_WIDTH = 375;
    // 667 + 30,通常26，自己的30
    // 窗口高度
    public static final int WINDOW_HEIGHT = 697;
    // 工具栏高度
    public static final int WINDOW_TOOLBAR_HEIGHT = 30;
    // 窗口内部高度
    public static final int WIDOW_INNER_HEIGHT = WINDOW_HEIGHT - WINDOW_TOOLBAR_HEIGHT;
    // 上下铅笔之间的距离
    public static final int PENCIL_GAP = WIDOW_INNER_HEIGHT / 5;

    // 图片移动速度
    public static int SPEED = 4;



}
