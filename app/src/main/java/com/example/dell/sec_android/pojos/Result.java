package com.example.dell.sec_android.pojos;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("result_data")
    String res;

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "Result{" +
                "res='" + res + '\'' +
                '}';
    }
}
