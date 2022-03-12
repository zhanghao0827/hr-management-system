package per.hr.resource.manage.sys.dao.impl;

import per.hr.resource.manage.sys.bean.Notice;
import per.hr.resource.manage.sys.bean.User;
import per.hr.resource.manage.sys.dao.NoticeDao;
import per.hr.resource.manage.sys.utils.JDBCUtils;

import java.util.List;

public class NoticeDaoImpl implements NoticeDao {

    @Override
    public long getTotalCount(Notice notice) {
        String noticeTitle = notice.getNoticeTitle();
        String noticeContent = notice.getNoticeContent();
        StringBuffer sb = new StringBuffer("select count(noticeId) from t_notice where delState=0");
        if (noticeTitle != null && !"".equals(noticeTitle)) {
            sb.append(" and noticeTitle like '%" + noticeTitle + "%'");
        }
        if (noticeContent != null && !"".equals(noticeContent)) {
            sb.append(" and noticeContent like '%" + noticeContent + "%'");
        }
        return (long) JDBCUtils.querySingleValue(sb.toString());
    }

    @Override
    public List<Notice> getNoticesWithPage(Notice notice, Integer pageIndex, Integer pageSize) {
        String noticeTitle = notice.getNoticeTitle();
        String noticeContent = notice.getNoticeContent();
        StringBuffer sb = new StringBuffer("select * from t_notice where delState=0");
        if (noticeTitle != null && !"".equals(noticeTitle)) {
            sb.append(" and noticeTitle like '%" + noticeTitle + "%'");
        }
        if (noticeContent != null && !"".equals(noticeContent)) {
            sb.append(" and noticeContent like '%" + noticeContent + "%'");
        }
        sb.append(" limit ?,?");
        String sql = sb.toString();
        System.out.println("sql = " + sql);
        return JDBCUtils.queryList(sql, Notice.class, (pageIndex - 1) * pageSize, pageSize);
    }

    @Override
    public boolean addNotice(Notice notice, User user) {
        return JDBCUtils.update("insert into t_notice values (null,?,?,now(),?,?,0)",
                notice.getNoticeContent(),
                user.getUserId(),
                notice.getNoticeTitle(),
                user.getUserName());
    }

    @Override
    public boolean deleteNoticeById(int id) {
        return JDBCUtils.update("update t_notice set delState=1 where noticeId=?", id);
    }

    @Override
    public Notice getNoticeById(int id) {
        return JDBCUtils.queryOne("select * from t_notice where noticeId=?", Notice.class, id);
    }

    @Override
    public boolean updateNoticeById(Notice notice) {
        return JDBCUtils.update("update t_notice set noticeContent=?,noticeTitle=? where noticeId=?",
                notice.getNoticeContent(),
                notice.getNoticeTitle(),
                notice.getNoticeId());
    }
}
