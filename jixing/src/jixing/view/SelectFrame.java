package jixing.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import jixing.controller.BrowseAction;
import jixing.dao.main.Thing;
import jixing.dao.main.User;

/**
 * 
 * @Title: SelectFrame.java 
 * @Package: jixing.view 
 * @ClassName: SelectFrame 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月18日 下午8:16:53 
 * @version: V6.0   
 */
public class SelectFrame extends PublicFrame {

    /**
     *
     */
    private static final long serialVersionUID = -3626468314470138881L;

    private JTextField tfUserId, tfId, tfName;
    private JComboBox<String> cboCategory; // 下拉选择框
    private DateChooserJButton btChoosDate;// 时间选择按钮
    private JButton btAdd; // 增
    private JButton btDelete; // 删
    private JButton btCheckById;// 查
    private JButton btFix;// 改
    BrowseAction browseAction = new BrowseAction();
    SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 
     * Title: Description:
     * 
     * @param user 
     */
    public SelectFrame(User user) {

	this.setLayout(null); // 绝对布局

	JLabel jlUserId = new JLabel("使用者名称");// 定义登录账号标签
	jlUserId.setBounds(20, 20, 70, 30);// 设置位置
	this.add(jlUserId);// 添加到当前界面
	tfUserId = new JTextField(); // 定义用户名输入框
	tfUserId.setBounds(100, 20, 50, 30);// 设置位置
	this.add(tfUserId);// 添加到当前界面

	JLabel jlId = new JLabel("事件编号");// 定义登录账号标签
	jlId.setBounds(200, 20, 70, 30);// 设置位置
	this.add(jlId);// 添加到当前界面
	tfId = new JTextField(); // 定义用户名输入框
	tfId.setBounds(270, 20, 50, 30);// 设置位置
	this.add(tfId);// 添加到当前界面

	JLabel jlName = new JLabel("事件内容");// 定义登录账号标签
	jlName.setBounds(20, 70, 70, 30);// 设置位置
	this.add(jlName);// 添加到当前界面
	tfName = new JTextField(); // 定义用户名输入框
	tfName.setBounds(100, 70, 50, 30);// 设置位置
	this.add(tfName);// 添加到当前界面

	JLabel jlCategory = new JLabel("分类：");// 定义分类标签
	jlCategory.setBounds(200, 70, 70, 30);// 设置位置
	this.add(jlCategory);// 添加到主界面
	cboCategory = new JComboBox<String>(); // 定义下拉列表框
	cboCategory
		.setModel(new DefaultComboBoxModel<String>(new String[] { "- 请选择 -", "作业", "考试", "活动", "讲座", "约会" }));// 下拉列表属性
	cboCategory.setBounds(250, 70, 100, 25);
	this.add(cboCategory);// 添加到当前界面

	JLabel jlChoosDate = new JLabel("日期：");// 定义分类标签
	jlChoosDate.setBounds(20, 120, 70, 30);// 设置位置
	this.add(jlChoosDate);// 添加到主界面

	btChoosDate = new DateChooserJButton();// 定义登录按钮
	btChoosDate.setBounds(60, 120, 200, 30);// 设置位置
	this.add(btChoosDate);// 添加到当前界面

	btAdd = new JButton("增加"); // 定义查询标签
	btAdd.setBounds(60, 180, 70, 30); // 设置位置
	this.add(btAdd);// 添加到界面中
	btAdd.addActionListener(new ActionListener() { // home 按钮的监听事件
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btAddActionPerformed();
		new BrowseFrame(user);
		dispose();// 关闭当前界面
	    }
	});
	btDelete = new JButton("删除"); // 定义查询标签
	btDelete.setBounds(250, 180, 70, 30); // 设置位置
	this.add(btDelete);// 添加到界面中
	btDelete.addActionListener(new ActionListener() { // home 按钮的监听事件
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btDeleteActionPerformed();
		new BrowseFrame(user);
		dispose();// 关闭当前界面
	    }
	});

	btCheckById = new JButton("查询"); // 定义查询标签
	btCheckById.setBounds(60, 220, 70, 30); // 设置位置
	this.add(btCheckById);// 添加到界面中
	btCheckById.addActionListener(new ActionListener() { // home 按钮的监听事件
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btCheckByIdActionPerformed();
		new BrowseFrame(user);
		dispose();// 关闭当前界面
	    }
	});

	btFix = new JButton("修改"); // 定义查询标签
	btFix.setBounds(250, 220, 70, 30); // 设置位置
	this.add(btFix);// 添加到界面中
	btFix.addActionListener(new ActionListener() { // home 按钮的监听事件
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btFixActionPerformed();
		new BrowseFrame(user);
		dispose();// 关闭当前界面
	    }
	});
	addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
		dispose();
		new BrowseFrame(user);
	    }
	});
	this.setTitle("记性");
	this.setSize(400, 300); // 设置界面大小
	this.setResizable(false); // 设置界面大小不可改变
	this.setLocationRelativeTo(null); // 设置登录界面居中
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 设置默认关闭操作
    }

    /**
     * 
     * @Title: btFixActionPerformed 
     * @Description: TODO  void
     */
    protected void btFixActionPerformed() {
	// TODO Auto-generated method stub
	Thing thing = new Thing(tfUserId.getText().toString(), Integer.valueOf(tfId.getText()),
		tfName.getText().toString(), cboCategory.getSelectedItem().toString(), btChoosDate.getDate());
	browseAction.updateThing(thing);
    }

    /**
     * 
     * @Title: btCheckByIdActionPerformed 
     * @Description: TODO  void
     */
    protected void btCheckByIdActionPerformed() {
	// TODO Auto-generated method stub
	Thing thing = browseAction.findThingById(Integer.valueOf(tfId.getText()));
	// System.out.println(myFmt.format(thing.getEndingdate()));
	speak("你在" + myFmt.format(thing.getEndingdate()) + "前，必须完成" + thing.getCategory() + thing.getName() + "!");
	JOptionPane.showMessageDialog(this,
		"你在" + myFmt.format(thing.getEndingdate()) + "前，必须完成" + thing.getCategory() + thing.getName() + "!");// 提示
    }

    /**
     * 
     * @Title: btDeleteActionPerformed 
     * @Description: TODO  void
     */
    protected void btDeleteActionPerformed() {
	// TODO Auto-generated method stub
	browseAction.deleteThing(Integer.valueOf(tfId.getText()));
    }

    /**
     * 
     * @Title: btAddActionPerformed 
     * @Description: TODO  void
     */
    public void btAddActionPerformed() {
	Thing thing = new Thing(tfUserId.getText().toString(), Integer.valueOf(tfId.getText()),
		tfName.getText().toString(), cboCategory.getSelectedItem().toString(), btChoosDate.getDate());
	browseAction.addThing(thing);
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
