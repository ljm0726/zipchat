
package com.ssafy.user.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.user.model.dto.UserDto;
import com.ssafy.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	private SqlSession sqlSession;
	
	@Autowired
	public UserServiceImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}


	@Override
	public int idCheck(String id) throws SQLException {
		return sqlSession.getMapper(UserMapper.class).idCheck(id);
	}

	@Override
	public int joinUser(UserDto userDto) throws SQLException {
		return sqlSession.getMapper(UserMapper.class).joinUser(userDto);
	}
	
	@Override
	public UserDto loginUser(UserDto usrDto) throws SQLException {
		return sqlSession.getMapper(UserMapper.class).loginUser(usrDto);
	}

	@Override
	public void updateUser(UserDto userDto) throws SQLException {
		sqlSession.getMapper(UserMapper.class).updateUser(userDto);
	}

	@Override
	public UserDto getUserInfo(String id) throws Exception {
		return sqlSession.getMapper(UserMapper.class).getUserInfo(id);
	}
	
	//****************** 토큰 관리 ******************
	@Override
	public void saveRefreshToken(String id, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("token", refreshToken);
		sqlSession.getMapper(UserMapper.class).saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String id) throws Exception {
		return sqlSession.getMapper(UserMapper.class).getRefreshToken(id);
	}

	@Override
	public void deleRefreshToken(String id) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("token", null);
		sqlSession.getMapper(UserMapper.class).deleteRefreshToken(map);		
	}


	@Override
	public void deleteUser(String id) throws SQLException {
		sqlSession.getMapper(UserMapper.class).deleteUser(id);
	}

}
