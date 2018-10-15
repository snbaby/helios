package com.seadun.helios.entity;

import java.util.List;

public class VPc {
    private String assetCode;

    private String assetName;

    private String assetDutyCode;

    private String assetDutyName;

    private String assetType;

    private String orgCode;

    private String orgName;

    private String dprId;

    private String dpId;

    private String dpCode;

    private String dpName;

    private Short dpPort;

    private String detectId;

    private String detectCode;

    private String detectName;

    private String detectIp;
    
    private List<Alarm> alarmList;
    
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

    public String getDprId() {
        return dprId;
    }

    public void setDprId(String dprId) {
        this.dprId = dprId;
    }

    public String getDpId() {
        return dpId;
    }

    public void setDpId(String dpId) {
        this.dpId = dpId;
    }

    public String getDpCode() {
        return dpCode;
    }

    public void setDpCode(String dpCode) {
        this.dpCode = dpCode;
    }

    public String getDpName() {
        return dpName;
    }

    public void setDpName(String dpName) {
        this.dpName = dpName;
    }

    public Short getDpPort() {
        return dpPort;
    }

    public void setDpPort(Short dpPort) {
        this.dpPort = dpPort;
    }

    public String getDetectId() {
        return detectId;
    }

    public void setDetectId(String detectId) {
        this.detectId = detectId;
    }

    public String getDetectCode() {
        return detectCode;
    }

    public void setDetectCode(String detectCode) {
        this.detectCode = detectCode;
    }

    public String getDetectName() {
        return detectName;
    }

    public void setDetectName(String detectName) {
        this.detectName = detectName;
    }

    public String getDetectIp() {
        return detectIp;
    }

    public void setDetectIp(String detectIp) {
        this.detectIp = detectIp;
    }

	public List<Alarm> getAlarmList() {
		return alarmList;
	}

	public void setAlarmList(List<Alarm> alarmList) {
		this.alarmList = alarmList;
	}
    
}