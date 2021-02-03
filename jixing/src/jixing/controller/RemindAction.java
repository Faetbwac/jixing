package jixing.controller;

import java.util.Calendar;
import java.util.Date;

import jixing.dao.main.Thing;

/**
 * 
 * @Title: RemindAction.java 
 * @Package: jixing.controller 
 * @ClassName: RemindAction 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月25日 上午3:54:12 
 * @version: V6.0   
 */
public class RemindAction {
    Calendar cal_start = Calendar.getInstance();
    Calendar cal_end = Calendar.getInstance();

    /**
     * 
     * @Title: isOut 
     * @Description: TODO 
     * @param thing
     * @return boolean
     */
    public boolean isOut(Thing thing) {
	Date date = new Date();
	Date date_start, date_end;
	cal_start.setTime(thing.getEndingdate());// 赋值
	cal_end.setTime(thing.getEndingdate());// 赋值
	cal_start.add(Calendar.SECOND, -1);
	cal_end.add(Calendar.SECOND, +1);
	date_start = cal_start.getTime();
	date_end = cal_end.getTime();
	if (date.getTime() < date_end.getTime() && date.getTime() > date_start.getTime()) {
	    return true;
	}
	return false;
    }
}
