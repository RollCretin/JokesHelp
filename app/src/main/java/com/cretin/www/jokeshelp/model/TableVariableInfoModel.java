package com.cretin.www.jokeshelp.model;

import java.util.List;

/**
 * Created by cretin on 2018/4/6.
 */

public class TableVariableInfoModel {

    /**
     * message : 数据返回成功
     * code : 1
     * data : {"page":-1,"totalCount":10,"totalPage":-1,"limit":-1,"jokesList":[{"variableName":"joke_id","variableType":"varchar","tableName":"jokes"},{"variableName":"content","variableType":"text","tableName":"jokes"},{"variableName":"update_time","variableType":"timestamp","tableName":"jokes"},{"variableName":"user_id","variableType":"varchar","tableName":"jokes"},{"variableName":"type","variableType":"int","tableName":"jokes"},{"variableName":"image_url","variableType":"varchar","tableName":"jokes"},{"variableName":"is_hot","variableType":"int","tableName":"jokes"},{"variableName":"latitude_longitude","variableType":"varchar","tableName":"jokes"},{"variableName":"address","variableType":"varchar","tableName":"jokes"},{"variableName":"show_address","variableType":"varchar","tableName":"jokes"}],"extraData":null}
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
         * totalCount : 10
         * totalPage : -1
         * limit : -1
         * jokesList : [{"variableName":"joke_id","variableType":"varchar","tableName":"jokes"},{"variableName":"content","variableType":"text","tableName":"jokes"},{"variableName":"update_time","variableType":"timestamp","tableName":"jokes"},{"variableName":"user_id","variableType":"varchar","tableName":"jokes"},{"variableName":"type","variableType":"int","tableName":"jokes"},{"variableName":"image_url","variableType":"varchar","tableName":"jokes"},{"variableName":"is_hot","variableType":"int","tableName":"jokes"},{"variableName":"latitude_longitude","variableType":"varchar","tableName":"jokes"},{"variableName":"address","variableType":"varchar","tableName":"jokes"},{"variableName":"show_address","variableType":"varchar","tableName":"jokes"}]
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
             * variableName : joke_id
             * variableType : varchar
             * tableName : jokes
             */

            private String variableName;
            private String variableType;
            private String tableName;

            public String getVariableName() {
                return variableName;
            }

            public void setVariableName(String variableName) {
                this.variableName = variableName;
            }

            public String getVariableType() {
                return variableType;
            }

            public void setVariableType(String variableType) {
                this.variableType = variableType;
            }

            public String getTableName() {
                return tableName;
            }

            public void setTableName(String tableName) {
                this.tableName = tableName;
            }
        }
    }
}
