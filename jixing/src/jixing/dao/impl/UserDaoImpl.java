package jixing.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import jixing.dao.IUserDao;
import jixing.dao.main.User;
import jixing.util.DBUtils;

/**
 * @Title: UserDaoImpl.java 
 * @Package: jixing.dao.impl 
 * @ClassName: UserDaoImpl 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月18日 下午12:58:38 
 * @version: V6.0   
 */
public class UserDaoImpl implements IUserDao {

    Connection conn = null;// 数据库连接对象 Connection
    Statement stmt = null;// 执行sql语句的对象 Statement
    ResultSet rs = null;// 存储查询语句返回的结果集
    List<User> list = null;

    /**
     * 
     * Title: add Description:
     * 
     * @param user
     * @see jixing.dao.IUserDao#add(jixing.dao.main.User)
     */
    @Override
    public void add(User user) {
        try {
            conn = DBUtils.getConnection();
            // 4. 定义sql；
            String sql = "insert into user value ('" + user.getUsername() + "','" + user.getPassword() + "','"
                + user.getName() + "'" + ",'" + user.getSex() + "','" + user.getAge() + "','" + user.getCity() + "')";

            // 5. 获取执行sql语句的对象 Statement；
            stmt = conn.createStatement();

            // 6. 执行sql，接受返回结果；
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtils.closeDQLResources(conn, stmt, rs);
        }
    }

    /**
     * 
     * Title: selectAll Description:
     * 
     * @return
     * @see jixing.dao.IUserDao#selectAll()
     */
    @Override
    public List<User> selectAll() {
        try {
            conn = DBUtils.getConnection();
            // 4. 定义sql；
            String sql = "select * from user";

            // 5. 获取执行sql语句的对象 Statement；
            stmt = DBUtils.getStatement(conn);
            List<User> users = DBUtils.executeDQLOpertionGetMoreData(stmt, sql, User.class);
            return users;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtils.closeDQLResources(conn, stmt, rs);
        }
        return null;
    }

    /**
     * 
     * Title: delete Description:
     * 
     * @param name
     * @see jixing.dao.IUserDao#delete(java.lang.String)
     */
    @Override
    public void delete(String name) {
        // TODO Auto-generated method stub
        try {
            conn = DBUtils.getConnection();
            // 4. 定义sql；
            String sql = "delete from user where name = '" + name + "'";;

            // 5. 获取执行sql语句的对象 Statement；
            stmt = DBUtils.getStatement(conn);
            DBUtils.executeDMLOpertion(stmt, sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtils.closeDQLResources(conn, stmt, rs);
        }
    }
}
