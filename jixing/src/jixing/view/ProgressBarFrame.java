package jixing.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicProgressBarUI;

import jixing.dao.main.User;

/**
 * 
 * @Title: ProgressBarFrame.java 
 * @Package: jixing.view 
 * @ClassName: ProgressBarFrame 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月21日 下午5:18:26 
 * @version: V6.0   
 */
public class ProgressBarFrame extends PublicFrame {
    /**
     *
     */
    private static final long serialVersionUID = 6201342837644826688L;
    private static final int MIN_PROGRESS = 0;
    private static final int MAX_PROGRESS = 100;

    private static int currentProgress = MIN_PROGRESS;

    /**
     * 
     * Title: Description:
     * 
     * @param user 
     */
    public ProgressBarFrame(User user) {
        this.setTitle("登陆中");
        this.setSize(250, 75);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// 真正关闭界面

        JPanel panel = new JPanel();

        // 创建一个进度条
        JProgressBar progressBar = new JProgressBar();
        // 设置进度的 最小值 和 最大值
        progressBar.setMinimum(MIN_PROGRESS);
        progressBar.setMaximum(MAX_PROGRESS);
        // 设置当前进度值
        progressBar.setValue(currentProgress);
        // 绘制百分比文本（进度条中间显示的百分数）
        progressBar.setStringPainted(true);
        // 添加进度改变通知
        progressBar.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (progressBar.getValue() == 100) {
                    JOptionPane.showInternalMessageDialog(null, "登录成功", "提示", JOptionPane.PLAIN_MESSAGE);// 提示
                    dispose();
                    new BrowseFrame(user);// 打开注册界面

                }
            }
        });
        progressBar.setUI(new GradientProgressBarUI());
        // 添加到内容面板
        panel.add(progressBar);
        this.setContentPane(panel);
        this.setVisible(true);

        // 模拟延时操作进度, 每隔 0.04 秒更新进度
        new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentProgress++;
                progressBar.setValue(currentProgress);
            }
        }).start();
    }

    /**
     * 
     * @Title: ProgressBarFrame.java 
     * @Package: jixing.view 
     * @ClassName: GradientProgressBarUI 
     * @Description: TODO 
     * @author: 石一歌   
     * @date: 2020年12月21日 下午5:18:48 
     * @version: V6.0   
     */
    public class GradientProgressBarUI extends BasicProgressBarUI {
        @Override
        protected void paintDeterminate(Graphics g, JComponent c) {
            Graphics2D graphics2d = (Graphics2D)g;
            Insets b = progressBar.getInsets();
            // JProgressBar的边界区域
            int width = progressBar.getWidth();
            int height = progressBar.getHeight();
            int barRectWidth = width - (b.right + b.left);
            int barRectHeight = height - (b.top + b.bottom);
            int arcSize = height / 2 - 1;
            int amountFull = getAmountFull(b, barRectWidth, barRectHeight);
            // 已完成的进度
            graphics2d.setColor(Color.white);
            graphics2d.fillRoundRect(0, 0, width - 1, height, arcSize, arcSize);
            // 绘制JProgressBar的背景
            // 用GradientPaint类来实现渐变色显示进度
            // 设置了开始点和终止点，并设置好这两个点的颜色，系统会自动在这两个点中用渐变色来填充
            Point2D start = new Point2D.Float(0, 0);
            Point2D end = new Point2D.Float(amountFull - 1, barRectHeight - 1);
            // 这里设置的终止点是当前已经完成的进度的那个点
            GradientPaint gradientPaint = new GradientPaint(start, Color.gray, end, Color.gray);
            graphics2d.setPaint(gradientPaint);
            graphics2d.fillRoundRect(b.left, b.top, amountFull - 1, barRectHeight, arcSize, arcSize);// 这里实现的是圆角的效果将arcSize调成0即可实现矩形效果
        }
    }
}
