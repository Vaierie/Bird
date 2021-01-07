package com.neutech.player;

import com.neutech.base.Sprite;
import com.neutech.constant.Constant;

import java.awt.*;

public class Bird extends Sprite {

    // 存放三个剪裁开始点的x坐标，9+34+18，61+34+18
    private int[] clipXs = {9, 61, 113};
    // 小鸟裁剪开始点数组的下标
    private double index = 0;

    public Bird() {}

    public Bird(Image image) {
        // 小鸟开始x的位置在窗口宽度的1/4,y在窗口高度的1/2，小鸟一个的宽度为34，高度为24，两个小鸟之间的距离为18
        super(Constant.WINDOW_WIDTH / 4,Constant.WINDOW_HEIGHT / 2, 34, 24,
                9, 10, 34, 24,image);
    }

    @Override
    public void draw(Graphics g) {
        // 通过变换数组的下标传数组的值来切换三个小鸟的动作，Math.floor()向下取整函数
        g.drawImage(getImage(),getX(),getY(),getX() + getWidth(),getY() + getHeight(),
                clipXs[(int) Math.floor(index)],getClipY(),
                clipXs[(int) Math.floor(index)] + getClipWidth(),getClipY() + getClipHeight(),null);
        index += 0.2;
        // index的下标不能大于等于3
        if (index >= 3) {
            index = 0;
        }
    }
}
