package net.swiftos.common.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * 下载服务
 * Created by gy939 on 2017/1/17.
 */

public class DownloadService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public interface ICallback {
        void downloading(DownloadEntity info);
    }

    public class DownloadEntity {
        public int id;
        public String fileName;
        public String storePath;
        public String sourceUrl;
        public long size;
        public long downloaded;
        public float progress;
    }

}
