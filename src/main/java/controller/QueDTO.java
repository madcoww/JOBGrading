package controller;

public class QueDTO {
	private String qId;
	private String mName;
	private String qTitle;
	private String qContent;
	private String qImage;
	private String qDate;
	public String getqId() {
		return qId;
	}
	public void setqId(String qId) {
		this.qId = qId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}
	public String getqContent() {
		return qContent;
	}
	public void setqContent(String qContent) {
		this.qContent = qContent;
	}
	public String getqImage() {
		return qImage;
	}
	public void setqImage(String qImage) {
		this.qImage = qImage;
	}
	public String getqDate() {
		return qDate;
	}
	public void setqDate(String qDate) {
		this.qDate = qDate;
	}
	public QueDTO() {
		
	}
	public QueDTO(String qId, String mName, String qTitle, String qContent, String qImage, String qDate) {
		super();
		this.qId = qId;
		this.mName = mName;
		this.qTitle = qTitle;
		this.qContent = qContent;
		this.qImage = qImage;
		this.qDate = qDate;
	}
	
	
	
}
