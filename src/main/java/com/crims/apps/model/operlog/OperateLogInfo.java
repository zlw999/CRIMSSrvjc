package com.crims.apps.model.operlog;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class OperateLogInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 日志序号
	 */
	private int id;

	/**
	 * 操作人编号
	 */
	private String userid;

	/**
	 * 操作人名称
	 */
	private String username;

	/**
	 * 操作时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(
		    pattern = "yyyy-MM-dd HH:mm:ss",
		    timezone = "GMT+8"
		)
	private Date operatetime;

	/**
	 * 操作类型
	 */
	private int operatetype;

	/**
	 * 操作动作
	 */
	private int operatecmd;

	/**
	 * 操作主要内容
	 */
	private String operatedsp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getOperatetime() {
		return operatetime;
	}

	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}

	public int getOperatetype() {
		return operatetype;
	}

	public void setOperatetype(int operatetype) {
		this.operatetype = operatetype;
	}

	public int getOperatecmd() {
		return operatecmd;
	}

	public void setOperatecmd(int operatecmd) {
		this.operatecmd = operatecmd;
	}

	public String getOperatedsp() {
		return operatedsp;
	}

	public void setOperatedsp(String operatedsp) {
		this.operatedsp = operatedsp;
	}

}