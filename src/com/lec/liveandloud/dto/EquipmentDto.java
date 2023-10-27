package com.lec.liveandloud.dto;

public class EquipmentDto {
	private int eid;
	private String aid;
	private String etitle;
	private String econtent;
	private String efileName;
	public EquipmentDto() {}
	public EquipmentDto(int eid, String aid, String etitle, String econtent, String efileName) {
		this.eid = eid;
		this.aid = aid;
		this.etitle = etitle;
		this.econtent = econtent;
		this.efileName = efileName;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getEtitle() {
		return etitle;
	}
	public void setEtitle(String etitle) {
		this.etitle = etitle;
	}
	public String getEcontent() {
		return econtent;
	}
	public void setEcontent(String econtent) {
		this.econtent = econtent;
	}
	public String getEfileName() {
		return efileName;
	}
	public void setEfileName(String efileName) {
		this.efileName = efileName;
	}
	@Override
	public String toString() {
		return "EquipmentDto [eid=" + eid + ", aid=" + aid + ", etitle=" + etitle + ", econtent=" + econtent
				+ ", efileName=" + efileName + "]";
	}
}
