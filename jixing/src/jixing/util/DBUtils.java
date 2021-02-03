package jixing.util;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 
 * @Title: DBUtils.java 
 * @Package: jixing.util 
 * @ClassName: DBUtils 
 * @Description: JDBC工具类的实现 用处：简化企业级开发过程中代码的冗余性
 * @author: 石一歌   
 * @date: 2020年12月18日 下午8:07:26 
 * @version: V6.0   
 */
public class DBUtils {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    /**
     * 使用静态代码快读取配置文件 完成驱动的加载
     */
    static {
	// 定义一个Map集合--Properties
	Properties properties = new Properties();
	try {
	    // 使用Properties集合的load方法加载配置文件
	    properties.load(new FileInputStream(new File("db.properties")));
	    driver = properties.getProperty("jdbc.driver");
	    url = properties.getProperty("jdbc.url");
	    user = properties.getProperty("jdbc.user");
	    password = properties.getProperty("jdbc.password");
	    // 加载驱动
	    Class.forName(driver);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * 
     * @Title: getConnection 
     * @Description: TODO 获取连接的工具方法
     * @return
     * @throws SQLException Connection
     */
    public static Connection getConnection() throws SQLException {
	Connection connection = DriverManager.getConnection(url, user, password);
	return connection;
    }

    /**
     * 
     * @Title: getStatement 
     * @Description: TODO获取执行sql对象的方法 
     * @param conn
     * @return
     * @throws SQLException Statement
     */
    public static Statement getStatement(Connection conn) throws SQLException {
	return conn.createStatement();
    }

    /**
     * 
     * @Title: executeDMLOpertion 
     * @Description: TODO 通用DML操作工具方法
     * @param stat
     * @param sql
     * @return int
     */
    public static int executeDMLOpertion(Statement stat, String sql) {
	int executeUpdate = 0;
	try {
	    executeUpdate = stat.executeUpdate(sql);
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return executeUpdate;
    }

    /**
     * 
     * @Title: executeDQLOpertionGetSingleData 
     * @Description: TODO 通用DQL操作工具方法 查询单条
     * @param <T>
     * @param stat
     * @param sql
     * @param clz
     * @return T
     */
    public static <T> T executeDQLOpertionGetSingleData(Statement stat, String sql, Class<T> clz) {
	try {
	    // 拿到当前JavaBean对象的所有属性
	    Field[] fields = clz.getDeclaredFields();
	    // 查询数据库数据
	    ResultSet set = stat.executeQuery(sql);
	    // 根据数据库数据设置值
	    if (set.next()) {
		// 通过反射构建JavaBean对象
		@SuppressWarnings("deprecation")
		T t = clz.newInstance();
		for (int i = 0; i < fields.length; i++) {
		    Field field = fields[i];
		    System.out.println(field.getName());
		    field.setAccessible(true);
		    Object object = set.getObject(field.getName());
		    System.out.println(object);
		    field.set(t, object);
		}
		return t;
	    }

	    return null;
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    System.out.println(e.getMessage());
	}

	return null;
    }

    /**
     * 
     * @Title: executeDQLOpertionGetMoreData 
     * @Description: TODO 通用DQL操作工具方法 查询多条数据
     * @param <T>
     * @param stat
     * @param sql
     * @param clz
     * @return List<T>
     */
    public static <T> List<T> executeDQLOpertionGetMoreData(Statement stat, String sql, Class<T> clz) {
	List<T> list = new ArrayList<T>();
	try {
	    // 拿到当前JavaBean对象的所有属性
	    Field[] fields = clz.getDeclaredFields();
	    // 查询数据库数据
	    ResultSet set = stat.executeQuery(sql);
	    // 根据数据库数据设置值
	    while (set.next()) {
		// 通过反射构建JavaBean对象
		@SuppressWarnings("deprecation")
		T t = clz.newInstance();
		for (int i = 0; i < fields.length; i++) {
		    Field field = fields[i];
		    if (("cart").equals(field.getName())) {
			continue;
		    }
		    field.setAccessible(true);
		    field.set(t, set.getObject(field.getName()));
		}
		list.add(t);
	    }
	    return list;
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return list;
    }

    /**
     * 
     * @Title: closeDQLResources 
     * @Description: TODO 关闭SQL操作类型的有关资源
     * @param conn
     * @param stat
     * @param set  void
     */
    public static void closeDQLResources(Connection conn, Statement stat, ResultSet set) {
	if (set != null) {
	    try {
		set.close();
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	if (stat != null) {
	    try {
		stat.close();
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	if (conn != null) {
	    try {
		conn.close();
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	}
    }

}
