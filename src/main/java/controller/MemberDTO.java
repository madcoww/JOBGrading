package controller;

public class MemberDTO {
	private String member_id;
	private String member_mail;
	private String member_pw;
	private String member_div;
	private String member_name;
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_mail() {
		return member_mail;
	}
	public void setMember_mail(String member_mail) {
		this.member_mail = member_mail;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_div() {
		return member_div;
	}
	public void setMember_div(String member_div) {
		this.member_div = member_div;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public MemberDTO() {
		
	}
	
	public MemberDTO(String member_id, String member_mail, String member_pw, String member_div, String member_name) {
		super();
		this.member_id = member_id;
		this.member_mail = member_mail;
		this.member_pw = member_pw;
		this.member_div = member_div;
		this.member_name = member_name;
	}
	
	
}
