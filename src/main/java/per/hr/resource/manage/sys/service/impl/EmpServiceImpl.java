package per.hr.resource.manage.sys.service.impl;

import per.hr.resource.manage.sys.bean.Emp;
import per.hr.resource.manage.sys.dao.EmpDao;
import per.hr.resource.manage.sys.dao.impl.EmpDaoImpl;
import per.hr.resource.manage.sys.service.EmpService;

import java.util.List;

public class EmpServiceImpl implements EmpService {

    EmpDao empDao = new EmpDaoImpl();

    @Override
    public boolean addEmp(Emp emp) {
        return empDao.addEmp(emp);
    }

    @Override
    public Emp getEmpById(Integer id) {
        return empDao.getEmpById(id);
    }

    @Override
    public long getTotalCount(Emp emp) {
        return empDao.getTotalCount(emp);
    }

    @Override
    public List<Emp> getEmpsWithPage(Emp emp, int pageIndex, int pageSize) {
        return empDao.getEmpsWithPage(emp, pageIndex, pageSize);
    }
}
