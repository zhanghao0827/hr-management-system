package per.hr.resource.manage.sys.service;

import per.hr.resource.manage.sys.bean.Emp;

import java.util.List;

public interface EmpService {

    boolean addEmp(Emp emp);

    Emp getEmpById(Integer id);

    long getTotalCount(Emp emp);

    List<Emp> getEmpsWithPage(Emp emp, int pageIndex, int pageSize);
}
