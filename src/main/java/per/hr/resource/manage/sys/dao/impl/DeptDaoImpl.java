package per.hr.resource.manage.sys.dao.impl;

import per.hr.resource.manage.sys.bean.Dept;
import per.hr.resource.manage.sys.dao.DeptDao;
import per.hr.resource.manage.sys.utils.JDBCUtils;

import java.util.List;

public class DeptDaoImpl implements DeptDao {

    @Override
    public List<Dept> getDepts() {
        return JDBCUtils.queryList("select * from t_dept where delState=0", Dept.class);
    }
}
