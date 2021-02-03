package jixing.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.Point2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class ProgressBarFramePro extends PublicFrame {

    private static final long serialVersionUID = -8985460438597435697L;
    int current = 0;

    public static void main(String[] args) {
	// new ProgressBarFramePro().setVisible(true);
	new ProgressBarFramePro();
    }

    public ProgressBarFramePro() {

	JPanel contentPane = new JPanel();

	setContentPane(contentPane);
	contentPane.setBounds(0, 0, 400, 400);

	final JProgressBar pbMusic = new JProgressBar();
	pbMusic.setBounds(16, 38, 332, 4);
	pbMusic.setValue(current);
	pbMusic.setMaximum(100);
	pbMusic.setUI(new GradientProgressBarUI());
	pbMusic.setBorderPainted(false);
	new Timer().schedule(new TimerTask() {

	    @Override
	    public void run() {
		pbMusic.setValue(current++);
	    }
	}, 0, 50);
	pbMusic.addChangeListener(new ChangeListener() {
	    @Override
	    public void stateChanged(ChangeEvent e) {
		if (pbMusic.getValue() == 100) {
		    dispose();
		}
	    }
	});
	contentPane.add(pbMusic);
	setLocation(600, 300); // 设置登录界面居中
	setSize(200, 70);
	setVisible(true);
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// 真正关闭界面
    }

    public class GradientProgressBarUI extends BasicProgressBarUI {
	@Override
	protected void paintDeterminate(Graphics g, JComponent c) {
	    Graphics2D graphics2d = (Graphics2D) g;

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
