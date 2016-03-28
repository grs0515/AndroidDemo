package com.cmcc.hyapps.KunlunTravel.home.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * http://112.54.207.48/KunlunTravelAPI/scenic/culture.do
 * Created by gaoruishan on 16/3/14.
 */
public class CultureBestBean implements Parcelable {

    /**
     * id : 17
     * title : 花儿吧
     * stars : null
     * logo_url : http://112.54.207.48/media/scenic/201512/banner.png
     * type : null
     * area : null
     * avg_price : null
     * longitude : 0
     * latitude : 0
     * make_method : null
     * comments : 0
     * favors : 0
     * descinfo : 1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
     * ranking : null
     * pid : 6
     * distance : null
     * play_times : null
     * recommend_id : null
     * voiceUrl : null
     * voiceDesc : null
     * isFavor : null
     */

    private List<ResultsEntity> results;

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public static class ResultsEntity implements Parcelable {
        private int id;
        private String title;
        private int stars;
        private String logo_url;
        private int type;
        private int area;
        private String avg_price;
        private int longitude;
        private int latitude;
        private String make_method;
        private int comments;
        private int favors;
        private String descinfo;
        private int ranking;
        private int pid;
        private int distance;
        private int play_times;
        private int recommend_id;
        private String voiceUrl;
        private String voiceDesc;
        private int isFavor;

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setStars(int stars) {
            this.stars = stars;
        }

        public void setLogo_url(String logo_url) {
            this.logo_url = logo_url;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public void setAvg_price(String avg_price) {
            this.avg_price = avg_price;
        }

        public void setLongitude(int longitude) {
            this.longitude = longitude;
        }

        public void setLatitude(int latitude) {
            this.latitude = latitude;
        }

        public void setMake_method(String make_method) {
            this.make_method = make_method;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public void setFavors(int favors) {
            this.favors = favors;
        }

        public void setDescinfo(String descinfo) {
            this.descinfo = descinfo;
        }

        public void setRanking(int ranking) {
            this.ranking = ranking;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public void setPlay_times(int play_times) {
            this.play_times = play_times;
        }

        public void setRecommend_id(int recommend_id) {
            this.recommend_id = recommend_id;
        }

        public void setVoiceUrl(String voiceUrl) {
            this.voiceUrl = voiceUrl;
        }

        public void setVoiceDesc(String voiceDesc) {
            this.voiceDesc = voiceDesc;
        }

        public void setIsFavor(int isFavor) {
            this.isFavor = isFavor;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public int getStars() {
            return stars;
        }

        public String getLogo_url() {
            return logo_url;
        }

        public int getType() {
            return type;
        }

        public int getArea() {
            return area;
        }

        public String getAvg_price() {
            return avg_price;
        }

        public int getLongitude() {
            return longitude;
        }

        public int getLatitude() {
            return latitude;
        }

        public String getMake_method() {
            return make_method;
        }

        public int getComments() {
            return comments;
        }

        public int getFavors() {
            return favors;
        }

        public String getDescinfo() {
            return descinfo;
        }

        public int getRanking() {
            return ranking;
        }

        public int getPid() {
            return pid;
        }

        public int getDistance() {
            return distance;
        }

        public int getPlay_times() {
            return play_times;
        }

        public int getRecommend_id() {
            return recommend_id;
        }

        public String getVoiceUrl() {
            return voiceUrl;
        }

        public String getVoiceDesc() {
            return voiceDesc;
        }

        public int getIsFavor() {
            return isFavor;
        }

        @Override
        public String toString() {
            return "ResultsEntity{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", stars=" + stars +
                    ", logo_url='" + logo_url + '\'' +
                    ", type=" + type +
                    ", area=" + area +
                    ", avg_price='" + avg_price + '\'' +
                    ", longitude=" + longitude +
                    ", latitude=" + latitude +
                    ", make_method='" + make_method + '\'' +
                    ", comments=" + comments +
                    ", favors=" + favors +
                    ", descinfo='" + descinfo + '\'' +
                    ", ranking=" + ranking +
                    ", pid=" + pid +
                    ", distance=" + distance +
                    ", play_times=" + play_times +
                    ", recommend_id=" + recommend_id +
                    ", voiceUrl='" + voiceUrl + '\'' +
                    ", voiceDesc='" + voiceDesc + '\'' +
                    ", isFavor=" + isFavor +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.title);
            dest.writeInt(this.stars);
            dest.writeString(this.logo_url);
            dest.writeInt(this.type);
            dest.writeInt(this.area);
            dest.writeString(this.avg_price);
            dest.writeInt(this.longitude);
            dest.writeInt(this.latitude);
            dest.writeString(this.make_method);
            dest.writeInt(this.comments);
            dest.writeInt(this.favors);
            dest.writeString(this.descinfo);
            dest.writeInt(this.ranking);
            dest.writeInt(this.pid);
            dest.writeInt(this.distance);
            dest.writeInt(this.play_times);
            dest.writeInt(this.recommend_id);
            dest.writeString(this.voiceUrl);
            dest.writeString(this.voiceDesc);
            dest.writeInt(this.isFavor);
        }

        public ResultsEntity() {
        }

        protected ResultsEntity(Parcel in) {
            this.id = in.readInt();
            this.title = in.readString();
            this.stars = in.readInt();
            this.logo_url = in.readString();
            this.type = in.readInt();
            this.area = in.readInt();
            this.avg_price = in.readString();
            this.longitude = in.readInt();
            this.latitude = in.readInt();
            this.make_method = in.readString();
            this.comments = in.readInt();
            this.favors = in.readInt();
            this.descinfo = in.readString();
            this.ranking = in.readInt();
            this.pid = in.readInt();
            this.distance = in.readInt();
            this.play_times = in.readInt();
            this.recommend_id = in.readInt();
            this.voiceUrl = in.readString();
            this.voiceDesc = in.readString();
            this.isFavor = in.readInt();
        }

        public static final Parcelable.Creator<ResultsEntity> CREATOR = new Parcelable.Creator<ResultsEntity>() {
            public ResultsEntity createFromParcel(Parcel source) {
                return new ResultsEntity(source);
            }

            public ResultsEntity[] newArray(int size) {
                return new ResultsEntity[size];
            }
        };
    }

    @Override
    public String toString() {
        return "CultureBest{" +
                "results=" + results +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(results);
    }

    public CultureBestBean() {
    }

    protected CultureBestBean(Parcel in) {
        this.results = in.createTypedArrayList(ResultsEntity.CREATOR);
    }

    public static final Parcelable.Creator<CultureBestBean> CREATOR = new Parcelable.Creator<CultureBestBean>() {
        public CultureBestBean createFromParcel(Parcel source) {
            return new CultureBestBean(source);
        }

        public CultureBestBean[] newArray(int size) {
            return new CultureBestBean[size];
        }
    };
}
