package com.cretin.www.jokeshelp.model;

import java.util.List;

/**
 * Created by cretin on 2018/4/5.
 */

public class JokeImgModel {

    /**
     * reason : success
     * result : [{"content":"有图，自己看！","hashId":"0038F99648890438BB8088A05ED09C0A","unixtime":1427604744,"url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201503/29/0038F99648890438BB8088A05ED09C0A.jpg"},{"content":"瞧你这享受的哟","hashId":"676CB2CFEA1B57F9B1F19F205F5957C3","unixtime":1427604776,"url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201503/29/676CB2CFEA1B57F9B1F19F205F5957C3.jpg"},{"content":"累觉不爱的钥匙","hashId":"EAE70B56C78B4C91E9772248B5A777A5","unixtime":1427604776,"url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201503/29/EAE70B56C78B4C91E9772248B5A777A5.jpg"},{"content":"我都这样了难道你还看不出来我对你的心么","hashId":"EFF479C9B136685B13C8EC7F8DD93123","unixtime":1427605453,"url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201503/29/EFF479C9B136685B13C8EC7F8DD93123.jpg"},{"content":"羡慕嫉妒恨嘛","hashId":"6D038F17815FD2A98B59E1D770F0656E","unixtime":1427605946,"url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201503/29/6D038F17815FD2A98B59E1D770F0656E.jpg"},{"content":"夏天坐摩托车的好处","hashId":"89A67C15A991BF845537F108670F60C7","unixtime":1427605953,"url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201503/29/89A67C15A991BF845537F108670F60C7.jpg"},{"content":"大湿兄，你又调皮了！！！","hashId":"2599E720894D722E2E71CFD862B7B034","unixtime":1427606533,"url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201503/29/2599E720894D722E2E71CFD862B7B034.jpg"},{"content":"人艰不拆的事实","hashId":"20B8AC61752AE710DAC63B542107874D","unixtime":1427606552,"url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201503/29/20B8AC61752AE710DAC63B542107874D.png"},{"content":"在超市里面看到的型男海带装","hashId":"7D8369050061D79A19CF4848708C5E5C","unixtime":1427607140,"url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201503/29/7D8369050061D79A19CF4848708C5E5C.jpg"},{"content":"老板文案不错","hashId":"8344C9DC765F7E7D0BFA2B11EC5511C7","unixtime":1427607154,"url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201503/29/8344C9DC765F7E7D0BFA2B11EC5511C7.jpg"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * content : 有图，自己看！
         * hashId : 0038F99648890438BB8088A05ED09C0A
         * unixtime : 1427604744
         * url : http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201503/29/0038F99648890438BB8088A05ED09C0A.jpg
         */

        private String content;
        private String hashId;
        private int unixtime;
        private String url;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHashId() {
            return hashId;
        }

        public void setHashId(String hashId) {
            this.hashId = hashId;
        }

        public int getUnixtime() {
            return unixtime;
        }

        public void setUnixtime(int unixtime) {
            this.unixtime = unixtime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
