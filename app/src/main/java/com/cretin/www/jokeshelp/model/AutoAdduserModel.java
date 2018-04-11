package com.cretin.www.jokeshelp.model;

import com.cretin.www.jokeshelp.db.UserModel;

import java.util.List;

/**
 * Created by cretin on 2018/4/11.
 * 批量假如用户的数据
 */

public class AutoAdduserModel {

    /**
     * message : 数据返回成功
     * code : 1
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
         * totalCount : 18
         * totalPage : -1
         * limit : -1
         * extraData : null
         */

        private int page;
        private int totalCount;
        private int totalPage;
        private int limit;
        private Object extraData;
        private List<UserModel> list;

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

        public List<UserModel> getList() {
            return list;
        }

        public void setList(List<UserModel> list) {
            this.list = list;
        }
    }
}
