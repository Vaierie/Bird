package com.neutech.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 图片工具类，用于读取图片
 */
public class ImageUtils {

    // 用map集合存储所有图片资源，用final imgMap不可以=new其他东西，但可以增加删除数值
    // list集合适合存储、传递和遍历，map适合查找，treemap适合排序
    private  static  final Map<String,Image> imgMap = new HashMap<String,Image>();

    static {
        // 读取小鸟图片
        imgMap.put("bird",readImage("com/neutech/res/birds.png"));

        // 读取背景图片
        imgMap.put("bg",readImage("com/neutech/res/background.png"));

        // 读取陆地图片
        imgMap.put("land",readImage("com/neutech/res/land.png"));

        // 读取下铅笔图片
        imgMap.put("pd",readImage("com/neutech/res/pie_down.png"));

        // 读取上铅笔图片
        imgMap.put("pu",readImage("com/neutech/res/pie_up.png"));

        // 读取开始按钮图片
        imgMap.put("sb",readImage("com/neutech/res/start_button.png"));
    }

    /**
     * 从map集合中获取图片
     * @param key map集合中的key
     * @return 图片对象
     */
    public static Image getImage(String key){
        return imgMap.get(key);
    }

    /**
     * 传入图片地址，读取图片对象
     * 不用static就需要new一个ImageUtils对象来调用这个函数，但是new一个ImageUtils对象没有意义
     * 一个方法里面用不到成员变量应该都用static
     * @param path 图片地址
     * @return 图片对象
     */
    public static Image readImage(String path) {
        // 需要return image回去
        Image image = null;
        try {
            // 绝对路径，image需要全局变量
            image = ImageIO.read(ImageUtils.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
