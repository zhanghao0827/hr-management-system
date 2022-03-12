package per.hr.resource.manage.sys.dao.impl;

import per.hr.resource.manage.sys.bean.Emp;
import per.hr.resource.manage.sys.dao.EmpDao;
import per.hr.resource.manage.sys.utils.JDBCUtils;

import java.util.List;

public class EmpDaoImpl implements EmpDao {

    @Override
    public boolean addEmp(Emp emp) {
        return JDBCUtils.update("insert into t_emp values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)",
                emp.getEmpName(),
                emp.getGender(),
                emp.getTelNum(),
                emp.getEmail(),
                emp.getStaffId(),
                emp.getEmpEdu(),
                emp.getCardNum(),
                emp.getdId(),
                emp.getEmpAddress(),

                emp.getRemark(),
                emp.getHobby(),
                emp.getPolitical(),
                emp.getQq(),
                emp.getEms(),
                emp.getBirth(),
                emp.getMajor(),
                emp.getVolk(),
                emp.getPhone());
    }

    @Override
    public Emp getEmpById(Integer id) {
        return JDBCUtils.queryOne("select * from t_emp where empId=?", Emp.class, id);
    }

    @Override
    public long getTotalCount(Emp emp) {
        StringBuffer sb = new StringBuffer("select count(empId) from t_emp where delState=0");
        Integer staffId = emp.getStaffId();//-1
        String empName = emp.getEmpName();
        String cardNum = emp.getCardNum();
        String gender = emp.getGender();//-1 || null
        String phone = emp.getPhone();
        Integer did = emp.getdId();//-1
        if (staffId != null && staffId != -1) {
            sb.append(" and t_emp.staffId=" + staffId);
        }
        if (empName != null && !"".equals(empName)) {
            sb.append(" and t_emp.empName like '%" + empName + "%'");
        }
        if (cardNum != null && !"".equals(cardNum)) {
            sb.append(" and t_emp.cardNum like '%" + cardNum + "%'");
        }
        if (gender != null && !"-1".equals(gender)) {
            sb.append(" and t_emp.gender='" + gender + "'");
        }
        if (phone != null && !"".equals(phone)) {
            sb.append(" and t_emp.phone like '%" + phone + "%'");
        }
        if (did != null && did != -1) {
            sb.append(" and t_emp.did=" + did);
        }
        String sql = sb.toString();
        return (long) JDBCUtils.querySingleValue(sql);
    }

    @Override
    public List<Emp> getEmpsWithPage(Emp emp, int pageIndex, int pageSize) {
        StringBuffer sb = new StringBuffer("select t_emp.*,t_staff.staffName,t_dept.dName from t_emp inner join t_staff on t_emp.staffId=t_staff.staffId inner join t_dept on t_emp.did=t_dept.did where t_emp.delState=0");
        Integer staffId = emp.getStaffId();//-1
        String empName = emp.getEmpName();
        String cardNum = emp.getCardNum();
        String gender = emp.getGender();//-1 || null
        String phone = emp.getPhone();
        Integer did = emp.getdId();//-1
        if (staffId != null && staffId != -1) {
            sb.append(" and t_emp.staffId=" + staffId);
        }
        if (empName != null && !"".equals(empName)) {
            sb.append(" and t_emp.empName like '%" + empName + "%'");
        }
        if (cardNum != null && !"".equals(cardNum)) {
            sb.append(" and t_emp.cardNum like '%" + cardNum + "%'");
        }
        if (gender != null && !"-1".equals(gender)) {
            sb.append(" and t_emp.gender='" + gender + "'");
        }
        if (phone != null && !"".equals(phone)) {
            sb.append(" and t_emp.phone like '%" + phone + "%'");
        }
        if (did != null && did != -1) {
            sb.append(" and t_emp.did=" + did);
        }
        sb.append(" limit ?,?");
        String sql = sb.toString();
        System.out.println("sql = " + sql);
        return JDBCUtils.queryList(sql, Emp.class, (pageIndex - 1) * pageSize, pageSize);
    }
}
