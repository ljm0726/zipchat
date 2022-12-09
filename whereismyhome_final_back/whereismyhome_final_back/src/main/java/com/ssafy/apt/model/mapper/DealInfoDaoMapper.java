package com.ssafy.apt.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.apt.model.dto.Bus;
import com.ssafy.apt.model.dto.DealInfo;
import com.ssafy.apt.model.dto.School;
import com.ssafy.apt.model.dto.SidoGugunDong;

@Mapper
public interface DealInfoDaoMapper {

	List<DealInfo> getDealInfos(Map<String, String> map) throws SQLException;
	void like(String id, String dongCode) throws SQLException;
	void delMyDong(String id, String dongCode) throws SQLException;
	List<DealInfo> listArticle(String id) throws SQLException;
	List<Bus> getNearBusInfo(Map<String, String> map) throws SQLException;
	List<SidoGugunDong> getSido() throws SQLException;
	List<SidoGugunDong> getGugun(String sidoCode) throws SQLException;
	List<SidoGugunDong> getDong(String gugunCode) throws SQLException;
	List<DealInfo> getNearApartInfoOne(Map<String, String> map) throws SQLException;
	List<DealInfo> getNearApartInfo(Map<String, String> map) throws SQLException;
	
	List<DealInfo> getMyFavorite(String id) throws SQLException;
	List<DealInfo> getMyFavoriteList(String dong) throws SQLException;
	List<DealInfo> getMainFavoriteList(String dongCode) throws SQLException;
	
	List<School> getNearSchoolInfo(Map<String, String> map) throws SQLException;
	
	String isFavoriteChk(String id, String dongCode) throws SQLException;
}
