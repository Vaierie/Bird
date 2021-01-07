package com.neutech.runtime;

import com.neutech.base.Background;
import com.neutech.base.Land;
import com.neutech.base.Score;
import com.neutech.base.StartButton;
import com.neutech.constant.Constant;
import com.neutech.player.Bird;
import com.neutech.player.DownPencil;
import com.neutech.player.Pencil;
import com.neutech.player.UpPencil;
import com.neutech.util.ImageUtils;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;

/**
 * 导演类，同时也是窗口类，调度所有游戏内元素
 */
public class Director extends Frame {

    public Director() {
        init();
    }

    /**
     * 初始化处理
     */
    private void init() {
        // 设置窗口的尺寸
        // 任何时候都不要写死值,编写常量类
        setSize(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
        // 设置居中
        setLocationRelativeTo(null);
        // 设置窗口不允许改变大小
        setResizable(false);
        // 禁止输入法
        enableInputMethods(false);
        // 设置显示状态
        setVisible(true);
        // 窗口右上角关闭按键的处理
        // 窗口都是基于事件驱动的
        // 添加窗口事件监听
        // WindowAdapter()是接口的抽象实现类，就不要求我们要重写了
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 不同数字含义不同，终止退出程序
                System.exit(0);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isLive) {
                    bird.mousePressed(e);
                }else {
                    restart();
                }
            }
        });
        // 刷新线程
        startThread();
        // 创建一组铅笔，程序一开始初始化时需创建一次，刷新重画时判断是否要添加，所以后面在paint方法调用
        createPencil();
    }

    // 创建背景类
    public Background bg = new Background(ImageUtils.getImage("bg"));
    // 创建陆地类
    public Land land = new Land((ImageUtils.getImage("land")));
    // 上下铅笔是一个组合，应该放一个集合
    //    public UpPencil up = new UpPencil(ImageUtils.getImage("pu"),300);
    // 除了线程安全没有区别，添加删除等方法一样方式调用
    public List<Pencil> pencils = new CopyOnWriteArrayList<Pencil>();
    public Bird bird = new Bird((ImageUtils.getImage("bird")));
    public Score score = new Score();
    public StartButton sb = new StartButton(ImageUtils.getImage("sb"));

    public boolean isUpScore = true;
    public boolean isLive = true;

    /**
     * 游戏重启方法
     */
    public void restart() {
        // 重新开始的逻辑
        isLive = true;
        // 启动刷新线程
        startThread();
        // 陆地复原、小鸟复原、成绩复原、铅笔的集合复原
        land = new Land((ImageUtils.getImage("land")));
        bird = new Bird((ImageUtils.getImage("bird")));
        pencils = new CopyOnWriteArrayList<Pencil>();
        score = new Score();
        isUpScore = true;
        // 重新创建一对铅笔
        createPencil();
    }

    /**
     * 重启线程方法
     */
    public void startThread() {
        // 启动刷新线程
        new Thread() {
            @Override
            public void run() {
                // 需要放在一个死循环里才会不断刷新
                while (isLive) {
                    repaint();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void paint(Graphics g) {
        check();
        // 谁后绘制谁就在上面
        // 画背景图
        bg.draw(g);
//        up.draw(g);
        // 集合应该遍历输出
        for (Pencil pencil : pencils) {
            pencil.draw(g);
        }
        // 画陆地图，应在背景图上
        land.draw(g);
        // 画鸟图
        bird.draw(g);
        score.draw(g);
        if (!isLive) {
            sb.draw(g);
        }
        // 陆地需要移动，通过移动x的起始坐标
        land.move();
        // 遍历铅笔后调用移动方法，需要对集合里的每个铅笔对象都改变x值
        for (Pencil pencil : pencils) {
            pencil.move(score.getNum());
        }
        // 小鸟移动
        bird.move();
        // 判断铅笔是否移出左侧界面
        removePencil();
        // 判断是否应该添加铅笔
        addPencil();
        // 用于监测查看集合内铅笔的个数
//        g.drawString("铅笔的个数：" + pencils.size() + "", 10, 40);
        upScore();
    }

    /**
     * 判断加分逻辑
     */
    public void upScore() {
        // 取出第一个铅笔的对象
        Pencil pencil = pencils.get(0);
        // 原为score.setNum(score.getNum() + Constant.SCORE_STEP)
        if (bird.getX() >= pencil.getX() + pencil.getWidth() && isUpScore) {
            score.setNum(score.getNum() + Constant.SCORE_STEP + score.getNum() / 10);
            isUpScore = false;
        }
    }

    /**
     * 碰撞检测
     */
    public void check() {
        // 检测小鸟与地板的碰撞
        if (bird.getY() >= land.getY() - 24) {
            isLive = false;
            // 设置小鸟的位置正好为地板 - 小鸟的高度，不产生撞地板的效果
            bird.setY(land.getY() - 24);
            return;
        }
        // 构建小鸟的矩形对象,创建一次就够了
        Rectangle birdRect = new Rectangle(bird.getX(),bird.getY(),bird.getWidth(),bird.getHeight());
        // 判断小鸟是否与铅笔相交（换图片、记录各个点判断、三角形判断）
        for (Pencil pencil : pencils) {
            // 构建铅笔的矩形对象
            Rectangle pencilRect = new Rectangle(pencil.getX(),pencil.getY(),pencil.getWidth(),pencil.getHeight());
            if (pencilRect.intersects(birdRect)) {
                isLive = false;
                // 立刻终止这个方法的运行
                return;
            }
        }
    }

    /**
     * 判断移除铅笔
     */
    public void removePencil() {
        for (Pencil pencil : pencils) {
            // 当铅笔的最右边到达窗口边才移除，即绘制的起始x坐标应该是 -铅笔图片的宽度
            if (pencil.getX() <= -pencil.getWidth()) {
                pencils.remove(pencil);
                isUpScore = true;
            }
        }
    }

    /**
     * 判断是否添加铅笔
     */
    public void addPencil() {
        // 当铅笔最左边的铅笔的一半到达窗口宽度的一半是添加，即绘制的起始x坐标 <= 窗口宽度的一半 - 铅笔宽度的一半
        // 当铅笔符合前面这个判断时，每刷新一次都会添加铅笔这个时候就需要控制铅笔添加的间隔，可以通过控制当前集合的大小
        if (pencils.get(0).getX() <= ((Constant.WINDOW_WIDTH - pencils.get(0).getWidth()) / 2)
                && pencils.size() < 4) {
            createPencil();
        }
    }

    /**
     * 创建一对铅笔
     */
    public void createPencil() {
        // 生成top值，范围：屏幕的1/8~1/2
        int minTop = Constant.WIDOW_INNER_HEIGHT / 8 + Constant.WINDOW_TOOLBAR_HEIGHT;
        int maxTop = Constant.WIDOW_INNER_HEIGHT / 2 + Constant.WINDOW_TOOLBAR_HEIGHT;
        // Math.random()产生0~1的随机数，浮点数相乘还是浮点数需要强制转型
        int top = minTop + (int) ((maxTop - minTop) * Math.random());
        // 将创建的铅笔添加进集合
        pencils.add(new UpPencil(ImageUtils.getImage("pu"), top));
        pencils.add(new DownPencil(ImageUtils.getImage("pd"), top));
    }

    // 页面缓存刷新不闪烁
    // 不重写调用的是Frame类中的update方法有清屏的操作
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.GREEN);
        gOffScreen.fillRect(0, 0, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }
}
