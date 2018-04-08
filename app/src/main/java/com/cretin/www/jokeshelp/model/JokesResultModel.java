package com.cretin.www.jokeshelp.model;

import java.util.List;

/**
 * Created by cretin on 2018/4/5.
 */

public class JokesResultModel {
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
        private int page;
        private int totalCount;
        private int totalPage;
        private int limit;
        private List<JokesListBean> jokesList;

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

        public List<JokesListBean> getJokesList() {
            return jokesList;
        }

        public void setJokesList(List<JokesListBean> jokesList) {
            this.jokesList = jokesList;
        }

        public static class JokesListBean {
            /**
             * jokeId : 002a30d8584044cf8e57801514b29449
             * updateTime : 1513738740000
             * userId : cc8a4a5c25cd4669a5378ed8c293d95c
             * type : 2
             * imageUrl : http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201412/28/0912B114FCB3CA723575C65E5B58CAE4.jpg
             * isHot : 0
             * latitudeLongitude :
             * address :
             * showAddress :
             * content : null
             */

            private String jokeId;
            private long updateTime;
            private String userId;
            private int type;
            private String imageUrl;
            private int isHot;
            private String latitudeLongitude;
            private String address;
            private String showAddress;
            private String content;

            public String getJokeId() {
                return jokeId;
            }

            public void setJokeId(String jokeId) {
                this.jokeId = jokeId;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public int getIsHot() {
                return isHot;
            }

            public void setIsHot(int isHot) {
                this.isHot = isHot;
            }

            public String getLatitudeLongitude() {
                return latitudeLongitude;
            }

            public void setLatitudeLongitude(String latitudeLongitude) {
                this.latitudeLongitude = latitudeLongitude;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getShowAddress() {
                return showAddress;
            }

            public void setShowAddress(String showAddress) {
                this.showAddress = showAddress;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
