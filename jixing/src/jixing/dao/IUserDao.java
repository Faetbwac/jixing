package jixing.dao;

import java.util.List;

import jixing.dao.main.User;

/**
 * 
 * @Title: IUserDao.java 
 * @Package: jixing.dao 
 * @ClassName: IUserDao 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月18日 下午12:59:11 
 * @version: V6.0   
 */
public interface IUserDao {

    /**
     * 
     * @Title: add 
     * @Description: TODO 
     * @param user 
     * @return void   
     */
    public void add(User user);

    /**
     * 
     * @Title: selectAll 
     * @Description: TODO  @return 
     * @return List<User>   
     */
    public List<User> selectAll();

    /**
     * 
     * @Title: delete 
     * @Description: TODO 
     * @param name 
     * @return void   
     */
    public void delete(String name);

}
