package com.micro.ss.web.data.model;

import java.util.Date;

public class UserListenRecord {
    private Long id;

    private Long userId;

    private Long musicId;

    private Date listenTime;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMusicId() {
        return musicId;
    }

    public void setMusicId(Long musicId) {
        this.musicId = musicId;
    }

    public Date getListenTime() {
        return listenTime;
    }

    public void setListenTime(Date listenTime) {
        this.listenTime = listenTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}