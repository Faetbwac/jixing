package jixing.dao;

import java.util.List;

import jixing.dao.main.Thing;

/**
 * 
 * @Title: IBrowseDao.java 
 * @Package: jixing.dao 
 * @ClassName: IBrowseDao 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月18日 下午1:18:56 
 * @version: V6.0   
 */
public interface IBrowseDao {
    /**
     * 
     * @Title: selectAll 
     * @Description: TODO 
     * @param userid @return 
     * @return List<Thing>   
     */
    public List<Thing> selectAll(String userid);

    /**
     * 
     * @Title: findThingById 
     * @Description: TODO 
     * @param id @return 
     * @return Thing   
     */
    public Thing findThingById(int id);

    /**
     * 
     * @Title: updateThing 
     * @Description: TODO 
     * @param thing 
     * @return void   
     */
    public void updateThing(Thing thing);

    /**
     * 
     * @Title: addThing 
     * @Description: TODO 
     * @param thing 
     * @return void   
     */
    public void addThing(Thing thing);

    /**
     * 
     * @Title: deleteThing 
     * @Description: TODO 
     * @param id 
     * @return void   
     */
    public void deleteThing(int id);

}
