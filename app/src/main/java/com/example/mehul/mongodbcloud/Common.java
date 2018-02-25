package com.example.mehul.mongodbcloud;

import com.example.mehul.mongodbcloud.Class.User;

/**
 * Created by DELL on 2/24/2018.
 */

public class Common {
    private static String DB_NAME = "mob_app";
    private static String COLLECTION_NAME = "user";
    private static String API_KEY = "kz0fcrfgOeH40cclc8vUeFzvh-Ny79lq";

    public static String getAddressSingle(User user){
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_NAME,COLLECTION_NAME);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("/"+user.get_id().getOid()+"?apiKey="+API_KEY);
        return stringBuilder.toString();
    }

    public static String getAddressAPI(){
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_NAME,COLLECTION_NAME);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("?apiKey="+API_KEY);
        return stringBuilder.toString();
    }
}