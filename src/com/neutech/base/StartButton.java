package com.neutech.base;

import com.neutech.constant.Constant;

import java.awt.*;

public class StartButton extends Sprite{

    public StartButton() {

    }

    public StartButton(Image image) {
        super(Constant.WINDOW_WIDTH / 2 - 30,Constant.WIDOW_INNER_HEIGHT / 3 + Constant.WINDOW_TOOLBAR_HEIGHT,
                image.getWidth(null),image.getHeight(null),0,0,
                image.getWidth(null),image.getHeight(null),image);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(),getX(),getY(),null);
    }
}
