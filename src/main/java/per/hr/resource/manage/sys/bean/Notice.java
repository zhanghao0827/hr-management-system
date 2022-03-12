package per.hr.resource.manage.sys.bean;


public class Notice {

    private Integer noticeId;
    private String noticeContent;
    private Integer userId;
    private String noticeTime;
    private String noticeTitle;
    private String noticeUser;
    private Integer delState;

    public Notice() {
    }

    public Notice(String noticeContent, String noticeTitle) {
        this.noticeContent = noticeContent;
        this.noticeTitle = noticeTitle;
    }

    public Notice(Integer noticeId, String noticeContent, String noticeTitle) {
        this.noticeId = noticeId;
        this.noticeContent = noticeContent;
        this.noticeTitle = noticeTitle;
    }

    public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeUser() {
        return noticeUser;
    }

    public void setNoticeUser(String noticeUser) {
        this.noticeUser = noticeUser;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeId=" + noticeId +
                ", noticeContent='" + noticeContent + '\'' +
                ", userId=" + userId +
                ", noticeTime='" + noticeTime + '\'' +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeUser='" + noticeUser + '\'' +
                ", delState=" + delState +
                '}';
    }
}
