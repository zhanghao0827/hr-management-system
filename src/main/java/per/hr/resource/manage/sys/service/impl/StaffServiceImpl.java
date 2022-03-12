package per.hr.resource.manage.sys.service.impl;

import per.hr.resource.manage.sys.bean.Staff;
import per.hr.resource.manage.sys.dao.StaffDao;
import per.hr.resource.manage.sys.dao.impl.StaffDaoImpl;
import per.hr.resource.manage.sys.service.StaffService;

import java.util.List;

public class StaffServiceImpl implements StaffService {

    StaffDao staffDao = new StaffDaoImpl();


    @Override
    public List<Staff> getStaffs() {
        return staffDao.getStaffs();
    }
}
