package com.micro.ss.web.data.model;

import java.util.Date;

public class UserHomeCommentary {
    private Long id;

    private Long userId;

    private Long commentUserId;

    private String commentaryContent;

    private Integer status;

    private Date createTime;

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

    public Long getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(Long commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentaryContent() {
        return commentaryContent;
    }

    public void setCommentaryContent(String commentaryContent) {
        this.commentaryContent = commentaryContent == null ? null : commentaryContent.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}