package com.ssafy.apt.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BusInfoDto : 버스정보", description = "버스의 상세 정보를 나타낸다.")
public class Bus {
	@ApiModelProperty(value = "버스이름")
	private String bus_name;
	@ApiModelProperty(value = "버스 위치 경도")
	private String gps_long;
	@ApiModelProperty(value = "버스 위치 위도")
	private String gps_lati;
	@ApiModelProperty(value = "거리")
	private String distance;

	public Bus() {
	}

	public Bus(String bus_name, String gps_long, String gps_lati, String distance) {
		super();
		this.bus_name = bus_name;
		this.gps_long = gps_long;
		this.gps_lati = gps_lati;
		this.distance = distance;
	}

	public String getBus_name() {
		return bus_name;
	}

	public void setBus_name(String bus_name) {
		this.bus_name = bus_name;
	}

	public String getGps_long() {
		return gps_long;
	}

	public void setGps_long(String gps_long) {
		this.gps_long = gps_long;
	}

	public String getGps_lati() {
		return gps_lati;
	}

	public void setGps_lati(String gps_lati) {
		this.gps_lati = gps_lati;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

}
