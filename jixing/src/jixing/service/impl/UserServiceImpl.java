package jixing.service.impl;

import java.util.List;

import jixing.dao.IUserDao;
import jixing.dao.impl.UserDaoImpl;
import jixing.dao.main.User;
import jixing.service.IUserService;

/**
 * 
 * @Title: UserServiceImpl.java 
 * @Package: jixing.service.impl 
 * @ClassName: UserServiceImpl 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月18日 下午8:06:45 
 * @version: V6.0   
 */
public class UserServiceImpl implements IUserService {

    IUserDao userDao = new UserDaoImpl();

    /**
     * 
     * Title: selectAll Description:
     * 
     * @return
     * @see jixing.service.IUserService#selectAll()
     */
    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    /**
     * 
     * Title: add Description:
     * 
     * @param user
     * @see jixing.service.IUserService#add(jixing.dao.main.User)
     */
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    /**
     * 
     * Title: delete Description:
     * 
     * @param name
     * @see jixing.service.IUserService#delete(java.lang.String)
     */
    @Override
    public void delete(String name) {
        // TODO Auto-generated method stub
        userDao.delete(name);
    }
}
