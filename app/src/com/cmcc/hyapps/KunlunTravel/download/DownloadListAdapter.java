package com.cmcc.hyapps.KunlunTravel.download;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.cmcc.hyapps.KunlunTravel.R;

import org.xutils.download.DownloadInfo;
import org.xutils.download.DownloadManager;
import org.xutils.download.DownloadService;
import org.xutils.download.DownloadState;
import org.xutils.ex.DbException;
import org.xutils.x;

public class DownloadListAdapter extends BaseAdapter {

    private Context mContext;
    private final LayoutInflater mInflater;
    private DownloadManager downloadManager;

    public DownloadListAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        if (downloadManager == null) {
            downloadManager = DownloadService.getDownloadManager();
        }
        return downloadManager.getDownloadListCount();
    }

    @Override
    public Object getItem(int i) {
        return downloadManager.getDownloadInfo(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DownloadItemViewHolder holder = null;
        DownloadInfo downloadInfo = downloadManager.getDownloadInfo(i);
        if (view == null) {
            view = mInflater.inflate(R.layout.download_item, null);
            holder = new DownloadItemViewHolder(view, downloadInfo);
            view.setTag(holder);
            holder.refresh();
        } else {
            holder = (DownloadItemViewHolder) view.getTag();
            holder.update(downloadInfo);
        }

        if (downloadInfo.getState().value() < DownloadState.FINISHED.value()) {
            try {
                downloadManager.startDownload(
                        downloadInfo.getUrl(),
                        downloadInfo.getLabel(),
                        downloadInfo.getFileSavePath(),
                        downloadInfo.isAutoResume(),
                        downloadInfo.isAutoRename(),
                        holder);
            } catch (DbException ex) {
                Toast.makeText(x.app(), "添加下载失败", Toast.LENGTH_LONG).show();
            }
        }

        return view;
    }
}
