package per.hr.resource.manage.sys.service.impl;

import per.hr.resource.manage.sys.bean.Dept;
import per.hr.resource.manage.sys.dao.DeptDao;
import per.hr.resource.manage.sys.dao.impl.DeptDaoImpl;
import per.hr.resource.manage.sys.service.DeptService;

import java.util.List;

public class DeptServiceImpl implements DeptService {

    DeptDao deptDao = new DeptDaoImpl();

    @Override
    public List<Dept> getDepts() {
        return deptDao.getDepts();
    }

}
