package com.cretin.www.jokeshelp.model;

import java.util.List;

/**
 * Created by cretin on 2018/4/9.
 */

public class VerificationCodeModel {
    /**
     * message : 数据返回成功
     * code : 1
     * data : {"page":0,"totalCount":21,"totalPage":3,"limit":10,"list":[{"smsId":"46725452e3e2483e936c00d6f50dde1d","smstel":"13227293734","smscontent":"验证码092335，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"092335","updateTime":1515050890000,"smstype":"regist"},{"smsId":"6aabd0cd26e448db9331097776dbaea2","smstel":"13227293733","smscontent":"验证码094994，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"094994","updateTime":1515050505000,"smstype":"regist"},{"smsId":"3d122c22845747fa865fe4923f9fa9b0","smstel":"13227293733","smscontent":"验证码339368，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"339368","updateTime":1515050403000,"smstype":"regist"},{"smsId":"407b311ce5cc4106b409adc6b1f58f16","smstel":"13227293733","smscontent":"验证码487893，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"487893","updateTime":1515050099000,"smstype":"regist"},{"smsId":"1d7f16d7ab454525877a7cb7706c6f3f","smstel":"13227293732","smscontent":"验证码858235，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"858235","updateTime":1515049833000,"smstype":"regist"},{"smsId":"40394143eacc4186979bc939a96b7e2c","smstel":"13227293732","smscontent":"验证码186239，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"186239","updateTime":1515049602000,"smstype":"regist"},{"smsId":"73768bbb87214394b4744fa08377f7ed","smstel":"13227293732","smscontent":"验证码222642，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"222642","updateTime":1515049196000,"smstype":"regist"},{"smsId":"ae01b86e2e4846aa864648cb645a5104","smstel":"13227293732","smscontent":"验证码369422，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"369422","updateTime":1515048884000,"smstype":"regist"},{"smsId":"528505de64b741579026fe9920cf572d","smstel":"13227293731","smscontent":"验证码421754，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"421754","updateTime":1515048388000,"smstype":"regist"},{"smsId":"a345f97da0284408a5f93a417b79525a","smstel":"13227293730","smscontent":"验证码042345，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"042345","updateTime":1515047773000,"smstype":"regist"}],"extraData":null}
     */

    private String message;
    private int code;
    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
         * page : 0
         * totalCount : 21
         * totalPage : 3
         * limit : 10
         * list : [{"smsId":"46725452e3e2483e936c00d6f50dde1d","smstel":"13227293734","smscontent":"验证码092335，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"092335","updateTime":1515050890000,"smstype":"regist"},{"smsId":"6aabd0cd26e448db9331097776dbaea2","smstel":"13227293733","smscontent":"验证码094994，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"094994","updateTime":1515050505000,"smstype":"regist"},{"smsId":"3d122c22845747fa865fe4923f9fa9b0","smstel":"13227293733","smscontent":"验证码339368，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"339368","updateTime":1515050403000,"smstype":"regist"},{"smsId":"407b311ce5cc4106b409adc6b1f58f16","smstel":"13227293733","smscontent":"验证码487893，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"487893","updateTime":1515050099000,"smstype":"regist"},{"smsId":"1d7f16d7ab454525877a7cb7706c6f3f","smstel":"13227293732","smscontent":"验证码858235，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"858235","updateTime":1515049833000,"smstype":"regist"},{"smsId":"40394143eacc4186979bc939a96b7e2c","smstel":"13227293732","smscontent":"验证码186239，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"186239","updateTime":1515049602000,"smstype":"regist"},{"smsId":"73768bbb87214394b4744fa08377f7ed","smstel":"13227293732","smscontent":"验证码222642，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"222642","updateTime":1515049196000,"smstype":"regist"},{"smsId":"ae01b86e2e4846aa864648cb645a5104","smstel":"13227293732","smscontent":"验证码369422，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"369422","updateTime":1515048884000,"smstype":"regist"},{"smsId":"528505de64b741579026fe9920cf572d","smstel":"13227293731","smscontent":"验证码421754，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"421754","updateTime":1515048388000,"smstype":"regist"},{"smsId":"a345f97da0284408a5f93a417b79525a","smstel":"13227293730","smscontent":"验证码042345，您正进行掌中乐的注册操作，打死不告诉别人！","smsstatus":1,"smscode":"042345","updateTime":1515047773000,"smstype":"regist"}]
         * extraData : null
         */

        private int page;
        private int totalCount;
        private int totalPage;
        private int limit;
        private Object extraData;
        private List<ListBean> list;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public Object getExtraData() {
            return extraData;
        }

        public void setExtraData(Object extraData) {
            this.extraData = extraData;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * smsId : 46725452e3e2483e936c00d6f50dde1d
             * smstel : 13227293734
             * smscontent : 验证码092335，您正进行掌中乐的注册操作，打死不告诉别人！
             * smsstatus : 1
             * smscode : 092335
             * updateTime : 1515050890000
             * smstype : regist
             */

            private String smsId;
            private String smstel;
            private String smscontent;
            private int smsstatus;
            private String smscode;
            private long updateTime;
            private String smstype;

            public String getSmsId() {
                return smsId;
            }

            public void setSmsId(String smsId) {
                this.smsId = smsId;
            }

            public String getSmstel() {
                return smstel;
            }

            public void setSmstel(String smstel) {
                this.smstel = smstel;
            }

            public String getSmscontent() {
                return smscontent;
            }

            public void setSmscontent(String smscontent) {
                this.smscontent = smscontent;
            }

            public int getSmsstatus() {
                return smsstatus;
            }

            public void setSmsstatus(int smsstatus) {
                this.smsstatus = smsstatus;
            }

            public String getSmscode() {
                return smscode;
            }

            public void setSmscode(String smscode) {
                this.smscode = smscode;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public String getSmstype() {
                return smstype;
            }

            public void setSmstype(String smstype) {
                this.smstype = smstype;
            }
        }
    }
}
