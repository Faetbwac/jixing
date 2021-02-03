package jixing.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import jixing.controller.UserAction;
import jixing.dao.main.User;

/**
 * 
 * @Title: LoginFrame.java 
 * @Package: jixing.view 
 * @ClassName: LoginFrame 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月18日 下午8:14:34 
 * @version: V6.0   
 */
public class LoginFrame extends PublicFrame {

    /**
     *
     */
    private static final long serialVersionUID = -5246864023350812435L;

    private JTextField tfUsername; // 定义一个用户名输入框
    private JPasswordField pfPassword; // 定义一个密码框
    private static boolean isLogin; // 判断是否登录
    private JCheckBox ckbRemember; // 是否记住密码

    private JButton btLogin; // 定义 登录按钮
    private JButton btRegister; // 定义 关闭按钮
    private JButton btDirectuse;// 定义 直接使用按钮

    private JPanel pnbackground = new JPanel();// 定义背景面板
    private ImageIcon image;// 定义图片
    private JLabel background;// 定义背景
    private JLayeredPane pane = new JLayeredPane(); // 分层网格用来设置透明
    UserAction userAction = new UserAction();

    /**
     * 
     * Title: Description:  
     */
    public LoginFrame() {

	// this.setLayout(null); // 绝对布局

	JLabel jlUser = new JLabel("登录账号");// 定义登录账号标签
	jlUser.setBounds(300, 320, 80, 15);// 设置位置
	jlUser.setForeground(Color.white);
	// this.add(jlUser);// 添加到当前界面

	tfUsername = new JTextField(); // 定义用户名输入框
	tfUsername.setBounds(370, 315, 180, 30);// 设置位置
	// this.add(tfUsername);// 添加到当前界面

	JLabel jlPassword = new JLabel("登录密码");// 定义登录密码标签
	jlPassword.setBounds(300, 370, 80, 15);// 设置位置
	jlPassword.setForeground(Color.white);
	// this.add(jlPassword);// 添加到当前界面

	pfPassword = new JPasswordField();// 定义密码输入框
	pfPassword.setBounds(370, 365, 180, 30);// 设置位置
	// this.add(pfPassword);// 添加到当前界面

	ckbRemember = new JCheckBox("记住登录账号");// 定义记住密码勾选框
	ckbRemember.setSelected(true);// 默认勾选
	ckbRemember.setBounds(350, 400, 140, 20);// 设置位置
	ckbRemember.setForeground(Color.white);
	// this.add(ckbRemember);// 添加到当前界面

	btLogin = new JButton("登  录");// 定义登录按钮
	btLogin.setBounds(300, 430, 90, 30);// 设置位置
	btLogin.addActionListener(new ActionListener() { // 登录按钮的监听事件
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btLoginActionPerformed();
	    }
	});
	// this.add(btLogin);// 添加到当前界面

	btRegister = new JButton("注   册");// 定义注册按钮
	btRegister.setBounds(460, 430, 90, 30);// 设置位置
	btRegister.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		new RegisterFrame();// 打开注册界面
		dispose();// 关闭界面
	    }
	});
	// this.add(btRegister);// 添加到当前界面

	btDirectuse = new JButton("直   接   使   用");// 定义注册按钮
	btDirectuse.setBounds(300, 470, 250, 30);// 设置位置
	btDirectuse.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		new BrowseFrame(null);// 打开注册界面
		new ForecastFrame();
		dispose();// 关闭界面
	    }
	});
	// this.add(btDirectuse);// 添加到当前界面

	image = new ImageIcon("image\\background.png");
	background = new JLabel(image); // 把背景图片添加到标签里

	pnbackground.setBounds(0, 0, image.getIconWidth(), image.getIconHeight()); // 把背景面板设置为和图片等高等宽
	pnbackground = (JPanel) this.getContentPane(); // 把此面板设置为内容面板
	pnbackground.add(background);

	jlUser.setOpaque(false);// 设置组件透明
	tfUsername.setOpaque(false);
	jlPassword.setOpaque(false);
	pfPassword.setOpaque(false);
	ckbRemember.setOpaque(false);
	btLogin.setOpaque(false);
	btRegister.setOpaque(false);
	btDirectuse.setOpaque(false);

	pane.add(pnbackground, JLayeredPane.DEFAULT_LAYER);// 最底层
	pane.add(jlUser, JLayeredPane.MODAL_LAYER);// 模式对话层
	pane.add(tfUsername, JLayeredPane.MODAL_LAYER);// 模式对话层
	pane.add(jlPassword, JLayeredPane.MODAL_LAYER);// 模式对话层
	pane.add(pfPassword, JLayeredPane.MODAL_LAYER);// 模式对话层
	pane.add(ckbRemember, JLayeredPane.MODAL_LAYER);// 模式对话层
	pane.add(btLogin, JLayeredPane.MODAL_LAYER);// 模式对话层
	pane.add(btRegister, JLayeredPane.MODAL_LAYER);// 模式对话层
	pane.add(btDirectuse, JLayeredPane.MODAL_LAYER);// 模式对话层

	this.setBounds(100, 100, image.getIconWidth(), image.getIconHeight());
	this.setLayeredPane(pane);// 第二层设置为pane
	this.setVisible(true);

	this.setTitle("记性--事件管理系统");
	// this.setSize(350, 270); // 设置界面大小
	this.setResizable(false); // 设置界面大小不可改变
	this.setLocationRelativeTo(null); // 设置登录界面居中
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 设置默认关闭操作

    }

    /**
     * 
     * @Title: btLoginActionPerformed 
     * @Description: TODO  void
     */
    public void btLoginActionPerformed() {

	// 获取用户名和密码
	String username = tfUsername.getText().trim();
	String password = new String(pfPassword.getPassword()).trim();

	if (("admin").equals(username) && ("admin").equals(password)) {
	    new BrowseFrame(null);
	    this.dispose();
	} else {

	    User user = userAction.login(username, password);// 调用具体的login方法 判断用户名和密码

	    if (user == null) {
		new ProgressBarFrame(user);
		JOptionPane.showMessageDialog(this, "账号或密码不正确！");// 提示
		tfUsername.requestFocus(); // 获取焦点
		// 增加用户体验，选中文本框中的文字
		tfUsername.selectAll();
		return;
	    } else {
		new ProgressBarFrame(user);
		this.setLogin(true);// login状态为true
		// 用户信息完整，则进入事件查询界面
	    }
	    this.dispose();// 关闭当前界面
	}
    }

    /**
     * 
     * @Title: isLogin 
     * @Description: TODO 
     * @return boolean
     */
    public static boolean isLogin() {
	return isLogin;
    }

    /**
     * 
     * @Title: setLogin 
     * @Description: TODO 
     * @param login void
     */
    public void setLogin(boolean login) {
	isLogin = login;
    }

}
