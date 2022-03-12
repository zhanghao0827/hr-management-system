package per.hr.resource.manage.sys.service.impl;

import per.hr.resource.manage.sys.bean.Download;
import per.hr.resource.manage.sys.dao.DownloadDao;
import per.hr.resource.manage.sys.dao.impl.DownloadDaoImpl;
import per.hr.resource.manage.sys.service.DownloadService;

import java.util.List;

public class DownloadServiceImpl implements DownloadService {

    DownloadDao downloadDao = new DownloadDaoImpl();

    @Override
    public boolean addDownload(Download download) {
        return downloadDao.addDownload(download);
    }

    @Override
    public List<Download> getDownloads(String title) {
        return downloadDao.getDownloads(title);
    }

    @Override
    public Download getDownloadById(int id) {
        return downloadDao.getDownloadById(id);
    }

    @Override
    public boolean updateDownloadById(Download download) {
        return downloadDao.updateDownloadById(download);
    }
}
