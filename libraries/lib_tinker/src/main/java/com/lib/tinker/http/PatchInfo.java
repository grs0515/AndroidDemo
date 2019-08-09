package com.lib.tinker.http;

import java.io.Serializable;

/**
 * Created by renzhiqiang on 17/1/14.
 */

public class PatchInfo implements Serializable {
    /**
     * msg : ok
     * status : 200
     * code : 0
     * data : {"downloadUrl":"https://gaoruishan.cn:3000/apk/patch_signed.apk","versionName":"v1.0.0","patchMessage":"热更新","md5":"0fda9d0eaf91b33c73b20edf61e67c46"}
     */

    private String msg;
    private int status;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * downloadUrl : https://gaoruishan.cn:3000/apk/patch_signed.apk
         * versionName : v1.0.0
         * patchMessage : 热更新
         * md5 : 0fda9d0eaf91b33c73b20edf61e67c46
         */
        //不为空则表明有更新
        private String downloadUrl;
        //本次patch包的版本号
        private String versionName;
        //本次patch包含的相关信息，例如：主要做了那些改动
        private String patchMessage;
        //patch文件正确的md5值
        private String md5;

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getPatchMessage() {
            return patchMessage;
        }

        public void setPatchMessage(String patchMessage) {
            this.patchMessage = patchMessage;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }
    }
}
