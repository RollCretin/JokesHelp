package com.cretin.www.jokeshelp.utils;

import java.util.UUID;

public class UUIDUtils {
    /**
     * 生成一个32位的不带-的不唯一的uuid
     *
     * @return
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}