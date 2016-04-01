package com.lina.android.smallcafeshow.bean;

/**
 * Created by Administrator on 2016/3/10.
 */
public  class BaseBean {
    public String imageUrl;
    public String imageUser;
    public String soundname;
    public String videoUrl;

    public BaseBean(String imageUrl, String imageUser, String soundname) {
        this.imageUrl = imageUrl;
        this.imageUser = imageUser;
        this.soundname = soundname;
    }

    public BaseBean() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUser() {
        return imageUser;
    }

    public void setImageUser(String imageUser) {
        this.imageUser = imageUser;
    }

    public String getSoundname() {
        return soundname;
    }

    public void setSoundname(String soundname) {
        this.soundname = soundname;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
