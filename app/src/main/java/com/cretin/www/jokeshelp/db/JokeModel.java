package com.cretin.www.jokeshelp.db;

/**
 * Created by cretin on 2018/4/5.
 */

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

@Table( database = JokesHelperDataBase.class ) //上面自己创建的类（定义表的名称 版本）
public class JokeModel extends BaseModel {
    @PrimaryKey  //主键 //autoincrement 开启自增
    private String jokeId;
    @Column
    private Date updateTime;
    @Column
    private String userId;
    @Column
    private Integer type;
    @Column
    private String imageUrl;
    @Column
    private Integer isHot;
    @Column
    private String latitudeLongitude;
    @Column
    private String address;
    @Column
    private String showAddress;
    @Column
    private String content;

    public String getJokeId() {
        return jokeId;
    }

    public void setJokeId(String jokeId) {
        this.jokeId = jokeId == null ? null : jokeId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public String getLatitudeLongitude() {
        return latitudeLongitude;
    }

    public void setLatitudeLongitude(String latitudeLongitude) {
        this.latitudeLongitude = latitudeLongitude == null ? null : latitudeLongitude.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getShowAddress() {
        return showAddress;
    }

    public void setShowAddress(String showAddress) {
        this.showAddress = showAddress == null ? null : showAddress.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public boolean equals(Object obj) {
        JokeModel jokeModel = ( JokeModel ) obj;
        if ( jokeModel.getContent() != null && jokeModel.getContent().equals(getContent())
                && jokeModel.getImageUrl() != null && jokeModel.getImageUrl().equals(getImageUrl()) ) {
            return true;
        }
        return false;
    }
}

