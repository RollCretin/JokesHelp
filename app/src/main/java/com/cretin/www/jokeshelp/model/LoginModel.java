package com.cretin.www.jokeshelp.model;

/**
 * Created by cretin on 2018/4/8.
 */

public class LoginModel {
    /**
     * message : 登录成功
     * code : 1
     * data : {"userId":"cc8a4a5c25cd4669a5378ed8c293d95c","username":"13227293721","avatar":"img/user/8/a/5/4/7/e/c/e/d41555ddd6034b0ea5479f5016069fa9.jpg","nickname":"垃圾游戏","signature":"有一个人很帅那就是我","sex":1}
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
         * userId : cc8a4a5c25cd4669a5378ed8c293d95c
         * username : 13227293721
         * avatar : img/user/8/a/5/4/7/e/c/e/d41555ddd6034b0ea5479f5016069fa9.jpg
         * nickname : 垃圾游戏
         * signature : 有一个人很帅那就是我
         * sex : 1
         */

        private String userId;
        private String username;
        private String avatar;
        private String nickname;
        private String signature;
        private int sex;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }
    }
}
