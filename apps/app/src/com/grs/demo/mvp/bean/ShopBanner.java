package com.grs.demo.mvp.bean;

import java.util.List;

/**
 * http://111.44.243.118/api/shop_banners/?format=json
 * Created by gaoruishan on 16/7/8.
 */
public class ShopBanner {

    /**
     * count : 5
     * next :
     * previous :
     * results : [{"admin_uid":65,"click_num":0,"create_time":"2016-06-20 15:33:35","id":86,"image_url":"http://111.44.243.118/media/scenic/201606/9B39CEEB4844429ABE2041DF64D635F8.jpg","obj_id":336,"order_weight":9,"position":1,"referred_shop":{"address":"西宁城西新华联购物中心5号门门口","average":80,"comment_count":1,"created":"2016-06-20 15:18:33","id":336,"image_url":"http://111.44.243.118/media/shop/201606/8FD331B97FAA44ADB8ABD733A42AE4BF.jpg","introduction":"中国十大火锅品牌，蜀中味·九州香，纯正成都味道飘香夏都。","latitude":34.51318758315319,"level":"1","longitude":117.82080969566347,"modified":"2016-06-20 15:18:33","name":"蜀九香火锅海湖店","order_weight":9,"promotion":"","recommend":0,"stype":2,"telephone":"0971-4519888"},"type":2,"update_time":"2016-06-20 15:33:35","video_url":""},{"admin_uid":65,"click_num":0,"create_time":"2016-06-20 15:34:42","id":91,"image_url":"http://111.44.243.118/media/scenic/201606/8C737B0B46304A38AFFCD6A31D40E596.jpg","obj_id":331,"order_weight":8,"position":1,"referred_shop":{"address":"西宁市城东韵家口镇互助东路朱家庄村","average":50,"comment_count":0,"created":"2016-06-17 16:38:57","id":331,"image_url":"http://111.44.243.118/media/shop/201606/E692D63043364102B2EEC0114C45DD73.jpg","introduction":"小花农家院为省旅游局评定的四级农家院，园区占地面十亩，划分有停车场，婚礼大厅，贵宾间，宴会小厅一共二十五个包间，可容纳三百人同时就餐，园区整体以农家院为布局规划。名贵牡丹一百六十余种为主，杏树，假山，木亭，围栏相印成趣，以自然景色庭院风光为主。小花农家院以青海本地菜为主，是你休闲娱乐，观光旅游，餐饮娱乐的好去处。","latitude":36.62314573608094,"level":"1","longitude":101.82076551820768,"modified":"2016-06-17 16:38:57","name":"小花农家院","order_weight":0,"promotion":"","recommend":0,"stype":5,"telephone":"18997103169"},"type":5,"update_time":"2016-06-20 15:34:42","video_url":""}]
     */

    private int count;
    private String next;
    private String previous;
    /**
     * admin_uid : 65
     * click_num : 0
     * create_time : 2016-06-20 15:33:35
     * id : 86
     * image_url : http://111.44.243.118/media/scenic/201606/9B39CEEB4844429ABE2041DF64D635F8.jpg
     * obj_id : 336
     * order_weight : 9
     * position : 1
     * referred_shop : {"address":"西宁城西新华联购物中心5号门门口","average":80,"comment_count":1,"created":"2016-06-20 15:18:33","id":336,"image_url":"http://111.44.243.118/media/shop/201606/8FD331B97FAA44ADB8ABD733A42AE4BF.jpg","introduction":"中国十大火锅品牌，蜀中味·九州香，纯正成都味道飘香夏都。","latitude":34.51318758315319,"level":"1","longitude":117.82080969566347,"modified":"2016-06-20 15:18:33","name":"蜀九香火锅海湖店","order_weight":9,"promotion":"","recommend":0,"stype":2,"telephone":"0971-4519888"}
     * type : 2
     * update_time : 2016-06-20 15:33:35
     * video_url :
     */

    private List<ResultsEntity> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public static class ResultsEntity {
        private int admin_uid;
        private int click_num;
        private String create_time;
        private int id;
        private String image_url;
        private int obj_id;
        private int order_weight;
        private int position;
        /**
         * address : 西宁城西新华联购物中心5号门门口
         * average : 80
         * comment_count : 1
         * created : 2016-06-20 15:18:33
         * id : 336
         * image_url : http://111.44.243.118/media/shop/201606/8FD331B97FAA44ADB8ABD733A42AE4BF.jpg
         * introduction : 中国十大火锅品牌，蜀中味·九州香，纯正成都味道飘香夏都。
         * latitude : 34.51318758315319
         * level : 1
         * longitude : 117.82080969566347
         * modified : 2016-06-20 15:18:33
         * name : 蜀九香火锅海湖店
         * order_weight : 9
         * promotion :
         * recommend : 0
         * stype : 2
         * telephone : 0971-4519888
         */

        private ReferredShopEntity referred_shop;
        private int type;
        private String update_time;
        private String video_url;

        public int getAdmin_uid() {
            return admin_uid;
        }

        public void setAdmin_uid(int admin_uid) {
            this.admin_uid = admin_uid;
        }

        public int getClick_num() {
            return click_num;
        }

        public void setClick_num(int click_num) {
            this.click_num = click_num;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public int getObj_id() {
            return obj_id;
        }

        public void setObj_id(int obj_id) {
            this.obj_id = obj_id;
        }

        public int getOrder_weight() {
            return order_weight;
        }

        public void setOrder_weight(int order_weight) {
            this.order_weight = order_weight;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public ReferredShopEntity getReferred_shop() {
            return referred_shop;
        }

        public void setReferred_shop(ReferredShopEntity referred_shop) {
            this.referred_shop = referred_shop;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public static class ReferredShopEntity {
            private String address;
            private int average;
            private int comment_count;
            private String created;
            private int id;
            private String image_url;
            private String introduction;
            private double latitude;
            private String level;
            private double longitude;
            private String modified;
            private String name;
            private int order_weight;
            private String promotion;
            private int recommend;
            private int stype;
            private String telephone;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getAverage() {
                return average;
            }

            public void setAverage(int average) {
                this.average = average;
            }

            public int getComment_count() {
                return comment_count;
            }

            public void setComment_count(int comment_count) {
                this.comment_count = comment_count;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public String getModified() {
                return modified;
            }

            public void setModified(String modified) {
                this.modified = modified;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getOrder_weight() {
                return order_weight;
            }

            public void setOrder_weight(int order_weight) {
                this.order_weight = order_weight;
            }

            public String getPromotion() {
                return promotion;
            }

            public void setPromotion(String promotion) {
                this.promotion = promotion;
            }

            public int getRecommend() {
                return recommend;
            }

            public void setRecommend(int recommend) {
                this.recommend = recommend;
            }

            public int getStype() {
                return stype;
            }

            public void setStype(int stype) {
                this.stype = stype;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }
        }
    }
}
