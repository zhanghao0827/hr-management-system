package per.hr.resource.manage.sys.service.impl;

import per.hr.resource.manage.sys.bean.Notice;
import per.hr.resource.manage.sys.bean.User;
import per.hr.resource.manage.sys.dao.NoticeDao;
import per.hr.resource.manage.sys.dao.impl.NoticeDaoImpl;
import per.hr.resource.manage.sys.service.NoticeService;

import java.util.List;

public class NoticeServiceImpl implements NoticeService {

    NoticeDao noticeDao = new NoticeDaoImpl();

    @Override
    public long getTotalCount(Notice notice) {
        return noticeDao.getTotalCount(notice);
    }

    @Override
    public List<Notice> getNoticesWithPage(Notice notice, Integer pageIndex, Integer pageSize) {
        return noticeDao.getNoticesWithPage(notice, pageIndex, pageSize);
    }

    @Override
    public boolean addNotice(Notice notice, User user) {
        return noticeDao.addNotice(notice, user);
    }

    @Override
    public boolean deleteNoticeById(int id) {
        return noticeDao.deleteNoticeById(id);
    }

    @Override
    public Notice getNoticeById(int id) {
        return noticeDao.getNoticeById(id);
    }

    @Override
    public boolean updateNoticeById(Notice notice) {
        return noticeDao.updateNoticeById(notice);
    }


}
