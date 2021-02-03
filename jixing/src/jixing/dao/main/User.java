package jixing.dao.main;

/**
 * 
 * @Title: User.java 
 * @Package: jixing.dao.main 
 * @ClassName: User 
 * @Description: TODO 
 * @author: 石一歌   
 * @date: 2020年12月18日 下午8:03:26 
 * @version: V6.0   
 */
public class User {

    private String username; // 账号
    private String password; // 密码
    private String name; // 真实姓名
    private String sex; // 性别
    private int age; // 年龄
    private String city; // 城市

    /**
     * 
     * Title: Description:
     * 
     * @param username
     * @param password
     * @param name
     * @param sex
     * @param age
     * @param city 
     */
    public User(String username, String password, String name, String sex, int age, String city) {
        super();
        this.username = username;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.city = city;
    }

    /**
     * 
     * Title: Description:  
     */
    public User() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @Title: getUsername 
     * @Description: TODO 
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @Title: setUsername 
     * @Description: TODO 
     * @param username
     *            void
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * @Title: getPassword 
     * @Description: TODO 
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @Title: setPassword 
     * @Description: TODO 
     * @param password
     *            void
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @Title: getSex 
     * @Description: TODO 
     * @return String
     */
    public String getSex() {
        return sex;
    }

    /**
     * 
     * @Title: setSex 
     * @Description: TODO 
     * @param sex
     *            void
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 
     * @Title: getAge 
     * @Description: TODO 
     * @return int
     */
    public int getAge() {
        return age;
    }

    /**
     * 
     * @Title: setAge 
     * @Description: TODO 
     * @param age
     *            void
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 
     * @Title: getCity 
     * @Description: TODO 
     * @return String
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @Title: setCity 
     * @Description: TODO 
     * @param city
     *            void
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * Title: toString Description:
     * 
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", name='" + name + '\''
            + ", sex='" + sex + '\'' + ", age=" + age + ", city='" + city + '\'' + '}';
    }
}