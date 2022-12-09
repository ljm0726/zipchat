package com.ssafy.apt.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "HouseInfoDto : 아파트정보", description = "아파트의 상세 정보를 나타낸다.")
public class DealInfo {
	
	@ApiModelProperty(value = "아파트이름")
	private String apartmentName;
	@ApiModelProperty(value = "층수")
	private String floor;
	@ApiModelProperty(value = "지역")
	private String area;
	@ApiModelProperty(value = "동이름")
	private String dong;
	@ApiModelProperty(value = "가격")
	private String dealamount;
	@ApiModelProperty(value = "도로명")
	private String roadName;
	@ApiModelProperty(value = "본번")
	private String bonbun;
	@ApiModelProperty(value = "위도")
	private String lng;
	@ApiModelProperty(value = "경도")
	private String lat;
	@ApiModelProperty(value = "팔린 연도")
	private String dealYear;
	@ApiModelProperty(value = "팔린 월")
	private String dealMonth;
	@ApiModelProperty(value = "팔린 일")
	private String dealDay;
	@ApiModelProperty(value = "동 코드")
	private String dongCode;

	public DealInfo() {
	}

	public DealInfo(String apartmentName, String floor, String area, String dong, String dealamount, String roadName,
			String bonbun, String lng, String lat, String dealYear, String dealMonth, String dealDay, String dongCode) {
		super();
		this.apartmentName = apartmentName;
		this.floor = floor;
		this.area = area;
		this.dong = dong;
		this.dealamount = dealamount;
		this.roadName = roadName;
		this.bonbun = bonbun;
		this.lng = lng;
		this.lat = lat;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.dongCode = dongCode;
	}
	
	public String getDealDay() {
		return dealDay;
	}

	public void setDealDay(String dealDay) {
		this.dealDay = dealDay;
	}

	public DealInfo(String dong, String dongCode) {
		super();
		this.dong = dong;
		this.dongCode = dongCode;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getDealYear() {
		return dealYear;
	}

	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}

	public String getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getDealamount() {
		return dealamount;
	}

	public void setDealamount(String dealamount) {
		this.dealamount = dealamount;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getBonbun() {
		return bonbun;
	}

	public void setBonbun(String bonbun) {
		this.bonbun = bonbun;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return "DealInfo [apartmentName=" + apartmentName + ", floor=" + floor + ", area=" + area + ", dong=" + dong
				+ ", dealamount=" + dealamount + ", roadName=" + roadName + ", bonbun=" + bonbun + ", lng=" + lng
				+ ", lat=" + lat + "]";
	}

}
