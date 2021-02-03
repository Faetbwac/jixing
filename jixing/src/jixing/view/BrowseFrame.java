package jixing.view;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import jixing.controller.BrowseAction;
import jixing.controller.DeleteAction;
import jixing.controller.RemindAction;
import jixing.dao.main.Thing;
import jixing.dao.main.User;

/**
 * 
 * @Title: BrowseFrame.java 
 * @Package: jixing.view 
 * @ClassName: BrowseFrame 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月18日 下午8:09:52 
 * @version: V6.0   
 */
public class BrowseFrame extends PublicFrame {

    /**
     *
     */
    private static final long serialVersionUID = 7560419529022677763L;
    private JLabel lblThingName; // 定义事件名标签
    private JLabel lblCategory; // 定义分类标签
    private JLabel lblThing; // 定义事件标签
    private JLabel lblName; // 定义用户名标签
    private JLabel lblCity; // 定义城市标签
    private JLabel lblQuantity; // 定义事件数量标签
    private JLabel lblCartDetail; // 定义详情
    private JTable tblThing;// 定义表格
    private JButton btnQuery, btnFix, btnHome, btnRefresh, btnRefreshContinued; // 查询、购买按钮
    private JScrollPane scrollPane;
    private JTextField txtThingName; // 定义商品名文本框
    private JComboBox<String> cboCategory; // 下拉选择框
    private User user = null;
    static BrowseAction browseAction = new BrowseAction();
    static DeleteAction deleteaction = new DeleteAction();
    static RemindAction remindAction = new RemindAction();
    private List<Thing> list;// 获取事件列表
    static TrayIcon trayIcon = null; // 托盘图标
    static SystemTray tray = null; // 本操作系统托盘的实例
    private SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 
     * Title: Description:
     * 
     * @param user 
     */
    public BrowseFrame(User user) {
	if (SystemTray.isSupported()) // 如果操作系统支持托盘
	{
	    this.tray();
	}
	this.setVisible(true);
	// 窗口关闭时触发事件
	addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
		System.exit(0);
	    }

	    @Override
	    public void windowIconified(WindowEvent e) {
		try {
		    tray.add(trayIcon); // 将托盘图标添加到系统的托盘实例中
		    setVisible(false); // 使窗口不可视
		    dispose();
		} catch (AWTException ex) {
		    ex.printStackTrace();
		}
	    }
	});

	this.setLayout(null); // 设置绝对布局
	this.user = user;
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 设置默认关闭操作

	lblThingName = new JLabel("事件名："); // 定义商品名标签
	lblThingName.setBounds(10, 64, 70, 15);// 设置位置
	this.add(lblThingName); // 添加到界面中

	txtThingName = new JTextField(10); // 定义事件名输入框
	txtThingName.setBounds(71, 59, 159, 25);// 设置位置
	this.add(txtThingName);// 添加到界面中

	btnQuery = new JButton("查询"); // 定义查询标签
	btnQuery.setBounds(600, 55, 70, 32); // 设置位置
	this.add(btnQuery);// 添加到界面中
	// 查询按钮点击 - 事件处理程序
	btnQuery.addActionListener(new ActionListener() { // 查询监听事件
	    @Override
	    public void actionPerformed(ActionEvent e) {

		String name = txtThingName.getText().toString(); // 获取查询的name
		Object category = cboCategory.getSelectedItem();// 获取查询选择的分类

		if (category.toString().equals("- 请选择 -")) { // 如果不选择 或者说选择的是“请选择”
		    initThingTableModel();// 初始化表格
		    showThing(name, null, user); // 根据name去查询
		} else {
		    initThingTableModel();// 初始化表格
		    showThing(name, category.toString(), user);// 展示商品信息
		}
	    }
	});

	lblName = new JLabel("您好,记性系统欢迎您！"); // 定义一个姓名标签
	lblName.setBounds(10, 10, 200, 15);// 设置位置
	this.add(lblName);// 添加到界面中

	lblQuantity = new JLabel("0件");// 设置购物车商品总数
	lblQuantity.setBounds(131, 402, 42, 15);// 设置位置
	this.add(lblQuantity);

	tblThing = new JTable(); // 定义一个表格
	scrollPane = new JScrollPane();// 设置滚动面板
	scrollPane.setBounds(5, 100, 724, 273);// 设置表格再界面中的位置
	scrollPane.setViewportView(tblThing); // 传递table列表到界面
	this.add(scrollPane); // 将滚动面板添加到界面

	lblCity = new JLabel("来自于："); // 定义 来自于 标签
	lblCity.setBounds(450, 10, 100, 15); // 设置位置
	this.add(lblCity); // 添加到界面

	JSeparator separator = new JSeparator(); // 垂直分割线
	separator.setBounds(10, 35, 724, 2); // 设置位置
	this.add(separator); // 将垂直分割线添加到界面

	btnFix = new JButton("修 改"); // 定义购买按钮
	btnFix.setBounds(600, 394, 70, 32); // 设置按钮位置
	this.add(btnFix);// 将按钮添加到界面
	btnFix.addActionListener(new ActionListener() { // home 按钮的监听事件
	    @Override
	    public void actionPerformed(ActionEvent e) {
		// 返回登录界面
		new SelectFrame(user); // 打开登录界面
		dispose();// 关闭当前界面
	    }
	});

	btnHome = new JButton("退出"); // 定义home按钮
	btnHome.setBounds(600, 2, 70, 32);// 设置按钮位置
	this.add(btnHome);// 添加到界面中
	btnHome.addActionListener(new ActionListener() { // home 按钮的监听事件
	    @Override
	    public void actionPerformed(ActionEvent e) {
		// 返回登录界面
		new LoginFrame(); // 打开登录界面
		dispose();// 关闭当前界面
	    }
	});

	btnRefreshContinued = new JButton("持续刷新"); // 定义购买按钮
	btnRefreshContinued.setBounds(330, 394, 140, 32); // 设置按钮位置
	this.add(btnRefreshContinued);// 将按钮添加到界面
	btnRefreshContinued.addActionListener(new ActionListener() { // 按钮的监听事件
	    @Override
	    public void actionPerformed(ActionEvent e) {
		reminder(5);
	    }
	});

	btnRefresh = new JButton("刷新"); // 定义购买按钮
	btnRefresh.setBounds(500, 394, 70, 32); // 设置按钮位置
	this.add(btnRefresh);// 将按钮添加到界面
	btnRefresh.addActionListener(new ActionListener() { // 按钮的监听事件
	    @Override
	    public void actionPerformed(ActionEvent e) {
		refresh();
	    }
	});

	lblThing = new JLabel("代办事件数："); // 定义事件数标签
	lblThing.setBounds(30, 402, 110, 15);// 设置位置
	this.add(lblThing);// 添加到当前界面

	lblCartDetail = new JLabel("查看详情"); // 定义查看详情标签
	lblCartDetail.setForeground(Color.BLUE); // 设置字体颜色
	lblCartDetail.setBounds(180, 402, 81, 15); // 设置位置
	this.add(lblCartDetail);// 添加到当前界面

	lblCategory = new JLabel("分类：");// 定义分类标签
	lblCategory.setBounds(300, 64, 70, 15);// 设置位置
	this.add(lblCategory);// 添加到主界面

	cboCategory = new JComboBox<String>(); // 定义下拉列表框
	cboCategory
		.setModel(new DefaultComboBoxModel<String>(new String[] { "- 请选择 -", "作业", "考试", "活动", "讲座", "约会" }));// 下拉列表属性
	cboCategory.setBounds(350, 59, 159, 25);// 设置位置
	this.add(cboCategory);// 添加带当前界面

	initDatas(); // 初始化数据
	initThingTableModel(); // 初始化表格信息
	showThing(null, null, user);// 展示表格数据

	this.setTitle("记性"); // 界面标题
	this.setSize(750, 500);// 设置界面位置大小
	this.setResizable(false);// 设置界面大小不可改变
	this.setLocationRelativeTo(null); // 设置登录界面居中
    }

    /**
     * 
     * @Title: tray 
     * @Description: TODO  void
     */
    private void tray() {
	// TODO Auto-generated method stub
	tray = SystemTray.getSystemTray(); // 获得本操作系统托盘的实例
	ImageIcon icon = new ImageIcon("image/tixing.png"); // 将要显示到托盘中的图标
	// 24px的png格式图片
	PopupMenu pop = new PopupMenu(); // 构造一个右键弹出式菜单
	String open = new String("打开记性");
	String close = new String("退出");
	MenuItem show = new MenuItem(open);
	MenuItem exit = new MenuItem(close);
	pop.add(show);
	pop.add(exit);
	show.addActionListener(new ActionListener() // 点击“显示窗口”菜单后将窗口显示出来
	{
	    @Override
	    public void actionPerformed(ActionEvent e) {
		tray.remove(trayIcon); // 从系统的托盘实例中移除托盘图标
		setExtendedState(JFrame.NORMAL);
		setVisible(true); // 显示窗口
		toFront();
	    }
	});
	exit.addActionListener(new ActionListener() // 点击“退出演示”菜单后退出程序
	{
	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(0); // 退出程序
	    }
	});

	trayIcon = new TrayIcon(icon.getImage(), "记性 v7.0", pop);
	// 添加鼠标监听器，当鼠标在托盘图标上双击时，默认显示窗口
	trayIcon.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) // 鼠标双击
		{
		    tray.remove(trayIcon); // 从系统的托盘实例中移除托盘图标
		    setExtendedState(JFrame.NORMAL);
		    setVisible(true); // 显示窗口
		    toFront();
		}
	    }
	});

    }

    /**
     * 
     * @Title: initThingTableModel 
     * @Description: TODO  void
     */
    private void initThingTableModel() {
	// 创建一个JTable的默认显示模式
	DefaultTableModel dt = new DefaultTableModel();
	// 设置JTable的列的个数和列的名字
	dt.setColumnIdentifiers(new Object[] { "编号", "使用者名称", "类别", "具体内容", "截止时间", });

	tblThing.setBackground(new Color(255, 255, 255));
	// 设置JTable表格对象被选中行的背景色
	tblThing.setSelectionBackground(new Color(255, 255, 255));
	// 设置JTable表格对象被选中行的字体色
	tblThing.setSelectionForeground(new Color(255, 103, 0));
	// 为表格设置商品信息表格模型
	tblThing.setModel(dt);

	// 设置表格的列
	tblThing.getColumnModel().getColumn(0).setPreferredWidth(70);
	tblThing.getColumnModel().getColumn(1).setPreferredWidth(70);
	tblThing.getColumnModel().getColumn(2).setPreferredWidth(70);
	tblThing.getColumnModel().getColumn(3).setPreferredWidth(70);
	tblThing.getColumnModel().getColumn(4).setPreferredWidth(150);

    }

    /**
     * 
     * @Title: showThing 
     * @Description: TODO 
     * @param name
     * @param category
     * @param user
     * @return void
     */
    private void showThing(String name, String category, User user) { // 展示所有商品信息

	List<Thing> thing = browseAction.queryThing(name, category, user); // 调用查询方法

	Iterator<Thing> it = thing.iterator();
	while (it.hasNext()) {
	    Thing value = it.next();
	    if (deleteaction.isOut(value)) {
		it.remove();
	    }
	    if (remindAction.isOut(value)) {
		speak(myFmt.format(value.getEndingdate()) + "的" + value.getName() + value.getCategory() + "马上要截止了！");
	    }
	}

	setList(thing);

	lblQuantity.setText(list.size() + "件");

	DefaultTableModel dt = (DefaultTableModel) tblThing.getModel(); // 在表格中增加内容
	for (int i = 0; i < thing.size(); i++) { // 遍历thing 列表 将数据添加到表格中
	    dt.insertRow(i, new Object[] { thing.get(i).getId(), thing.get(i).getUserId(), thing.get(i).getCategory(),
		    thing.get(i).getName(), myFmt.format(thing.get(i).getEndingdate()) });
	}
    }

    /**
     * 
     * @Title: refresh 
     * @Description: TODO 
     * @return void
     */
    public void refresh() { // 刷新界面数据
	DefaultTableModel model = (DefaultTableModel) tblThing.getModel();
	cboCategory
		.setModel(new DefaultComboBoxModel<String>(new String[] { "- 请选择 -", "作业", "考试", "活动", "讲座", "约会" }));// 下拉列表属性
	model.setRowCount(0); // 清空表格数据
	List<Thing> list = browseAction.showAll(null); // 获取所有的商品
	Iterator<Thing> it = list.iterator();
	while (it.hasNext()) {
	    Thing value = it.next();
	    if (deleteaction.isOut(value)) {
		speak(myFmt.format(value.getEndingdate()) + "的" + value.getName() + value.getCategory() + "已过期！");
		it.remove();
	    }
	    if (remindAction.isOut(value)) {
		speak(myFmt.format(value.getEndingdate()) + "的" + value.getName() + value.getCategory() + "马上要截止了！");
	    }
	}
	lblQuantity.setText(list.size() + "件");

	for (int i = 0; i < list.size(); i++) {// 遍历list
	    // 重新为表格填数据
	    Thing thing = (Thing) list.get(i); // 获取每个thing
	    model.addRow(// 添加到表格中
		    new Object[] { thing.getId(), thing.getUserId(), thing.getCategory(), thing.getName(),
			    myFmt.format(thing.getEndingdate()) });
	}
    }

    /**
     * 
     * @Title: initDatas 
     * @Description: TODO  void
     */
    private void initDatas() {
	if (user == null) {
	    this.btnHome.setText("登录");
	    return;
	}
	// 显示用户真实姓名 + 欢迎语
	this.lblName.setText(user.getName() + ",您好,欢迎登录记性");
	// 显示用户所在地
	this.lblCity.setText("来自于: " + user.getCity());
    }

    /**
     * 
     * @Title: getList 
     * @Description: TODO 
     * @return List<Thing>
     */
    public List<Thing> getList() {
	return list;
    }

    /**
     * 
     * @Title: setList 
     * @Description: TODO 
     * @param list void
     */
    public void setList(List<Thing> list) {
	this.list = list;
    }

    /**
     * 
     * @Title: reminder 
     * @Description: TODO 
     * @param seconds
     *            void
     */
    public void reminder(int seconds) {
        Timer timer = new Timer();
        @Override
        timer.schedule(new TimerTask() {
            public void run() {
                refresh();
            }
        }, 1000, seconds * 1000);// 1秒后运行，间隔seconds秒

    }

    /**
     * 
     * @Title: speak 
     * @Description: TODO 
     * @param s void
     */
    public void speak(String s) {
	ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
	Dispatch sapo = sap.getObject();
	try {
	    // 音量 0-100
	    sap.setProperty("Volume", new Variant(100));
	    // 语音朗读速度 -10 到 +10
	    sap.setProperty("Rate", new Variant(0));
	    // 执行朗读
	    Dispatch.call(sapo, "Speak", new Variant(s));
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    sapo.safeRelease();
	    sap.safeRelease();
	}

    }

}
