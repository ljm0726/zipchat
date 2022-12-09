package com.ssafy.user.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.user.model.dto.UserDto;

@Mapper
public interface UserMapper {

	int idCheck(String userId) throws SQLException;

	int joinUser(UserDto userDto) throws SQLException;

	UserDto loginUser(UserDto userDto) throws SQLException;
	
	UserDto getUserInfo(String userId) throws SQLException;

	void updateUser(UserDto userDto) throws SQLException;
	
	void deleteUser(String id) throws SQLException;
	
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	
	Object getRefreshToken(String userid) throws SQLException;
	
	void deleteRefreshToken(Map<String, String> map) throws SQLException;
}
