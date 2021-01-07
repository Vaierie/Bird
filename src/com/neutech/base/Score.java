package com.neutech.base;

import com.neutech.constant.Constant;

import java.awt.*;

public class Score extends Sprite{

    // 分数值
    private int num;

    public Score() {
        init();
    }

    private void init() {
        setX(Constant.WINDOW_WIDTH / 2);
        setY(Constant.WIDOW_INNER_HEIGHT / 18 + Constant.WINDOW_TOOLBAR_HEIGHT);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(255,203,235));
        g.setFont(new Font("Arial",Font.BOLD,25));
        g.drawString(String.valueOf(num),getX(),getY());
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
