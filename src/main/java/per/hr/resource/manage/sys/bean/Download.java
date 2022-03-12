package per.hr.resource.manage.sys.bean;


public class Download {

    private Integer downloadId;
    private String downloadTittle;
    private String downloadCreateTime;
    private Integer userId;
    private String downloadDes;
    private String downPath;
    private Integer delState;

    private String userName;

    public Download() {
    }

    public Download(String downloadTittle, Integer userId, String downloadDes, String downPath) {
        this.downloadTittle = downloadTittle;
        this.userId = userId;
        this.downloadDes = downloadDes;
        this.downPath = downPath;
    }

    public Download(Integer downloadId, String downloadTittle, String downloadDes, String downPath) {
        this.downloadId = downloadId;
        this.downloadTittle = downloadTittle;
        this.downloadDes = downloadDes;
        this.downPath = downPath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(Integer downloadId) {
        this.downloadId = downloadId;
    }

    public String getDownloadTittle() {
        return downloadTittle;
    }

    public void setDownloadTittle(String downloadTittle) {
        this.downloadTittle = downloadTittle;
    }

    public String getDownloadCreateTime() {
        return downloadCreateTime;
    }

    public void setDownloadCreateTime(String downloadCreateTime) {
        this.downloadCreateTime = downloadCreateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDownloadDes() {
        return downloadDes;
    }

    public void setDownloadDes(String downloadDes) {
        this.downloadDes = downloadDes;
    }

    public String getDownPath() {
        return downPath;
    }

    public void setDownPath(String downPath) {
        this.downPath = downPath;
    }

    public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }

    @Override
    public String toString() {
        return "Download{" +
                "downloadId=" + downloadId +
                ", downloadTittle='" + downloadTittle + '\'' +
                ", downloadCreateTime='" + downloadCreateTime + '\'' +
                ", userId=" + userId +
                ", downloadDes='" + downloadDes + '\'' +
                ", downPath='" + downPath + '\'' +
                ", delState=" + delState +
                ", userName='" + userName + '\'' +
                '}';
    }
}
