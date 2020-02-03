package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Member implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623309535604336010L;

	private int memberid;
	private String email;
	private String password;
	private String name;
	private String username;
	private String signupdate;
	private int delflag;
	private int auth;
	
	private boolean signup;
	private String message;
	

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Member(String email, String password, String name, String username) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.username = username;
	}



	public Member(int memberid, String email, String password, String name, String username, String signupdate,
			int delflag, int auth) {
		super();
		this.memberid = memberid;
		this.email = email;
		this.password = password;
		this.name = name;
		this.username = username;
		this.signupdate = signupdate;
		this.delflag = delflag;
		this.auth = auth;
	}



	public Member(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Member(String email, String name, String username) {
		super();
		this.email = email;
		this.name = name;
		this.username = username;
	}


	
	public Member(String email, String password, String name, String username, String signupdate) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.username = username;
		this.signupdate = signupdate;
	}



	public Member(int memberid, String email, String name, String username, String signupdate, int auth) {
		super();
		this.memberid = memberid;
		this.email = email;
		this.name = name;
		this.username = username;
		this.signupdate = signupdate;
		this.auth = auth;
	}
	
	




	public Member(int memberid, String email, String name, String username, int auth) {
		super();
		this.memberid = memberid;
		this.email = email;
		this.name = name;
		this.username = username;
		this.auth = auth;
	}



	public Member(boolean signup, String message) {
		super();
		this.signup = signup;
		this.message = message;
	}



	@Override
	public String toString() {
		return "Member [memberid=" + memberid + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", signupdate=" + signupdate + ", delflag=" + delflag + ", auth=" + auth + "]";
	}

	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}



	public String getSignupdate() {
		return signupdate;
	}

	public void setSignupdate(String signupdate) {
		this.signupdate = signupdate;
	}

	public int getDelflag() {
		return delflag;
	}

	public void setDelflag(int delflag) {
		this.delflag = delflag;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public boolean isSignup() {
		return signup;
	}



	public void setSignup(boolean signup) {
		this.signup = signup;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
