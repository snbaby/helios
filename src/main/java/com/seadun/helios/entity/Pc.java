package com.seadun.helios.entity;

import java.util.Date;

public class Pc {
    private String assetCode;

    private String assetName;

    private String assetDutyCode;

    private String assetDutyName;

    private String assetType;

    private String orgCode;

    private String orgName;

    private String status;

    private String crtUser;

    private Date crtTime;

    private String uptUser;

    private Date uptTime;
    
    private String azwz;

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetDutyCode() {
        return assetDutyCode;
    }

    public void setAssetDutyCode(String assetDutyCode) {
        this.assetDutyCode = assetDutyCode;
    }

    public String getAssetDutyName() {
        return assetDutyName;
    }

    public void setAssetDutyName(String assetDutyName) {
        this.assetDutyName = assetDutyName;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public String getUptUser() {
        return uptUser;
    }

    public void setUptUser(String uptUser) {
        this.uptUser = uptUser;
    }

    public Date getUptTime() {
        return uptTime;
    }

    public void setUptTime(Date uptTime) {
        this.uptTime = uptTime;
    }

	public String getAzwz() {
		return azwz;
	}

	public void setAzwz(String azwz) {
		this.azwz = azwz;
	}
    
    
}