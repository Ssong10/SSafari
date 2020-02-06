package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Likepost implements Serializable {
	private int categoryid;
	private int postid;
	private int memberid;
	public Likepost() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Likepost(int categoryid, int postid, int memberid) {
		super();
		this.categoryid = categoryid;
		this.postid = postid;
		this.memberid = memberid;
	}
	
	@Override
	public String toString() {
		return "Likepost [categoryid=" + categoryid + ", postid=" + postid + ", memberid=" + memberid + "]";
	}
	
	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
}
