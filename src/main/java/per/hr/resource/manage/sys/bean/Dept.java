package per.hr.resource.manage.sys.bean;

public class Dept {

    private Integer did;
    private String dName;
    private String dDes;
    private Integer delState;

    public Dept() {
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdDes() {
        return dDes;
    }

    public void setdDes(String dDes) {
        this.dDes = dDes;
    }

    public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }
}
