package com.ssafy.apt.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.apt.model.dto.Bus;
import com.ssafy.apt.model.dto.DealInfo;
import com.ssafy.apt.model.dto.School;
import com.ssafy.apt.model.dto.SidoGugunDong;
import com.ssafy.apt.model.mapper.DealInfoDaoMapper;

@Service
public class DealInfoServiceImpl implements DealInfoService {
	
	private SqlSession sqlSession;
	
	
	@Autowired
	public DealInfoServiceImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<DealInfo> getDealInfos(Map<String, String> map) throws SQLException {
		return sqlSession.getMapper(DealInfoDaoMapper.class).getDealInfos(map);
	}

	@Override
	public void like(String id, String dongCode) throws SQLException {
		sqlSession.getMapper(DealInfoDaoMapper.class).like(id, dongCode);
	}

	@Override
	public List<DealInfo> listArticle(String id) throws SQLException {
		return sqlSession.getMapper(DealInfoDaoMapper.class).listArticle(id);
	}

	@Override
	public List<Bus> getNearBusInfo(Map<String, String> map) throws SQLException {
		return sqlSession.getMapper(DealInfoDaoMapper.class).getNearBusInfo(map);
	}

	@Override
	public List<SidoGugunDong> getSido() throws SQLException {
		return sqlSession.getMapper(DealInfoDaoMapper.class).getSido();
	}

	@Override
	public List<SidoGugunDong> getGugun(String sidoCode) throws SQLException {
		return sqlSession.getMapper(DealInfoDaoMapper.class).getGugun(sidoCode);
	}

	@Override
	public List<SidoGugunDong> getDong(String gugunCode) throws SQLException {
		return sqlSession.getMapper(DealInfoDaoMapper.class).getDong(gugunCode);
	}


	@Override
	public List<DealInfo> getNearApartInfoOne(Map<String, String> map) throws SQLException {
		return sqlSession.getMapper(DealInfoDaoMapper.class).getNearApartInfoOne(map);
	}

	@Override
	public List<DealInfo> getNearApartInfo(Map<String, String> map) throws SQLException {
		return sqlSession.getMapper(DealInfoDaoMapper.class).getNearApartInfo(map);
	}
	
	@Override
	public List<DealInfo> getMyFavorite(String id) throws SQLException {
		return sqlSession.getMapper(DealInfoDaoMapper.class).getMyFavorite(id);
	}

	@Override
	public List<DealInfo> getMyFavoriteList(String dong) throws SQLException {
		return sqlSession.getMapper(DealInfoDaoMapper.class).getMyFavoriteList(dong);
	}
	
	@Override
	public List<School> getNearSchoolInfo(Map<String, String> map) throws SQLException {
		return sqlSession.getMapper(DealInfoDaoMapper.class).getNearSchoolInfo(map);
	}

	@Override
	public List<DealInfo> getMainFavoriteList(String dongCode) throws SQLException {
		return sqlSession.getMapper(DealInfoDaoMapper.class).getMainFavoriteList(dongCode);
	}

	@Override
	public void delMyDong(String id, String dongCode) throws SQLException {
		sqlSession.getMapper(DealInfoDaoMapper.class).delMyDong(id, dongCode);
	}

	@Override
	public String isFavoriteChk(String id, String dongCode) throws SQLException {
		return sqlSession.getMapper(DealInfoDaoMapper.class).isFavoriteChk(id, dongCode);
	}

}