package com.papers.PaperAPI.Models;

import java.io.Serializable;

public class Paper implements Serializable {
    private long paperId;
    private long userId;
    private String title;
    private String content;
    private String createdTime;
    private String lastModified;

    public long getPaperId() {
        return paperId;
    }

    public void setPaperId(long paperId) {
        this.paperId = paperId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }
}
