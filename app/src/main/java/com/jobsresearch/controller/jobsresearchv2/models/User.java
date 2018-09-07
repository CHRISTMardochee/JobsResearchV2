package com.jobsresearch.controller.jobsresearchv2.models;

import android.support.annotation.Nullable;

public class User {
    private String uid;
    private String username;
    private Boolean isRecruiter;
    @Nullable private String urlPicture;

    public User() { }

    public User(String uid, String username, String urlPicture ) {
        this.uid = uid;
        this.username = username;
        this.urlPicture = urlPicture;
        this.isRecruiter = false;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid)
    { this.uid = uid; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public Boolean getRecruiter() { return isRecruiter; }

    public void setRecruiter(Boolean recruiter) { isRecruiter = recruiter; }

    @Nullable
    public String getUrlPicture() { return urlPicture; }

    public void setUrlPicture(@Nullable String urlPicture) { this.urlPicture = urlPicture; }



}

