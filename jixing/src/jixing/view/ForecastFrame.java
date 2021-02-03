package jixing.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.alibaba.fastjson.JSONObject;

import jixing.controller.FindForecastAction;

public class ForecastFrame extends PublicFrame {

    /**
     *
     */
    private static final long serialVersionUID = 2577042595185329882L;
    private JLabel lb_humidity, lb_pm25, lb_pm10, lb_quality, lb_temp, lb_Cold_index, lb_content;
    private JButton btrefresh;// 定义 刷新按钮
    FindForecastAction jsondata = new FindForecastAction();
    JSONObject data = jsondata.get_weather("101100101");

    public ForecastFrame() {
	try {
	    if (data == null) {
		System.out.println(false + "接口又崩了。。。");
	    }
	    String humidity = data.getString("shidu");
	    float pm25 = Float.parseFloat(data.getString("pm25"));
	    float pm10 = Float.parseFloat(data.getString("pm10"));
	    String quality = data.getString("quality");
	    int temp = Integer.parseInt(data.getString("wendu"));
	    String Cold_index = data.getString("ganmao");
	    for (Object weather : data.getJSONArray("forecast")) {
		JSONObject object = (JSONObject) weather;
		System.out.println();
		lb_content = new JLabel("<html>" + "日期:  " + object.get("date") + ",日出:  " + object.get("sunrise")
			+ ",日落:  " + object.get("sunset") + "</br>" + ", " + object.get("high") + ",  "
			+ object.get("low") + ",空气质量指数:  " + object.get("aqi") + "</br>" + ",风向:  " + object.get("fx")
			+ ",风力:  " + object.get("fl") + ",天气:  " + object.get("type") + "</br>" + ",建议:  "
			+ object.get("notice") + "</html>");
		break;
	    }
	    lb_humidity = new JLabel("湿度: " + humidity);
	    lb_humidity.setBounds(10, 10, 90, 30);
	    this.add(lb_humidity);
	    lb_pm25 = new JLabel("pm2.5: " + pm25);
	    lb_pm25.setBounds(90, 10, 90, 30);
	    this.add(lb_pm25);
	    lb_pm10 = new JLabel("pm10: " + pm10);
	    lb_pm10.setBounds(190, 10, 90, 30);
	    this.add(lb_pm10);
	    lb_temp = new JLabel("平均气温: " + temp);
	    lb_temp.setBounds(290, 10, 100, 30);
	    this.add(lb_temp);
	    lb_quality = new JLabel("空气质量: " + quality);
	    lb_quality.setBounds(10, 50, 140, 30);
	    this.add(lb_quality);

	    String str = "<html>" + "感冒指数:" + Cold_index + "</html>";
	    lb_Cold_index = new JLabel(str);
	    lb_Cold_index.setBounds(110, 50, 250, 60);
	    this.add(lb_Cold_index);

	    lb_content.setBounds(10, 80, 375, 200);
	    this.add(lb_content);

	    btrefresh = new JButton("刷新"); // 定义查询标签
	    btrefresh.setBounds(280, 220, 70, 32); // 设置位置
	    this.add(btrefresh);// 添加到界面中
	    btrefresh.addActionListener(new ActionListener() { // 查询监听事件
		@Override
		public void actionPerformed(ActionEvent e) {
		    new ForecastFrame();
		    dispose();
		}
	    });
	} catch (NumberFormatException | NullPointerException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	this.setLayout(null); // 绝对布局
	this.setTitle("天气预报");
	this.setSize(400, 300); // 设置界面大小
	this.setResizable(false); // 设置界面大小不可改变
	this.setLocation(885, 395); // 设置登录界面居中
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 设置默认关闭操作
    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	new ForecastFrame();

    }
}
