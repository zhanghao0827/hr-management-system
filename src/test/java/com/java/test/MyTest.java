package com.java.test;

import org.junit.Test;
import per.hr.resource.manage.sys.bean.Emp;
import per.hr.resource.manage.sys.bean.Notice;
import per.hr.resource.manage.sys.bean.User;
import per.hr.resource.manage.sys.dao.EmpDao;
import per.hr.resource.manage.sys.dao.impl.EmpDaoImpl;
import per.hr.resource.manage.sys.service.NoticeService;
import per.hr.resource.manage.sys.service.UserService;
import per.hr.resource.manage.sys.service.impl.NoticeServiceImpl;
import per.hr.resource.manage.sys.service.impl.UserServiceImpl;
import per.hr.resource.manage.sys.utils.StringUtils;

import java.util.List;

public class MyTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void test() {
        User user = new User();
        user.setUserLoginName("test");
        user.setUserName("test");
        user.setUserPwd("test");
        user.setState(1);
        boolean b = userService.addUser(user);
        System.out.println("b = " + b);
    }

    @Test
    public void test2() {
        String downFileName = "89310f1b-c584-4d8a-b42a-8990447fe6c0.jpg";
        //获取文件名后缀
        String suffix = downFileName.substring(downFileName.lastIndexOf("."), downFileName.length());
        System.out.println("suffix = " + suffix);
    }

    @Test
    public void test3() {
        String disposition = "disposition = form-data; name=\"file\"; filename=\"计划书.doc\"";
        //上传文件后缀名
        String suffix = disposition.substring(disposition.lastIndexOf("."), disposition.length() - 1);
        System.out.println("suffix = " + suffix);
    }

    @Test
    public void test4() {
        String s1 = "disposition = form-data; name=\"file\"; filename=\"计划书.doc\"";
        String s2 = "disposition = form-data; name='file'; filename='计划书.doc'";
        System.out.println(s1.length());
        System.out.println(s2.length());
    }

    @Test
    public void test5() {
        boolean b = StringUtils.isDownload("disposition = form-data; name=\"file\"; filename=\"\"");
        System.out.println(b);
    }

    NoticeService noticeService = new NoticeServiceImpl();

    @Test
    public void test6() {
        Notice notice = new Notice();
        notice.setNoticeContent("天气");
        notice.setNoticeTitle("高");
        long totalCount = noticeService.getTotalCount(notice);
        System.out.println(totalCount);
    }

    @Test
    public void test7() {
        Notice notice = new Notice();
        notice.setNoticeContent("天气");
        List<Notice> notices = noticeService.getNoticesWithPage(notice, 1, 5);
        for (Notice not : notices) {
            System.out.println(not);
        }
    }

    EmpDao empDao = new EmpDaoImpl();

    @Test
    public void test8(){
        Emp emp = new Emp();
        emp.setEmpName("张");
        emp.setGender("女");
        long totalCount = empDao.getTotalCount(emp);
        System.out.println("totalCount = " + totalCount);
    }
    
    @Test
    public void test9(){
        Emp emp = new Emp();
        emp.setEmpName("张");
        emp.setGender("女");

        List<Emp> emps = empDao.getEmpsWithPage(emp, 1, 5);
        for (Emp e : emps) {
            System.out.println(e);
        }
    }
}
