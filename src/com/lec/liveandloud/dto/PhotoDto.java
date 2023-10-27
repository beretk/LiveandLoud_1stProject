package com.lec.liveandloud.dto;

public class PhotoDto {
	private int pid;
	private String aid;
	private String ptitle;
	private String pcontent;
	private String pfileName;
	public PhotoDto() {}
	public PhotoDto(int pid, String aid, String ptitle, String pcontent, String pfileName) {
		this.pid = pid;
		this.aid = aid;
		this.ptitle = ptitle;
		this.pcontent = pcontent;
		this.pfileName = pfileName;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getPtitle() {
		return ptitle;
	}
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public String getPfileName() {
		return pfileName;
	}
	public void setPfileName(String pfileName) {
		this.pfileName = pfileName;
	}
	@Override
	public String toString() {
		return "PhotoDto [pid=" + pid + ", aid=" + aid + ", ptitle=" + ptitle + ", pcontent=" + pcontent
				+ ", pfileName=" + pfileName + "]";
	}
}
