package jixing;

import static jixing.view.PublicFrame.InitGlobalFont;

import java.awt.Font;

import jixing.controller.MusicAction;
import jixing.view.LoginFrame;

/**
 * @Title: BootStrap.java 
 * @Package: jixing 
 * @ClassName: BootStrap 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月18日 下午1:23:02 
 * @version: V6.0   
 */
public class BootStrap {
    /**
     * 
     * @Title: main 
     * @Description: TODO 
     * @param args 
     * @return void   
     */
    static MusicAction musicAction = new MusicAction();

    public static void main(String[] args) {
	InitGlobalFont(new Font("微软雅黑", Font.PLAIN, 14)); // 设置默认字体
	for (int i = 0; i < 1; i++) { // 启动单线程
	    new ExtendsThread().start();
	}

    }

    /**
     * 
     * @Title: BootStrap.java 
     * @Package: jixing 
     * @ClassName: ExtendsThread 
     * @Description: TODO 
     * @author: 石一歌   
     * @date: 2020年12月18日 下午1:39:51 
     * @version: V6.0   
     */
    static class ExtendsThread extends Thread {
	@Override
	public void run() {
	    // musicAction.sing();
	    new LoginFrame();
	}
    }

}
