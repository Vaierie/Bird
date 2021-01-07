package com.neutech.player;

import com.neutech.constant.Constant;

import java.awt.*;

public class UpPencil extends Pencil{

    public UpPencil() {}

    public UpPencil(Image image,int top) {
        // 指的是Pencil里的super，上铅笔开始的点y的值=上铅笔的笔尖的值-图片的高度（为一个负值）
        super(Constant.WINDOW_WIDTH,top - image.getHeight(null),image.getWidth(null),image.getHeight(null),
                0,0,image.getWidth(null),image.getHeight(null),image);

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(),getX(),getY(),null);
    }
}
