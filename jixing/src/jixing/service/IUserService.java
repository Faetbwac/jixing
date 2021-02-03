package jixing.service;

import java.util.List;

import jixing.dao.main.User;

/**
 * 
 * @Title: IUserService.java 
 * @Package: jixing.service 
 * @ClassName: IUserService 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月18日 下午8:05:24 
 * @version: V6.0   
 */
public interface IUserService {

    /**
     * 
     * @Title: selectAll 
     * @Description: TODO 
     * @return List<User>
     */
    public List<User> selectAll();

    /**
     * 
     * @Title: add 
     * @Description: TODO 
     * @param user
     *            void
     */
    public void add(User user);

    /**
     * 
     * @Title: delete 
     * @Description: TODO 
     * @param name
     *            void
     */
    public void delete(String name);

}
