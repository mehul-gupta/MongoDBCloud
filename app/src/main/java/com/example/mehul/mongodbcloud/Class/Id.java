package com.example.mehul.mongodbcloud.Class;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 2/24/2018.
 */

public class Id {
    @SerializedName("$oid")
    private String oid;

    public String getOid(){
        return oid;
    }

    public void setOid(String oid){
        this.oid = oid;
    }
}
