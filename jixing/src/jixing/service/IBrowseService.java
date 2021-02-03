package jixing.service;

import java.util.List;

import jixing.dao.main.Thing;

/**
 * 
 * @Title: IBrowseService.java 
 * @Package: jixing.service 
 * @ClassName: IBrowseService 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月18日 下午8:04:46 
 * @version: V6.0   
 */
public interface IBrowseService {

    /**
     * 
     * @Title: findAll 
     * @Description: TODO 
     * @param userid
     * @return List<Thing>
     */
    public List<Thing> findAll(String userid);

    /**
     * 
     * @Title: findThingById 
     * @Description: TODO 
     * @param id
     * @return Thing
     */
    public Thing findThingById(int id);

    /**
     * 
     * @Title: updateThing 
     * @Description: TODO 
     * @param thing
     *            void
     */
    public void updateThing(Thing thing);

    /**
     * 
     * @Title: addThing 
     * @Description: TODO 
     * @param thing
     *            void
     */
    public void addThing(Thing thing);

    /**
     * 
     * @Title: deleteThing 
     * @Description: TODO 
     * @param id
     *            void
     */
    public void deleteThing(int id);
}
