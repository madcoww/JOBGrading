package controller;

public class CommDTO {
	private String comm_id;
	private String que_id;
	private String member_name;
	private String comm_text;
	private String comm_date;
	public String getComm_id() {
		return comm_id;
	}
	public void setComm_id(String comm_id) {
		this.comm_id = comm_id;
	}
	public String getQue_id() {
		return que_id;
	}
	public void setQue_id(String que_id) {
		this.que_id = que_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getComm_text() {
		return comm_text;
	}
	public void setComm_text(String comm_text) {
		this.comm_text = comm_text;
	}
	public String getComm_date() {
		return comm_date;
	}
	public void setComm_date(String comm_date) {
		this.comm_date = comm_date;
	}
	public CommDTO() {
		
	}
	public CommDTO(String comm_id, String que_id, String member_name, String comm_text, String comm_date) {
		super();
		this.comm_id = comm_id;
		this.que_id = que_id;
		this.member_name = member_name;
		this.comm_text = comm_text;
		this.comm_date = comm_date;
	}
	
	
}
