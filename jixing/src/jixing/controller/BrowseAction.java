package jixing.controller;

import java.util.ArrayList;
import java.util.List;

import jixing.dao.main.Thing;
import jixing.dao.main.User;
import jixing.service.IBrowseService;
import jixing.service.impl.BrowseServiceImpl;

/**
 * 
 * @Title: BrowseAction.java 
 * @Package: jixing.controller 
 * @ClassName: BrowseAction 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月18日 下午7:53:15 
 * @version: V6.0   
 */
public class BrowseAction {
    // 构建service对象，调用方法：
    IBrowseService browseService = new BrowseServiceImpl();

    /**
     * 
     * @Title: showAll 
     * @Description: TODO 
     * @param userid
     * @return List<Thing>
     */
    public List<Thing> showAll(String userid) {
	if (userid == null) {
	    userid = "游客";
	}

	List<Thing> thing = browseService.findAll(userid);
	// 2. 返回结果
	return thing;
    }

    /**
     * 
     * @Title: findThingById 
     * @Description: TODO 
     * @param id
     * @return Thing
     */
    public Thing findThingById(int id) {
	Thing thing = browseService.findThingById(id);
	// 2. 返回结果
	return thing;
    }

    /**
     * 
     * @Title: updateThing 
     * @Description: TODO 
     * @param thing void
     */
    public void updateThing(Thing thing) {
	browseService.updateThing(thing);
    }

    /**
     * 
     * @Title: addThing 
     * @Description: TODO 
     * @param thing void
     */
    public void addThing(Thing thing) {
	// TODO Auto-generated method stub
	browseService.addThing(thing);
    }

    /**
     * 
     * @Title: deleteThing 
     * @Description: TODO 
     * @param id void
     */
    public void deleteThing(int id) {
	// TODO Auto-generated method stub
	browseService.deleteThing(id);
    }

    /**
     * 
     * @Title: queryThing 
     * @Description: TODO 
     * @param name
     * @param category
     * @param user
     * @return List<Thing>
     */
    public List<Thing> queryThing(String name, String category, User user) {

	List<Thing> thing = new ArrayList<>(); // 定义查询列表
	String thingName = name;
	if (thingName == null) {
	    thingName = "";
	}
	List<Thing> thingList;
	if (user == null) {
	    thingList = this.showAll(null);
	} else {
	    thingList = this.showAll(user.getName()); // 获取所有的事件
	}

	for (Thing things : thingList) { // 遍历所有事件
	    if ((things.getName().contains(thingName)) // 找到符合条件的
		    && (category == null || things.getCategory().equals(category))) {
		thing.add(things); // 添加到集合中
	    }
	}
	// 返回结果
	return thing;

    }

}
