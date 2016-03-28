package com.cmcc.hyapps.KunlunTravel.base;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.cmcc.hyapps.KunlunTravel.R;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.common.util.FileUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * 加载图片工具类
 * Created by gaoruishan on 16/1/5.
 */
public class ImageManager {
    private static int default_loading = R.drawable.default_loading;
    private static int default_empty = R.drawable.default_loading;
    private static int default_fail = R.drawable.default_loading;

    /**
     * 显示图片－带加载和错误图片
     * @param url 图片的地址
     * @param img ImageView控件
     * @param loadingResource 加载中图片(R.drawable.loading)
     * @param errorResource 错误图片
     */
    public static void displayImage(String url, ImageView img, int loadingResource, int errorResource) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
                .setRadius(DensityUtil.dip2px(5))
                .setCrop(false)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                // 加载中或错误图片的ScaleType //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                .setLoadingDrawableId(loadingResource)
                .setFailureDrawableId(errorResource)
                .build();
        x.image().bind(img, url, imageOptions);
    }

    /**
     * 显示图片
     * @param url 图片的地址
     * @param img ImageView控件
     */
    public static void displayImage(String url, ImageView img) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setLoadingDrawableId(default_loading)
                .setFailureDrawableId(default_fail)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();
        x.image().bind(img, url, imageOptions);
    }

    /**
     * 显示图片
     * @param url 图片的地址
     * @param img ImageView控件
     * @param callback 回调
     */
    public static void displayImage(String url, ImageView img,Callback.CommonCallback<Drawable> callback) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setLoadingDrawableId(default_loading)
                .setFailureDrawableId(default_fail)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();
        x.image().bind(img, url, imageOptions,callback);
    }

    /**
     * 清理 缓存
     */
    public static void clearDiskCache() {
        x.image().clearCacheFiles();
    }

    /**
     * 获取缓存大小
     * /storage/emulated/0/Android/data/com.cmcc.hyapps.KunlunTravel/cache
     * @return
     */
    public static String getDiskCache() {
        return FileUtil.getDiskCache();
    }
}
