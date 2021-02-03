package jixing.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import jixing.dao.IBrowseDao;
import jixing.dao.main.Thing;
import jixing.util.DBUtils;

/**
 * 
 * @Title: BrowseDaoImpl.java 
 * @Package: jixing.dao.impl 
 * @ClassName: BrowseDaoImpl 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月18日 下午7:58:35 
 * @version: V6.0   
 */
public class BrowseDaoImpl implements IBrowseDao {

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    List<Thing> list = null;
    Calendar cal = Calendar.getInstance();

    /**
     * 
     * Title: selectAll Description:
     * 
     * @param userid
     * @return
     * @see jixing.dao.IBrowseDao#selectAll(java.lang.String)
     */
    @Override
    public List<Thing> selectAll(String userid) {
        try {
            conn = DBUtils.getConnection();
            // 4. 定义sql；
            String sql = "select * from thing where userid = '" + userid + "'";

            // 5. 获取执行sql语句的对象 Statement；
            stmt = DBUtils.getStatement(conn);
            list = DBUtils.executeDQLOpertionGetMoreData(stmt, sql, Thing.class);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtils.closeDQLResources(conn, stmt, rs);
        }
        Iterator<Thing> it = list.iterator();
        while (it.hasNext()) {
            Thing value = it.next();
            cal.setTime(value.getEndingdate());
            cal.add(Calendar.HOUR, -8);
            value.setEndingdate(cal.getTime());
        }
        return list;
    }

    /**
     * 
     * Title: findThingById Description:
     * 
     * @param id
     * @return
     * @see jixing.dao.IBrowseDao#findThingById(int)
     */
    @Override
    public Thing findThingById(int id) {
        try {
            conn = DBUtils.getConnection();
            // 4. 定义sql；
            String sql = "select * from thing where id =  '" + id + "'";

            // 5. 获取执行sql语句的对象 Statement；
            stmt = DBUtils.getStatement(conn);
            Thing thing = DBUtils.executeDQLOpertionGetSingleData(stmt, sql, Thing.class);
            cal.setTime(thing.getEndingdate());
            cal.add(Calendar.HOUR, -8);
            thing.setEndingdate(cal.getTime());
            return thing;
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
     * Title: updateThing Description:
     * 
     * @param thing
     * @see jixing.dao.IBrowseDao#updateThing(jixing.dao.main.Thing)
     */
    @Override
    public void updateThing(Thing thing) {
        try {
            conn = DBUtils.getConnection();
            // 4. 定义sql；
            String sql = "update thing set Category = '" + thing.getCategory() + "' , Endingdate = '"
                + new Timestamp(thing.getEndingdate().getTime()) + "' , name = '" + thing.getName() + "'   where id = '"
                + thing.getId() + "'";
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

    /**
     * 
     * Title: addThing Description:
     * 
     * @param thing
     * @see jixing.dao.IBrowseDao#addThing(jixing.dao.main.Thing)
     */
    @Override
    public void addThing(Thing thing) {
        // TODO Auto-generated method stub
        try {
            conn = DBUtils.getConnection();
            // 4. 定义sql；
            String sql =
                "insert into thing value ('" + thing.getId() + "','" + thing.getName() + "','" + thing.getCategory()
                    + "','" + new Timestamp(thing.getEndingdate().getTime()) + "','" + thing.getUserId() + "')";
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

    /**
     * 
     * Title: deleteThing Description:
     * 
     * @param id
     * @see jixing.dao.IBrowseDao#deleteThing(int)
     */
    @Override
    public void deleteThing(int id) {
        // TODO Auto-generated method stub
        try {
            conn = DBUtils.getConnection();
            // 4. 定义sql；
            String sql = "delete from thing where id = '" + id + "'";
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
