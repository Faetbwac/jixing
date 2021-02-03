package jixing.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import jixing.dao.main.User;
import jixing.service.IUserService;
import jixing.service.impl.UserServiceImpl;

public class UserInfoFrame extends PublicFrame {
    /**
     *
     */
    private static final long serialVersionUID = -8856524942861488553L;
    private IUserService userService = new UserServiceImpl();
    private JButton bt_del, bt_insert, bt_out;
    private JTable t;
    public int index;
    public int sel_num;
    public JButton bt_select;

    public UserInfoFrame() {
        init();
        mouseClick();
        this.setTitle("记性—用户信息界面");
        this.setSize(660, 400);
        this.setLocationRelativeTo(null);
        this.setLayout(null); // 绝对布局
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 设置默认关闭操作
    }

    void init() {
        Object[] title = {"账号", "密码", "姓名", "性别", "年龄", "城市",};
        Object[] userarray = userService.selectAll().toArray();
        Object[][] userinfo = new Object[userarray.length][6];
        User u = null;
        for (int i = 0; i < userarray.length; i++) {
            u = (User)userarray[i];
            userinfo[i][0] = u.getUsername();
            userinfo[i][1] = u.getPassword();
            userinfo[i][2] = u.getName();
            userinfo[i][3] = u.getSex();
            userinfo[i][4] = u.getAge();
            userinfo[i][5] = u.getCity();
        }
        t = new JTable(userinfo, title);
        t.setRowHeight(30);// 行高
        t.setColumnSelectionAllowed(false);// 列选禁用
        t.setCellSelectionEnabled(false);// 行列同选禁用
        t.setRowSelectionAllowed(true);// 行选开启
        JScrollPane scrollPane = new JScrollPane(t);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 660, 270);
        this.add(scrollPane);
        bt_insert = new JButton("增加");
        bt_insert.setBounds(25, 300, 100, 30);
        this.add(bt_insert);// 添加到本界面

        bt_del = new JButton("删除");
        bt_del.setBounds(150, 300, 100, 30);
        this.add(bt_del);// 添加到本界面

        bt_out = new JButton("退出");
        bt_out.setBounds(525, 300, 100, 30);
        this.add(bt_out);// 添加到本界面

    }

    void mouseClick() {

        bt_del.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                // 点击调用
                index = t.getSelectedRow(); // 选择的行
                sel_num = t.getSelectedRowCount();// 选择的行数
                if (sel_num == 0) {
                    JOptionPane.showMessageDialog(null, "请先选择使用者！", "提醒", 1);// 提示
                } else if (sel_num > 1) {
                    JOptionPane.showMessageDialog(null, "单次只能操作一行", "提醒", 1);// 提示
                } else {
                    userService.delete((String)t.getValueAt(index, 2));
                    dispose();
                    JOptionPane.showMessageDialog(null, t.getValueAt(index, 2) + "已被删除！", "提醒", 1);// 提示
                    new UserInfoFrame();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {} // 按下调用

            @Override
            public void mouseReleased(MouseEvent e) {}// 释放调用

            @Override
            public void mouseEntered(MouseEvent e) {
                bt_del.setBackground(Color.orange);
            }// 进入调用

            @Override
            public void mouseExited(MouseEvent e) {
                bt_del.setBackground(null);
            }// 离开调用

        });
        bt_insert.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                // 点击调用
                dispose();
                new RegisterFrame();
            }

            @Override
            public void mousePressed(MouseEvent e) {} // 按下调用

            @Override
            public void mouseReleased(MouseEvent e) {}// 释放调用

            @Override
            public void mouseEntered(MouseEvent e) {
                bt_insert.setBackground(Color.orange);
            }// 进入调用

            @Override
            public void mouseExited(MouseEvent e) {
                bt_insert.setBackground(null);
            }// 离开调用

        });
        bt_out.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                // 点击调用
                dispose();
                new LoginFrame();
            }

            @Override
            public void mousePressed(MouseEvent e) {} // 按下调用

            @Override
            public void mouseReleased(MouseEvent e) {}// 释放调用

            @Override
            public void mouseEntered(MouseEvent e) {
                bt_out.setBackground(Color.orange);
            }// 进入调用

            @Override
            public void mouseExited(MouseEvent e) {
                bt_out.setBackground(null);
            }// 离开调用
        });
    }

    // public static void main(String[] args) {
    // // TODO Auto-generated method stub
    // new UserInfoFrame();
    //
    // }

}
