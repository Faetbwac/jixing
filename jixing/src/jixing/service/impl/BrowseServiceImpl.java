package jixing.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jixing.dao.IBrowseDao;
import jixing.dao.impl.BrowseDaoImpl;
import jixing.dao.main.Thing;
import jixing.service.IBrowseService;

/**
 * 
 * @Title: BrowseServiceImpl.java 
 * @Package: jixing.service.impl 
 * @ClassName: BrowseServiceImpl 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月18日 下午8:06:13 
 * @version: V6.0   
 */
public class BrowseServiceImpl implements IBrowseService {
    // 构建Dao对象，调用方法：
    IBrowseDao browseDao = new BrowseDaoImpl();

    /**
     * 
     * Title: findAll Description:
     * 
     * @param userid
     * @return
     * @see jixing.service.IBrowseService#findAll(java.lang.String)
     */
    @Override
    public List<Thing> findAll(String userid) {
        List<Thing> thing = browseDao.selectAll(userid);
        sort(thing);
        return thing;
    }

    /**
     * 
     * Title: findThingById Description:
     * 
     * @param id
     * @return
     * @see jixing.service.IBrowseService#findThingById(int)
     */
    @Override
    public Thing findThingById(int id) {
        Thing thing = browseDao.findThingById(id);
        // 2. 返回结果
        return thing;
    }

    /**
     * 
     * Title: updateThing Description:
     * 
     * @param thing
     * @see jixing.service.IBrowseService#updateThing(jixing.dao.main.Thing)
     */
    @Override
    public void updateThing(Thing thing) {
        browseDao.updateThing(thing);
    }

    /**
     * 
     * @Title: sort 
     * @Description: TODO 
     * @param thing
     *            void
     */
    public static void sort(List<Thing> thing) {
        Collections.sort(thing, new Comparator<Thing>() {
            @Override
            public int compare(Thing t1, Thing t2) {
                if (t1.getEndingdate().compareTo(t2.getEndingdate()) > 0) {
                    return -1;
                } else if (t1.getEndingdate().compareTo(t2.getEndingdate()) < 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

    /**
     * 
     * Title: addThing Description:
     * 
     * @param thing
     * @see jixing.service.IBrowseService#addThing(jixing.dao.main.Thing)
     */
    @Override
    public void addThing(Thing thing) {
        // TODO Auto-generated method stub
        browseDao.addThing(thing);
    }

    /**
     * 
     * Title: deleteThing Description:
     * 
     * @param id
     * @see jixing.service.IBrowseService#deleteThing(int)
     */
    @Override
    public void deleteThing(int id) {
        // TODO Auto-generated method stub
        browseDao.deleteThing(id);
    }
}
