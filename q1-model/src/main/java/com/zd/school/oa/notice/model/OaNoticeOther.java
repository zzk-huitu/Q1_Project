package com.zd.school.oa.notice.model;

import java.io.Serializable;

import com.zd.core.annotation.FieldInfo;

/**
 * POJO
 * @author ZZK
 *
 */
public class OaNoticeOther implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "公告ID")
	private String noticeId;

	@FieldInfo(name = "通知的部门ID串")
	private String deptIds;

	@FieldInfo(name = "通知的部门名称串")
	private String deptNames;

	@FieldInfo(name = "通知的角色ID串")
	private String roleIds;

	@FieldInfo(name = "通知的角色名称串")
	private String roleNames;

	@FieldInfo(name = "通知的人员ID串")
	private String userIds;

	@FieldInfo(name = "通知的人员名称串")
	private String userNames;
	

	@FieldInfo(name = "通知的学生ID串")
	private String stuIds;

	@FieldInfo(name = "通知的学生名称串")
	private String stuNames;
	

	@FieldInfo(name = "通知的终端ID串")
	private String termIds;

	@FieldInfo(name = "通知的终端名称串")
	private String termNames;

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

	public String getDeptNames() {
		return deptNames;
	}

	public void setDeptNames(String deptNames) {
		this.deptNames = deptNames;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getStuIds() {
		return stuIds;
	}

	public void setStuIds(String stuIds) {
		this.stuIds = stuIds;
	}

	public String getStuNames() {
		return stuNames;
	}

	public void setStuNames(String stuNames) {
		this.stuNames = stuNames;
	}

	public String getTermIds() {
		return termIds;
	}

	public void setTermIds(String termIds) {
		this.termIds = termIds;
	}

	public String getTermNames() {
		return termNames;
	}

	public void setTermNames(String termNames) {
		this.termNames = termNames;
	}

	

}
