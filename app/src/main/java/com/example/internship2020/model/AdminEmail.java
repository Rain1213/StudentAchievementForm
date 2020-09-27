package com.example.internship2020.model;

import com.google.gson.annotations.SerializedName;

public class AdminEmail {
    @SerializedName("response")
    private String Response;

    @SerializedName("user-name")
    private String email;

    public String getResponse() {
        return Response;
    }

    public String getEmail() {
        return email;
    }
}
