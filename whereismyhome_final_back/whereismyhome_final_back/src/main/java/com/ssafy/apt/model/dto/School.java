package com.ssafy.apt.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SchoolInfoDto : 학교정보", description = "학교의 상세 정보를 나타낸다.")
public class School {
	@ApiModelProperty(value = "학교아이디")
	private String schoolId;
	@ApiModelProperty(value = "학교이름")
	private String schoolName;
	@ApiModelProperty(value = "학교급구분")
	private String schoolLevel;
	@ApiModelProperty(value = "지번")
	private String addressJibun;
	@ApiModelProperty(value = "도로명")
	private String addressDoro;
	@ApiModelProperty(value = "위도")
	private String gpsLati;
	@ApiModelProperty(value = "경도")
	private String gpsLong;

	public School() {
	}

	public School(String schoolId, String schoolName, String schoolLevel, String addressJibun, String addressDoro,
			String gpsLati, String gpsLong) {
		super();
		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.schoolLevel = schoolLevel;
		this.addressJibun = addressJibun;
		this.addressDoro = addressDoro;
		this.gpsLati = gpsLati;
		this.gpsLong = gpsLong;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolLevel() {
		return schoolLevel;
	}

	public void setSchoolLevel(String schoolLevel) {
		this.schoolLevel = schoolLevel;
	}

	public String getAddressJibun() {
		return addressJibun;
	}

	public void setAddressJibun(String addressJibun) {
		this.addressJibun = addressJibun;
	}

	public String getAddressDoro() {
		return addressDoro;
	}

	public void setAddressDoro(String addressDoro) {
		this.addressDoro = addressDoro;
	}

	public String getGpsLati() {
		return gpsLati;
	}

	public void setGpsLati(String gpsLati) {
		this.gpsLati = gpsLati;
	}

	public String getGpsLong() {
		return gpsLong;
	}

	public void setGpsLong(String gpsLong) {
		this.gpsLong = gpsLong;
	}

	@Override
	public String toString() {
		return "School [schoolId=" + schoolId + ", schoolName=" + schoolName + ", schoolLevel=" + schoolLevel
				+ ", addressJibun=" + addressJibun + ", addressDoro=" + addressDoro + ", gpsLati=" + gpsLati
				+ ", gpsLong=" + gpsLong + "]";
	}

	

}
