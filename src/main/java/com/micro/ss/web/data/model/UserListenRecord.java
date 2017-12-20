package com.micro.ss.web.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class UserListenRecord {
	@Id
	@GeneratedValue
    private Long id;
    @Column
    private Long userId;
    @Column
    private Long musicId;
    @Column
    private Date listenTime;
    @Column
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