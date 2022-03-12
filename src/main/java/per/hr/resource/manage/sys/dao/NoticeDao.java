package per.hr.resource.manage.sys.dao;

import per.hr.resource.manage.sys.bean.Notice;
import per.hr.resource.manage.sys.bean.User;

import java.util.List;

public interface NoticeDao {
    long getTotalCount(Notice notice);

    List<Notice> getNoticesWithPage(Notice notice, Integer pageIndex, Integer pageSize);

    boolean addNotice(Notice notice, User user);

    boolean deleteNoticeById(int id);

    Notice getNoticeById(int id);

    boolean updateNoticeById(Notice notice);

}
