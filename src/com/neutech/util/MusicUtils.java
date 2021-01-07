package com.neutech.util;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MusicUtils extends Thread{

//    Player player;
//    InputStream music;
    private InputStream mp3Name;

    private static final Map<String, InputStream> musicMap = new HashMap<String, java.io.InputStream>();

    static {
//        musicMap.put("bgm","D:\\code\\bird\\src\\com\\neutech\\res\\bgm.mp3");
        musicMap.put("bgm",MusicUtils.class.getClassLoader().getResourceAsStream("com/neutech/res/bgm.mp3"));
        musicMap.put("overBgm",MusicUtils.class.getClassLoader().getResourceAsStream("com/neutech/res/overBgm.mp3"));
    }


    public MusicUtils(String key){
        this.mp3Name = musicMap.get(key);
    }

    @Override
    public void run() {
        for (;;) {
            InputStream resourceAsStream = mp3Name;
            try {
                AdvancedPlayer advancedPlayer = new AdvancedPlayer(resourceAsStream);
                advancedPlayer.play();
            } catch (JavaLayerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

//    public MusicUtils(String file) {
//        this.music = file;
//    }
//    public void run() {
//        try {
//            play();
//        } catch (FileNotFoundException | JavaLayerException e) {
//            e.printStackTrace();
//        }
//    }
//    public void play() throws FileNotFoundException, JavaLayerException {
//        BufferedInputStream buffer = new BufferedInputStream(music);
//        player = new Player(buffer);
//        player.play();
//    }

//    private String mp3Name;
//
//    public MusicUtils() {
//    }
//
//    public MusicUtils(String mp3Name) {
//        this.mp3Name = mp3Name;
//    }
//
//
//
//    @Override
//    public void run() {
//        for (;;) {
//            InputStream resourceAsStream = MusicUtils.class.getClassLoader().getResourceAsStream("com/neusoft/res/"+mp3Name);
//            try {
//                AdvancedPlayer advancedPlayer = new AdvancedPlayer(resourceAsStream);
//                advancedPlayer.play();
//            } catch (JavaLayerException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
