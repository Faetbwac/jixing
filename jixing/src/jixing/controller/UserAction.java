package jixing.controller;

import java.util.List;

import jixing.dao.main.User;
import jixing.service.IUserService;
import jixing.service.impl.UserServiceImpl;

/**
 * 
 * @Title: UserAction.java 
 * @Package: jixing.controller 
 * @ClassName: UserAction 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月18日 下午1:40:02 
 * @version: V6.0   
 */
public class UserAction {
    private IUserService userService = new UserServiceImpl();

    /**
     * 
     * @Title: login 
     * @Description: TODO 
     * @param username
     * @param password
     * @return User
     */
    public User login(String username, String password) {
	// 获取用户列表
	List<User> users = userService.selectAll();

	// 循环判断用户输入的账号和密码在数据集合中是否存在。
	for (int i = 0; i < users.size(); i++) {
	    User user = users.get(i);
	    if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
		return user;
	    }
	}
	// 无对应用户数据返回null
	return null;
    }

}
