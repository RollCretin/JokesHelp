package com.cretin.www.jokeshelp.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by cretin on 2018/4/8.
 */

@Table( database = JokesHelperDataBase.class ) //上面自己创建的类（定义表的名称 版本）
public class UserModel extends BaseModel {
    @PrimaryKey  //主键 //autoincrement 开启自增
    private String userId;
    @Column
    private String username;
    @Column
    private String avatar;
    @Column
    private String nickname;
    @Column
    private String signature;
    @Column
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
