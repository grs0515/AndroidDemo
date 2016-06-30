package com.cmcc.hyapps.KunlunTravel.download;

import android.os.Bundle;
import android.widget.ListView;

import com.cmcc.hyapps.KunlunTravel.R;
import com.cmcc.hyapps.KunlunTravel.base.AppConst;
import com.cmcc.hyapps.KunlunTravel.base.BaseActivity;
import com.cmcc.hyapps.KunlunTravel.utils.common.SPUtils;

import org.xutils.download.DownloadManager;
import org.xutils.download.DownloadService;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_download)
public class DownloadListActivity extends BaseActivity {
    @ViewInject(R.id.lv_download)
    private ListView downloadList;

    private DownloadManager downloadManager;
    private DownloadListAdapter downloadListAdapter;
    private String[] strArr = {
            "http://gdown.baidu.com/data/wisegame/faf7795f5e16ff68/weixin_740.apk",
            "http://z.k1982.com/show_img/201303/2013033012383895.jpg",
            "http://gdown.baidu.com/data/wisegame/faf7795f5e16ff68/weixin_740.apk",
            "http://dlqncdn.miaopai.com/stream/MVaux41A4lkuWloBbGUGaQ__.mp4"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean b = (boolean) SPUtils.get(this, AppConst.KEY_BOOLEAN, false);
        if (!b) {
            initDownload();
        }
        downloadManager = DownloadService.getDownloadManager();
        if (downloadManager.getDownloadListCount() == 0) {
            initDownload();
        }
        downloadListAdapter = new DownloadListAdapter(getBaseContext());
        downloadList.setAdapter(downloadListAdapter);
    }

    private void initDownload() {
        SPUtils.put(this, AppConst.KEY_BOOLEAN, true);
        for (String s : strArr) {
            startDownload(s);
        }
    }

    private void startDownload(String url) {
        String apk_name = url.substring(url.lastIndexOf("/") + 1);
        try {
            DownloadService.getDownloadManager().startDownload(
                    url, apk_name,
                    "/sdcard/xUtils/" + apk_name, true, true, null);
        } catch (DbException e) {
            e.printStackTrace();
        }

    }
}
