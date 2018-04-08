package com.cretin.www.jokeshelp.model;

import java.util.List;

/**
 * Created by cretin on 2018/4/8.
 */

public class JokeTextModel {
    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"allPages":1795,"ret_code":0,"contentlist":[{"text":"老妈强行带我去相亲，见面后发现我和那男的竟然是小学同学。然后那男的对他妈说：这就是小时候动不动就揍我，逼我给她写作业，天天抢我饭钱买零食的女孩......他妈和我妈一脸黑线....","title":"老妈强行带我去相亲","type":1,"ct":"2018-04-08 10:00:02.143"},{"text":"今天店里来了个女顾客要洗澡，把手机寄存在吧台，并嘱咐来电话帮她接一下告诉对方她在洗澡，不一会电话真响了，我果断接听：\u201c喂，你好，她在我家洗澡，暂时不能接听你的电话，一会儿给你回复。\u201d只听对方大吼：\u201c你TM是谁？\u201d我心想这人真没礼貌，然后果断挂了电话。","title":"遇到一个没礼貌的人","type":1,"ct":"2018-04-08 10:00:02.143"},{"text":"前女友打电话来：说话便利吗？我看了一眼身旁的女友说：哦，还没吃呢。对方又说：那我等你便利了，再打过来。我笑了笑：做完饭也得8点了。挂掉电话后女友问我谁的电话，我说：我前女友的！她瞪了我一眼：厌烦，又逗我，肯定是你妈！","title":"前女友打来电话","type":1,"ct":"2018-04-08 10:00:02.142"},{"text":"和老公去吃饭，路过一家卖婴幼儿用品的店铺，在最显眼的地方贴了一张巨大的海报，是一个超甜美可爱的外国小萝莉，大大的蓝眼睛，微黄的小卷发，嘟着个小嘴，可爱到爆。<br />\r\n老公看着看着，突然很期待的说：\u201c将来我们生个孩子长得像这样就好了！\u201d 我坏笑着说：\u201c真的高兴吗？\u201d","title":"和老公去吃饭","type":1,"ct":"2018-04-08 10:00:02.142"},{"text":"今天媳妇搞卫生，不小心把老妈最心爱的花瓶摔破了。我知道肯定要闹翻了，媳妇惊慌失措捡起碎片，我怕她被割到手，便去帮忙捡那些最小的。捡完，媳妇掏出两百块钱给我说：\u201c等下婆婆问起，你就说你不小心摔破的，毕竟你亲生的，她不会把你怎么样。","title":"媳妇搞卫生","type":1,"ct":"2018-04-08 10:00:02.142"}],"currentPage":9,"allNum":8975,"maxResult":5}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * allPages : 1795
         * ret_code : 0
         * contentlist : [{"text":"老妈强行带我去相亲，见面后发现我和那男的竟然是小学同学。然后那男的对他妈说：这就是小时候动不动就揍我，逼我给她写作业，天天抢我饭钱买零食的女孩......他妈和我妈一脸黑线....","title":"老妈强行带我去相亲","type":1,"ct":"2018-04-08 10:00:02.143"},{"text":"今天店里来了个女顾客要洗澡，把手机寄存在吧台，并嘱咐来电话帮她接一下告诉对方她在洗澡，不一会电话真响了，我果断接听：\u201c喂，你好，她在我家洗澡，暂时不能接听你的电话，一会儿给你回复。\u201d只听对方大吼：\u201c你TM是谁？\u201d我心想这人真没礼貌，然后果断挂了电话。","title":"遇到一个没礼貌的人","type":1,"ct":"2018-04-08 10:00:02.143"},{"text":"前女友打电话来：说话便利吗？我看了一眼身旁的女友说：哦，还没吃呢。对方又说：那我等你便利了，再打过来。我笑了笑：做完饭也得8点了。挂掉电话后女友问我谁的电话，我说：我前女友的！她瞪了我一眼：厌烦，又逗我，肯定是你妈！","title":"前女友打来电话","type":1,"ct":"2018-04-08 10:00:02.142"},{"text":"和老公去吃饭，路过一家卖婴幼儿用品的店铺，在最显眼的地方贴了一张巨大的海报，是一个超甜美可爱的外国小萝莉，大大的蓝眼睛，微黄的小卷发，嘟着个小嘴，可爱到爆。<br />\r\n老公看着看着，突然很期待的说：\u201c将来我们生个孩子长得像这样就好了！\u201d 我坏笑着说：\u201c真的高兴吗？\u201d","title":"和老公去吃饭","type":1,"ct":"2018-04-08 10:00:02.142"},{"text":"今天媳妇搞卫生，不小心把老妈最心爱的花瓶摔破了。我知道肯定要闹翻了，媳妇惊慌失措捡起碎片，我怕她被割到手，便去帮忙捡那些最小的。捡完，媳妇掏出两百块钱给我说：\u201c等下婆婆问起，你就说你不小心摔破的，毕竟你亲生的，她不会把你怎么样。","title":"媳妇搞卫生","type":1,"ct":"2018-04-08 10:00:02.142"}]
         * currentPage : 9
         * allNum : 8975
         * maxResult : 5
         */

        private int allPages;
        private int ret_code;
        private int currentPage;
        private int allNum;
        private int maxResult;
        private List<ContentlistBean> contentlist;

        public int getAllPages() {
            return allPages;
        }

        public void setAllPages(int allPages) {
            this.allPages = allPages;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getAllNum() {
            return allNum;
        }

        public void setAllNum(int allNum) {
            this.allNum = allNum;
        }

        public int getMaxResult() {
            return maxResult;
        }

        public void setMaxResult(int maxResult) {
            this.maxResult = maxResult;
        }

        public List<ContentlistBean> getContentlist() {
            return contentlist;
        }

        public void setContentlist(List<ContentlistBean> contentlist) {
            this.contentlist = contentlist;
        }

        public static class ContentlistBean {
            /**
             * text : 老妈强行带我去相亲，见面后发现我和那男的竟然是小学同学。然后那男的对他妈说：这就是小时候动不动就揍我，逼我给她写作业，天天抢我饭钱买零食的女孩......他妈和我妈一脸黑线....
             * title : 老妈强行带我去相亲
             * type : 1
             * ct : 2018-04-08 10:00:02.143
             */

            private String text;
            private String title;
            private int type;
            private String ct;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getCt() {
                return ct;
            }

            public void setCt(String ct) {
                this.ct = ct;
            }
        }
    }
}
