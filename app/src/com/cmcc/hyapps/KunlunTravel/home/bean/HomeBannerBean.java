package com.cmcc.hyapps.KunlunTravel.home.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * http://112.54.207.48/KunlunTravelAPI/scenic/index.do
 * Created by gaoruishan on 16/3/21.
 */
public class HomeBannerBean implements Parcelable {

    /**
     * id : 213
     * href : http://112.54.207.48/media/qhkl/model/201603/A72221CF7BDE4F698C6EFF215675DE97.mp4
     * img_url : http://112.54.207.48/media/qhkl/model/201603/129B11A1CAC74C59995CAE99873FDC25.png
     * scenic_id : null
     * locate_id : null
     * title : 土族盘秀
     * playtime : null
     * descinfo : 土族盘秀
     * playcount : 9
     */

    private List<VideoEntity> video;
    /**
     * id : 29
     * image_url : http://112.54.207.48/media/qhkl/model/201603/02818A0CAC834A90ADEE52B752250D01.png
     * stype : 1
     * url :
     */

    private List<BannerEntity> banner;

    public void setVideo(List<VideoEntity> video) {
        this.video = video;
    }

    public void setBanner(List<BannerEntity> banner) {
        this.banner = banner;
    }

    public List<VideoEntity> getVideo() {
        return video;
    }

    public List<BannerEntity> getBanner() {
        return banner;
    }

    public static class VideoEntity implements Parcelable {
        private int id;
        private String href;
        private String img_url;
        private int scenic_id;
        private int locate_id;
        private String title;
        private String playtime;
        private String descinfo;
        private int playcount;

        public void setId(int id) {
            this.id = id;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public void setScenic_id(int scenic_id) {
            this.scenic_id = scenic_id;
        }

        public void setLocate_id(int locate_id) {
            this.locate_id = locate_id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPlaytime(String playtime) {
            this.playtime = playtime;
        }

        public void setDescinfo(String descinfo) {
            this.descinfo = descinfo;
        }

        public void setPlaycount(int playcount) {
            this.playcount = playcount;
        }

        public int getId() {
            return id;
        }

        public String getHref() {
            return href;
        }

        public String getImg_url() {
            return img_url;
        }

        public int getScenic_id() {
            return scenic_id;
        }

        public int getLocate_id() {
            return locate_id;
        }

        public String getTitle() {
            return title;
        }

        public String getPlaytime() {
            return playtime;
        }

        public String getDescinfo() {
            return descinfo;
        }

        public int getPlaycount() {
            return playcount;
        }

        @Override
        public String toString() {
            return "VideoEntity{" +
                    "id=" + id +
                    ", href='" + href + '\'' +
                    ", img_url='" + img_url + '\'' +
                    ", scenic_id=" + scenic_id +
                    ", locate_id=" + locate_id +
                    ", title='" + title + '\'' +
                    ", playtime='" + playtime + '\'' +
                    ", descinfo='" + descinfo + '\'' +
                    ", playcount=" + playcount +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.href);
            dest.writeString(this.img_url);
            dest.writeInt(this.scenic_id);
            dest.writeInt(this.locate_id);
            dest.writeString(this.title);
            dest.writeString(this.playtime);
            dest.writeString(this.descinfo);
            dest.writeInt(this.playcount);
        }

        public VideoEntity() {
        }

        protected VideoEntity(Parcel in) {
            this.id = in.readInt();
            this.href = in.readString();
            this.img_url = in.readString();
            this.scenic_id = in.readInt();
            this.locate_id = in.readInt();
            this.title = in.readString();
            this.playtime = in.readString();
            this.descinfo = in.readString();
            this.playcount = in.readInt();
        }

        public static final Creator<VideoEntity> CREATOR = new Creator<VideoEntity>() {
            public VideoEntity createFromParcel(Parcel source) {
                return new VideoEntity(source);
            }

            public VideoEntity[] newArray(int size) {
                return new VideoEntity[size];
            }
        };
    }

    public static class BannerEntity implements Parcelable {
        private int id;
        private String image_url;
        private int stype;
        private String url;

        public void setId(int id) {
            this.id = id;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public void setStype(int stype) {
            this.stype = stype;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getId() {
            return id;
        }

        public String getImage_url() {
            return image_url;
        }

        public int getStype() {
            return stype;
        }

        public String getUrl() {
            return url;
        }

        @Override
        public String toString() {
            return "BannerEntity{" +
                    "id=" + id +
                    ", image_url='" + image_url + '\'' +
                    ", stype=" + stype +
                    ", url='" + url + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.image_url);
            dest.writeInt(this.stype);
            dest.writeString(this.url);
        }

        public BannerEntity() {
        }

        protected BannerEntity(Parcel in) {
            this.id = in.readInt();
            this.image_url = in.readString();
            this.stype = in.readInt();
            this.url = in.readString();
        }

        public static final Creator<BannerEntity> CREATOR = new Creator<BannerEntity>() {
            public BannerEntity createFromParcel(Parcel source) {
                return new BannerEntity(source);
            }

            public BannerEntity[] newArray(int size) {
                return new BannerEntity[size];
            }
        };
    }

    @Override
    public String toString() {
        return "HomeBannerBean{" +
                "video=" + video +
                ", banner=" + banner +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.video);
        dest.writeList(this.banner);
    }

    public HomeBannerBean() {
    }

    protected HomeBannerBean(Parcel in) {
        this.video = new ArrayList<VideoEntity>();
        in.readList(this.video, List.class.getClassLoader());
        this.banner = new ArrayList<BannerEntity>();
        in.readList(this.banner, List.class.getClassLoader());
    }

    public static final Creator<HomeBannerBean> CREATOR = new Creator<HomeBannerBean>() {
        public HomeBannerBean createFromParcel(Parcel source) {
            return new HomeBannerBean(source);
        }

        public HomeBannerBean[] newArray(int size) {
            return new HomeBannerBean[size];
        }
    };
}
