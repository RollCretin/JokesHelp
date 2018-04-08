package com.cretin.www.jokeshelp.model;

import java.util.List;

/**
 * Created by cretin on 2018/4/6.
 */

public class TableInfo {

    /**
     * message : 数据返回成功
     * code : 1
     * data : {"page":-1,"totalCount":28,"totalPage":-1,"limit":-1,"jokesList":[{"tableName":"banner","tableRows":1},{"tableName":"comment","tableRows":5},{"tableName":"comment_child","tableRows":16},{"tableName":"comment_count","tableRows":2},{"tableName":"comment_floor","tableRows":4},{"tableName":"comment_user_like","tableRows":2},{"tableName":"comment_user_like_count","tableRows":2},{"tableName":"daily_saying","tableRows":117},{"tableName":"feedback","tableRows":0},{"tableName":"global_config","tableRows":1},{"tableName":"invite_info","tableRows":1},{"tableName":"item_count","tableRows":7},{"tableName":"jokes","tableRows":906},{"tableName":"jokes_user_collect","tableRows":17},{"tableName":"jokes_user_like","tableRows":37},{"tableName":"jokes_user_like_count","tableRows":32},{"tableName":"jpush","tableRows":15},{"tableName":"message","tableRows":34},{"tableName":"report","tableRows":2},{"tableName":"signin","tableRows":9},{"tableName":"user","tableRows":16},{"tableName":"user_attention","tableRows":14},{"tableName":"user_integration","tableRows":643},{"tableName":"user_integration_count","tableRows":14},{"tableName":"user_lottery","tableRows":275},{"tableName":"user_setting","tableRows":16},{"tableName":"verification_code","tableRows":21},{"tableName":"version_update","tableRows":1}],"extraData":null}
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
         * page : -1
         * totalCount : 28
         * totalPage : -1
         * limit : -1
         * jokesList : [{"tableName":"banner","tableRows":1},{"tableName":"comment","tableRows":5},{"tableName":"comment_child","tableRows":16},{"tableName":"comment_count","tableRows":2},{"tableName":"comment_floor","tableRows":4},{"tableName":"comment_user_like","tableRows":2},{"tableName":"comment_user_like_count","tableRows":2},{"tableName":"daily_saying","tableRows":117},{"tableName":"feedback","tableRows":0},{"tableName":"global_config","tableRows":1},{"tableName":"invite_info","tableRows":1},{"tableName":"item_count","tableRows":7},{"tableName":"jokes","tableRows":906},{"tableName":"jokes_user_collect","tableRows":17},{"tableName":"jokes_user_like","tableRows":37},{"tableName":"jokes_user_like_count","tableRows":32},{"tableName":"jpush","tableRows":15},{"tableName":"message","tableRows":34},{"tableName":"report","tableRows":2},{"tableName":"signin","tableRows":9},{"tableName":"user","tableRows":16},{"tableName":"user_attention","tableRows":14},{"tableName":"user_integration","tableRows":643},{"tableName":"user_integration_count","tableRows":14},{"tableName":"user_lottery","tableRows":275},{"tableName":"user_setting","tableRows":16},{"tableName":"verification_code","tableRows":21},{"tableName":"version_update","tableRows":1}]
         * extraData : null
         */

        private int page;
        private int totalCount;
        private int totalPage;
        private int limit;
        private Object extraData;
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

        public Object getExtraData() {
            return extraData;
        }

        public void setExtraData(Object extraData) {
            this.extraData = extraData;
        }

        public List<JokesListBean> getJokesList() {
            return jokesList;
        }

        public void setJokesList(List<JokesListBean> jokesList) {
            this.jokesList = jokesList;
        }

        public static class JokesListBean {
            /**
             * tableName : banner
             * tableRows : 1
             */

            private String tableName;
            private int tableRows;

            public String getTableName() {
                return tableName;
            }

            public void setTableName(String tableName) {
                this.tableName = tableName;
            }

            public int getTableRows() {
                return tableRows;
            }

            public void setTableRows(int tableRows) {
                this.tableRows = tableRows;
            }
        }
    }
}
