package com.ssafy.user.model.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.user.model.dto.UserDto;

public interface UserService {
	int idCheck(String id) throws Exception;
	
	UserDto getUserInfo(String id) throws Exception;

	int joinUser(UserDto userDto) throws Exception;

	UserDto loginUser(UserDto userDto) throws Exception;
	
	void updateUser(UserDto userDto) throws Exception;
	
	void deleteUser(String id) throws SQLException;
	
	public void saveRefreshToken(String id, String refreshToken) throws Exception;
	
	public Object getRefreshToken(String id) throws Exception;
	
	public void deleRefreshToken(String id) throws Exception;
	
}
