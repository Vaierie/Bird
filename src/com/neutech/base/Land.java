package com.neutech.base;

import com.neutech.constant.Constant;

import java.awt.*;

public class Land extends Sprite{

    public Land() {
    }

    public Land(Image image) {
        // 传给父类，设置相应的值，陆地开始的y坐标应该是窗口的高度-图片的高度
        super(0, Constant.WINDOW_HEIGHT - image.getHeight(null),image.getWidth(null),image.getHeight(null),
                0,0,image.getWidth(null),image.getHeight(null),image);
    }

    @Override
    public void draw(Graphics g) {
        // 画笔画陆地
        g.drawImage(getImage(),getX(),getY(),null);
    }

    @Override
    public void move() {
        // 陆地移动方法，向左移动应为-，画图理解if里的判断条件
        setX(getX() - Constant.SPEED);
        if (getX() <= -(getImage().getWidth(null) - Constant.WINDOW_WIDTH)) {
            setX(0);
        }
    }
}
