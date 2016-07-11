package com.grs.demo.mvp.bean;

import java.util.List;

/**
 * http://111.44.243.118/api/shops/?ordering=recommend
 * Created by gaoruishan on 16/7/11.
 */
public class ShopRecommend {

    /**
     * count : 83
     * next : http://111.44.243.118:80/api/shops/?limit=10&offset=10&ordering=recommend
     * previous :
     * results : [{"address":"城北区宁大路243号(青海大学向西100米)","average":80,"comment_count":0,"created":"2016-07-07 10:06:01","id":371,"image_url":"http://111.44.243.118/media/shop/201607/63A93EBE2A7440B58064845495A76CD3.jpg","introduction":"现烤的炭火烤全羊、精品烤羊排、炭火烤土豆等地方特色菜","latitude":36.7287996434708,"level":"1","longitude":101.7535763192158,"modified":"2016-07-07 10:06:01","name":"熙园生态园","order_weight":9,"promotion":"","recommend":0,"stype":5,"telephone":"0971-5313977"},{"address":"城东区杨沟湾苹果园内","average":80,"comment_count":0,"created":"2016-07-07 10:01:35","id":366,"image_url":"http://111.44.243.118/media/shop/201607/80AB05BE4EF7422AA31BE44067EE9326.jpg","introduction":"集餐饮、娱乐、休闲为一体的四星级大型生态园，占地面积四千多平米，可一次性接待四百多人的宴席.","latitude":36.602116754388206,"level":"1","longitude":101.83186471159732,"modified":"2016-07-07 10:01:35","name":"君悦生态园","order_weight":9,"promotion":"","recommend":0,"stype":5,"telephone":"0971-6518441"},{"address":"城东区韵家口朱家村3号","average":60,"comment_count":0,"created":"2016-07-07 09:56:21","id":361,"image_url":"http://111.44.243.118/media/shop/201607/76C734AE7F754A47912A8AE64A8AB096.jpg","introduction":"家裙土鸡,蕊轩翡翠包,河州发菜,豆鼓带鱼,裙土豆,开屏武昌鱼","latitude":36.59040993144285,"level":"1","longitude":101.87631125128111,"modified":"2016-07-07 09:56:29","name":"蕊轩盆景生态园","order_weight":9,"promotion":"","recommend":0,"stype":5,"telephone":"18997338888"},{"address":"湟中县109国道远达石化加油站西200米","average":100,"comment_count":0,"created":"2016-07-07 09:53:14","id":356,"image_url":"http://111.44.243.118/media/shop/201607/C3DEC9FF4F8942CB904CC90A214C67EE.jpg","introduction":"主要经营川菜、粤菜、海鲜、地方特色菜、青海老八盘、承包团宴，一年四季营业，是休闲旅游度假的好去处。","latitude":36.64441383560696,"level":"1","longitude":101.60052202751002,"modified":"2016-07-07 09:53:14","name":"万聚源生态园","order_weight":9,"promotion":"","recommend":0,"stype":5,"telephone":"0971-2277888"},{"address":"城东区八一路30号湟乐公园内","average":100,"comment_count":0,"created":"2016-07-07 09:49:25","id":351,"image_url":"http://111.44.243.118/media/shop/201607/DD9EFBB0B6AC410CA8F38C1CA6CC6D3B.png","introduction":"是将现代设施农业和绿色餐饮完美结合的产物，以人为本、景观为主、餐饮为辅的大型温室类休闲佳地.","latitude":36.58167937146769,"level":"1","longitude":101.86381884867023,"modified":"2016-07-07 09:49:25","name":"景熙丰湿地生态园","order_weight":9,"promotion":"","recommend":0,"stype":5,"telephone":"0971-7173000"},{"address":"西宁城西新华联购物中心5号门门口","average":80,"comment_count":1,"created":"2016-06-20 15:18:33","id":336,"image_url":"http://111.44.243.118/media/shop/201606/8FD331B97FAA44ADB8ABD733A42AE4BF.jpg","introduction":"中国十大火锅品牌，蜀中味·九州香，纯正成都味道飘香夏都。","latitude":34.51318758315319,"level":"1","longitude":117.82080969566347,"modified":"2016-06-20 15:18:33","name":"蜀九香火锅海湖店","order_weight":9,"promotion":"","recommend":0,"stype":2,"telephone":"0971-4519888"},{"address":"青海省西宁市城北区大堡子镇陶北村口","average":60,"comment_count":2,"created":"2016-04-27 11:46:09","id":286,"image_url":"http://111.44.243.118/media/shop/201604/2CB944D2989C4250917F3748AAE90FC7.jpg","introduction":"菜系：青海农家系列特色菜肴 特色菜品介绍：农家炒土鸡、土家排骨、洋芋酿皮、炕洋芋、豆面饭块、","latitude":36.662656123892,"level":"1","longitude":101.64149719401,"modified":"2016-05-13 11:47:27","name":"西宁乡趣文化生态园","order_weight":9,"promotion":"","recommend":0,"stype":5,"telephone":"0971-8586659"},{"address":"西宁市城中区南川东路水磨公交车站旁","average":80,"comment_count":0,"created":"2016-05-13 09:59:18","id":316,"image_url":"http://111.44.243.118/media/shop/201605/D6C82761C5F84370B4769482BBBEDB27.png","introduction":"高原生态裙土鸡、祁连黄菇炒牛肉。","latitude":36.572987779601,"level":"1","longitude":101.74410951582,"modified":"2016-05-13 09:59:18","name":"皓翔阁","order_weight":9,"promotion":"","recommend":0,"stype":5,"telephone":"18797118977"},{"address":"城东区红叶谷休闲生态景区3号别墅","average":100,"comment_count":0,"created":"2016-07-07 10:13:54","id":376,"image_url":"http://111.44.243.118/media/shop/201607/E673D6B9F61C400CB62C312A09F14EEC.jpg","introduction":"青海菜","latitude":36.557898988909805,"level":"1","longitude":101.89895043657411,"modified":"2016-07-07 10:13:54","name":"红叶3号别墅休闲餐厅","order_weight":0,"promotion":"","recommend":0,"stype":5,"telephone":"0971-7160000"},{"address":"城西区西川南路93号 ","average":50,"comment_count":0,"created":"2016-07-01 16:40:53","id":346,"image_url":"http://111.44.243.118/media/shop/201607/F0098EA1D6DD4C1EB082EEDEE28E24FF.jpg","introduction":"金品盆景园坐落在城 西区西川南路93号，是目前我区最大的盆景展示园，为四星级乡村旅游接待点，园内展示着各种姿态各异的珍奇盆景。 ","latitude":36.64676254801016,"level":"1","longitude":101.69778414590706,"modified":"2016-07-01 16:40:53","name":"金品盆景园","order_weight":0,"promotion":"","recommend":0,"stype":5,"telephone":"0971-6317765"}]
     */

    private int count;
    private String next;
    private String previous;
    /**
     * address : 城北区宁大路243号(青海大学向西100米)
     * average : 80
     * comment_count : 0
     * created : 2016-07-07 10:06:01
     * id : 371
     * image_url : http://111.44.243.118/media/shop/201607/63A93EBE2A7440B58064845495A76CD3.jpg
     * introduction : 现烤的炭火烤全羊、精品烤羊排、炭火烤土豆等地方特色菜
     * latitude : 36.7287996434708
     * level : 1
     * longitude : 101.7535763192158
     * modified : 2016-07-07 10:06:01
     * name : 熙园生态园
     * order_weight : 9
     * promotion :
     * recommend : 0
     * stype : 5
     * telephone : 0971-5313977
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
