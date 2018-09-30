package com.seadun.helios.entity;

import java.util.Date;
import java.util.List;

public class BaseMenuTree {
	private String id;

    private String name;

    private String code;

    private String path;

    private String parentId;

    private String crtUser;

    private Date crtTime;

    private String uptUser;

    private Date uptName;
    
	private List<BaseMenuTree> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public Date getUptName() {
		return uptName;
	}

	public void setUptName(Date uptName) {
		this.uptName = uptName;
	}

	public List<BaseMenuTree> getChildren() {
		return children;
	}

	public void setChildren(List<BaseMenuTree> children) {
		this.children = children;
	}
	
	
	
}
