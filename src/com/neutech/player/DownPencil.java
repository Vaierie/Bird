package com.neutech.player;

import com.neutech.constant.Constant;

import java.awt.*;

public class DownPencil extends Pencil{

    public DownPencil() {}

    public DownPencil(Image image,int top) {
        // 指的是Pencil里的super，下铅笔开始的点y的值=下铅笔的笔尖的值=上笔尖的值top+两个笔之间的距离（内窗口高度的1/5）
        super(Constant.WINDOW_WIDTH,top + Constant.PENCIL_GAP,image.getWidth(null),image.getHeight(null),
                0,0,image.getWidth(null),image.getHeight(null),image);

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(),getX(),getY(),null);
    }
}
