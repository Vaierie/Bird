package com.neutech.base;

import java.awt.*;

/**
 * 精灵类，游戏内所有元素的父类
 */
public abstract class Sprite {

    // 绘制到窗口上的起始点坐标
    private int x;
    private int y;
    // 绘制到窗口上元素的宽高
    private int width;
    private int height;
    // 裁剪图片的起始点坐标
    private int clipX;
    private int clipY;
    // 裁剪图片的宽高
    private int clipWidth;
    private int clipHeight;

    // 对象对应的图片
    private Image image;

    // 任何时候都要保证有一个无参的构造函数
    // 构造函数要写在get、set的前面
    public Sprite() {
        super();
    }

    public Sprite(int x, int y, int width, int height, int clipX, int clipY, int clipWidth, int clipHeight, Image image) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.clipX = clipX;
        this.clipY = clipY;
        this.clipWidth = clipWidth;
        this.clipHeight = clipHeight;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getClipX() {
        return clipX;
    }

    public void setClipX(int clipX) {
        this.clipX = clipX;
    }

    public int getClipY() {
        return clipY;
    }

    public void setClipY(int clipY) {
        this.clipY = clipY;
    }

    public int getClipWidth() {
        return clipWidth;
    }

    public void setClipWidth(int clipWidth) {
        this.clipWidth = clipWidth;
    }

    public int getClipHeight() {
        return clipHeight;
    }

    public void setClipHeight(int clipHeight) {
        this.clipHeight = clipHeight;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public  abstract void draw(Graphics g);

    // 抽象子类都要重写，背景类重写没有意义
    public void move() {
    }

}
