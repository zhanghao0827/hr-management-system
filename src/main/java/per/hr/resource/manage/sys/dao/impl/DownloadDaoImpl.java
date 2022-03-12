package per.hr.resource.manage.sys.dao.impl;

import per.hr.resource.manage.sys.bean.Download;
import per.hr.resource.manage.sys.dao.DownloadDao;
import per.hr.resource.manage.sys.utils.JDBCUtils;

import java.util.List;

public class DownloadDaoImpl implements DownloadDao {

    @Override
    public boolean addDownload(Download download) {
        return JDBCUtils.update("insert into t_download values (null,?,now(),?,?,?,0);",
                download.getDownloadTittle(),
                download.getUserId(),
                download.getDownloadDes(),
                download.getDownPath()
        );
    }

    @Override
    public List<Download> getDownloads(String title) {
        StringBuffer sb = new StringBuffer("select d.*,u.userName from t_download d inner join t_user u on d.userId=u.userId WHERE d.delState=0");
        if (title != null && !"".equals(title)) {
            sb.append(" and d.downloadTittle like '%" + title + "%'");
        }
        return JDBCUtils.queryList(sb.toString(), Download.class);
    }

    @Override
    public Download getDownloadById(int id) {
        return JDBCUtils.queryOne("select * from t_download where downloadId=?", Download.class, id);
    }

    @Override
    public boolean updateDownloadById(Download download) {
        return JDBCUtils.update("update t_download set downloadTittle=?,downloadDes=?,downPath=? where downloadId=?",
                download.getDownloadTittle(),
                download.getDownloadDes(),
                download.getDownPath(),
                download.getDownloadId()
        );
    }
}
