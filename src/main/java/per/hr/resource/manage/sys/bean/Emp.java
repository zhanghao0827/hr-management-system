package per.hr.resource.manage.sys.bean;


public class Emp {

    private Integer empId;
    private String empName;
    private String gender;
    private String telNum;
    private String email;
    private Integer staffId;
    private String empEdu;
    private String cardNum;
    private Integer dId;
    private String empAddress;
    private String empCreateTime;
    private String remark;
    private String hobby;
    private String political;
    private String qq;
    private String ems;
    private String birth;
    private String major;
    private String volk;
    private String phone;
    private Integer delState;

    private String staffName;
    private String dName;


    public Emp() {
    }

    public Emp(String empName, String gender, Integer staffId, String cardNum, Integer dId, String phone) {
        this.empName = empName;
        this.gender = gender;
        this.staffId = staffId;
        this.cardNum = cardNum;
        this.dId = dId;
        this.phone = phone;
    }

    public Emp(String empName, String gender, String telNum, String email, Integer staffId, String empEdu, String cardNum, Integer dId, String empAddress, String remark, String hobby, String political, String qq, String ems, String birth, String major, String volk, String phone) {
        this.empName = empName;
        this.gender = gender;
        this.telNum = telNum;
        this.email = email;
        this.staffId = staffId;

        this.empEdu = empEdu;
        this.cardNum = cardNum;
        this.dId = dId;
        this.empAddress = empAddress;
        this.remark = remark;

        this.hobby = hobby;
        this.political = political;
        this.qq = qq;
        this.ems = ems;
        this.birth = birth;

        this.major = major;
        this.volk = volk;
        this.phone = phone;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getEmpEdu() {
        return empEdu;
    }

    public void setEmpEdu(String empEdu) {
        this.empEdu = empEdu;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpCreateTime() {
        return empCreateTime;
    }

    public void setEmpCreateTime(String empCreateTime) {
        this.empCreateTime = empCreateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEms() {
        return ems;
    }

    public void setEms(String ems) {
        this.ems = ems;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getVolk() {
        return volk;
    }

    public void setVolk(String volk) {
        this.volk = volk;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", gender='" + gender + '\'' +
                ", telNum='" + telNum + '\'' +
                ", email='" + email + '\'' +
                ", staffId=" + staffId +
                ", empEdu='" + empEdu + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", dId=" + dId +
                ", empAddress='" + empAddress + '\'' +
                ", empCreateTime='" + empCreateTime + '\'' +
                ", remark='" + remark + '\'' +
                ", hobby='" + hobby + '\'' +
                ", political='" + political + '\'' +
                ", qq='" + qq + '\'' +
                ", ems='" + ems + '\'' +
                ", birth='" + birth + '\'' +
                ", major='" + major + '\'' +
                ", volk='" + volk + '\'' +
                ", phone='" + phone + '\'' +
                ", delState=" + delState +
                ", staffName='" + staffName + '\'' +
                ", dName='" + dName + '\'' +
                '}';
    }
}
