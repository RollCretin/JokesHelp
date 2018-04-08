package com.cretin.www.jokeshelp.db;

import com.raizlabs.android.dbflow.annotation.Database;

@Database( name = JokesHelperDataBase.NAME, version = JokesHelperDataBase.VERSION )
public class JokesHelperDataBase {
    //数据库名称
    public static final String NAME = "JokesHelperDB";
    //数据库版本
    public static final int VERSION = 1;
}
