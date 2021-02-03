package jixing.view;

import java.awt.Font;
import java.awt.Image;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * 
 * @Title: PublicFrame.java 
 * @Package: jixing.view 
 * @ClassName: PublicFrame 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月18日 下午8:15:49 
 * @version: V6.0   
 */
public class PublicFrame extends JFrame {

    private static final long serialVersionUID = 1031309698454821804L;

    /**
     * 使用静态代码块 快速读取美化GUI
     */
    static {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);

        }
    }// 查找并使用 Nimbus 风格

    /**
     * 
     * Title: Description:  
     */

    public PublicFrame() {
        // 创建设置框架的图标
        Image jixing = new JFrame().getToolkit().getImage("image/3.png");
        // 设置图标的图片
        this.setIconImage(jixing);
        // 设置框架可见
        this.setVisible(true);

    }

    /**
     * 
     * @Title: InitGlobalFont 
     * @Description: TODO 
     * @param font
     *            void
     */
    public static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }
}
