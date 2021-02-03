package jixing.dao.main;

import java.util.Date;

/**
 * 
 * @Title: Thing.java 
 * @Package: jixing.dao.main 
 * @ClassName: Thing 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月18日 下午8:01:15 
 * @version: V6.0   
 */
public class Thing {
    private String userId;// 使用者id
    private int id; // 事件id
    private String name; // 事件名称
    private String category; // 事件分类
    private Date endingdate;// 事件截至时间

    /**
     * 
     * @Title: getId 
     * @Description: TODO 
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @Title: setId 
     * @Description: TODO 
     * @param id
     *            void
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @Title: getName 
     * @Description: TODO 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @Title: setName 
     * @Description: TODO 
     * @param name
     *            void
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @Title: getCategory 
     * @Description: TODO 
     * @return String
     */
    public String getCategory() {
        return category;
    }

    /**
     * 
     * @Title: setCategory 
     * @Description: TODO 
     * @param category
     *            void
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 
     * @Title: getEndingdate 
     * @Description: TODO 
     * @return Date
     */
    public Date getEndingdate() {
        return endingdate;
    }

    /**
     * 
     * @Title: setEndingdate 
     * @Description: TODO 
     * @param endingdate
     *            void
     */
    public void setEndingdate(Date endingdate) {
        this.endingdate = endingdate;
    }

    /**
     * 
     * @Title: getUserId 
     * @Description: TODO 
     * @return String
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 
     * @Title: setUserId 
     * @Description: TODO 
     * @param userId
     *            void
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 
     * Title: Description:
     * 
     * @param userId
     * @param id
     * @param name
     * @param category
     * @param endingdate 
     */
    public Thing(String userId, int id, String name, String category, Date endingdate) {
        super();
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.category = category;
        this.endingdate = endingdate;
    }

    /**
     * 
     * Title: Description:  
     */
    public Thing() {
        super();
    }

    /**
     * 
     * @Title: compareTo 
     * @Description: TODO 
     * @param o
     * @return int
     */
    public int compareTo(Thing o) {
        if (this.getEndingdate().before(o.getEndingdate())) {
            return -1;
        } else if (this.getEndingdate().after(o.getEndingdate())) {
            return 1;
        } else {
            return 0;
        }
    }

}