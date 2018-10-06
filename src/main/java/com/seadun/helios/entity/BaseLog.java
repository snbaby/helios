package com.seadun.helios.entity;

import java.util.Date;

public class BaseLog {
    private String id;

    private String message;

    private String crtUser;

    private String crtName;

    private String crtCode;

    private Date crtTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    public String getCrtName() {
        return crtName;
    }

    public void setCrtName(String crtName) {
        this.crtName = crtName;
    }

    public String getCrtCode() {
        return crtCode;
    }

    public void setCrtCode(String crtCode) {
        this.crtCode = crtCode;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }
}