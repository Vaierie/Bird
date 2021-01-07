package com.neutech.player;

import com.neutech.base.Sprite;
import com.neutech.constant.Constant;

import java.awt.*;

public abstract class Pencil extends Sprite {

    public Pencil() {

    }

    public Pencil(int x, int y, int width, int height, int clipX, int clipY, int clipWidth, int clipHeight, Image image) {
        super(x, y, width, height, clipX, clipY, clipWidth, clipHeight, image);
    }

    @Override
    public void move() {
        setX(getX() - Constant.SPEED);
    }
}
