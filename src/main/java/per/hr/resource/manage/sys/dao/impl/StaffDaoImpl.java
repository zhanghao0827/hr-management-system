package per.hr.resource.manage.sys.dao.impl;

import per.hr.resource.manage.sys.bean.Staff;
import per.hr.resource.manage.sys.dao.StaffDao;
import per.hr.resource.manage.sys.utils.JDBCUtils;

import java.util.List;

public class StaffDaoImpl implements StaffDao {

    @Override
    public List<Staff> getStaffs() {
        return JDBCUtils.queryList("select * from t_staff where delState=0", Staff.class);
    }
}
