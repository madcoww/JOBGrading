package controller;

public class RepDTO {
	private String rId;
	private String mName;
	private String rCname;
	private String rPer;
	private String rScore;
	private String rContent;
	public String getrId() {
		return rId;
	}
	public void setrId(String rId) {
		this.rId = rId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getrCname() {
		return rCname;
	}
	public void setrCname(String rCname) {
		this.rCname = rCname;
	}
	public String getrPer() {
		return rPer;
	}
	public void setrPer(String rPer) {
		this.rPer = rPer;
	}
	public String getrScore() {
		return rScore;
	}
	public void setrScore(String rScore) {
		this.rScore = rScore;
	}
	public String getrContent() {
		return rContent;
	}
	public void setrContent(String rContent) {
		this.rContent = rContent;
	}
	public RepDTO() {
		
	}
	public RepDTO(String rId, String mName, String rCname, String rPer, String rScore, String rContent) {
		super();
		this.rId = rId;
		this.mName = mName;
		this.rCname = rCname;
		this.rPer = rPer;
		this.rScore = rScore;
		this.rContent = rContent;
	}
	
}
