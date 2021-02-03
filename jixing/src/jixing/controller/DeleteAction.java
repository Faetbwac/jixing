package jixing.controller;

import java.util.Date;

import jixing.dao.main.Thing;

/**
 * 
 * @Title: DeleteAction.java 
 * @Package: jixing.controller 
 * @ClassName: DeleteAction 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月18日 下午1:40:56 
 * @version: V6.0   
 */
public class DeleteAction {
    static BrowseAction browseAction = new BrowseAction();

    /**
     * 
     * @Title: isOut 
     * @Description: TODO 
     * @param thing
     * @return boolean
     */
    public boolean isOut(Thing thing) {
        Date date = new Date();
        // System.out.println(date);
        if (date.after(thing.getEndingdate())) {
            browseAction.deleteThing(thing.getId());
            return true;
        }
        return false;
    }
}
