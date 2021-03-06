package com.grs.demo.base;


import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import org.xutils.common.util.LogUtil;

/**
 * 服务器的API接口管理
 * Created by gaoruishan on 15/12/23.
 */
public class ServiceAPI {
    // 测试服务器
    private static final String SERVER_BASE_TEST = "http://111.44.243.118/";
    // 正式服务器
    private static final String SERVER_BASE_PRODUCT = getURL();
    //服务器IP地址
    public static String ADDRESS = SERVER_BASE_TEST;
    //服务器URL
    private static String URL = ADDRESS + "api/";

    //常用
    public static final String PARAM_TYPE = "type";
    public static final String PARAM_AND_TYPE = "&&type=";
    public static final String PARAM_QUE_TYPE = "?type=";
    public static final String PARAM_QUE_CID = "?cid=";
    public static final String PARAM_ID = "id";
    public static final String PARAM_CID = "cid";
    public static final String PARAM_AND_ID = "&&id=";
    public static final String PARAM_QUE_ID = "?id=";
    public static final String UTF_8 = "utf-8";
    public static final String PARAM_LIMIT = "limit";
    public static final String PARAM_AND_LIMIT = "&&limit=";
    public static final String PARAM_QUE_LIMIT = "?limit=";
    public static final String PARAM_OFFSET = "offset";
    public static final String PARAM_AND_OFFSET = "&&offset=";
    public static final String PARAM_AND_LATITUDE = "&&latitude=";
    public static final String PARAM_AND_LONGITUDE = "&&longitude=";
    public static final String PARAM_AND_CID = "&&cid=";
    public static final String PARAM_TOTAL = "total";
    public static final String PARAM_LATITUDE = "latitude";
    public static final String PARAM_LONGITUDE = "longitude";
    public static final String PARAM_CITY = "city";
    public static final String PARAM_DIVIDE = "divice";
    public static final String PARAM_TITLE = "title";
    public static final String PARAM_RECOMMEND = "recommend";

    //测试和正式服务开关
    public static void switchServer(boolean debug) {
        ADDRESS = debug ? SERVER_BASE_TEST : SERVER_BASE_PRODUCT;
        LogUtil.e(ADDRESS);
    }

    public static class Base {
        public static String CONTENT = "content/";
        public static String GET_LIST = "getList.do";
        public static String SHOPS = "shops/";
        public static String GET_CONTENT = "getcontent.do";
        public static String GET_DETAIL_ONE = "getDetailsOne.do";
        public static String GET_DETAIL_TWO = "getDetailsTwo.do";
        public static String GET_DETAIL_THREE = "getDetailsThree.do";
        public static String BUILD_MAIN = URL + "main/";
        public static String SHOP_BANNER = URL + "shop_banners/";
        public static String SHOP_RECOMMEND = URL + SHOPS;
        public static String BUILD_SCENIC = URL + "scenic/";
        public static String BUILD_ORDERING = "?ordering=";

        public static String getScenicList() {//获取列表
            return BUILD_SCENIC + GET_LIST;
        }

    }

    //首页
    public static class Index extends Base {

        public static String getIndex() {
            return BUILD_SCENIC + "index.do";
        }

        public static String getCultureBest() {
            return BUILD_SCENIC + "culture.do";
        }
    }

    //商户
    public static class Shop extends Base {

        public static String getBanner() {
            return SHOP_BANNER;
        }

        public static String getRecommend(String str) {
            return SHOP_RECOMMEND + BUILD_ORDERING + str;
        }
    }

    /**
     * 获得多渠道打包指定的IP
     *
     * @return
     */
    public static String getURL() {
        ApplicationInfo appInfo = null;
        try {
            appInfo = BaseApp.getContext().getPackageManager()
                    .getApplicationInfo(BaseApp.getContext().getPackageName(),
                            PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String server_ip = appInfo.metaData.getString("Server_IP");
        if (server_ip == null) {
            server_ip = "http://112.54.207.48/";
        }
        return server_ip;
    }
}
