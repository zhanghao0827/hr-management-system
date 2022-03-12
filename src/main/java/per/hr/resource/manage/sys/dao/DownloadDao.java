package per.hr.resource.manage.sys.dao;

import per.hr.resource.manage.sys.bean.Download;

import java.util.List;

public interface DownloadDao {

    boolean addDownload(Download download);

    List<Download> getDownloads(String title);

    Download getDownloadById(int id);

    boolean updateDownloadById(Download download);
}
