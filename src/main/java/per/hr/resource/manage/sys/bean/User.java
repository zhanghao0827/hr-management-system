package per.hr.resource.manage.sys.bean;

public class User {

    private Integer userId;
    private String userLoginName;
    private String userPwd;
    private String userName;
    private Integer state = -1; //0禁用，1启用，-1全查
    private String createTime;
    private Integer delState;

    public User() {
    }

    public User(String userName, Integer state) {
        this.userName = userName;
        this.state = state;
    }

    public User(String userLoginName, String userPwd) {
        this.userLoginName = userLoginName;
        this.userPwd = userPwd;
    }

    public User(Integer userId, String userLoginName, String userPwd, String userName, Integer state) {
        this.userId = userId;
        this.userLoginName = userLoginName;
        this.userPwd = userPwd;
        this.userName = userName;
        this.state = state;
    }

    public User(String userLoginName, String userPwd, String userName, Integer state) {
        this.userLoginName = userLoginName;
        this.userPwd = userPwd;
        this.userName = userName;
        this.state = state;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userLoginName='" + userLoginName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userName='" + userName + '\'' +
                ", state=" + state +
                ", createTime='" + createTime + '\'' +
                ", delState=" + delState +
                '}';
    }
}
