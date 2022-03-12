package per.hr.resource.manage.sys.service;

import per.hr.resource.manage.sys.bean.Download;

import java.util.List;

public interface DownloadService {

    boolean addDownload(Download download);

    List<Download> getDownloads(String title);

    Download getDownloadById(int id);

    boolean updateDownloadById(Download download);
}
