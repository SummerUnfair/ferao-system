package com.ferao.utils;/*
 * @author Ferao
 * @date
 * @discription
 */

import java.util.UUID;

public class UUIDutils   {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
