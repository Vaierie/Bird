package com.neutech.base;

import com.neutech.constant.Constant;

import java.awt.*;

public class Background extends Sprite{

    public Background() {}

    public Background(Image image) {
        // 传递给父类，由父类自己进行设置
        super(0, Constant.WINDOW_TOOLBAR_HEIGHT, image.getWidth(null), image.getHeight(null),
                0, 0, image.getWidth(null), image.getHeight(null), image);
    }

    @Override
    public void draw(Graphics g) {
        // 使用g画笔将自己绘制到窗口上
//        g.drawImage(getImage(),getX(),getY(),getX() + getWidth(),getY() + getHeight(),
//                getClipX(),getClipY(),getClipX() + getWidth(),getY() + getHeight(),null);
        // 不裁剪的简化方法
        g.drawImage(getImage(),getX(),getY(),null);
   }
}
