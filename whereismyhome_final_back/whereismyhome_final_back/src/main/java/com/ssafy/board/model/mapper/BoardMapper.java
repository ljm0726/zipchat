package com.ssafy.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.dto.BoardDto;
import com.ssafy.board.model.dto.BoardParameterDto;

@Mapper
public interface BoardMapper {
 
	int writeArticle(BoardDto boardDto) throws SQLException;
	
	List<BoardDto> listArticle(BoardParameterDto boardParameterDto) throws SQLException;
	List<BoardDto> listArticleAsync() throws SQLException;
	
	int getTotalArticleCount(BoardParameterDto boardParameterDto) throws SQLException;
	
	BoardDto getArticle(int articleNo) throws SQLException;
	
	int modifyArticle(BoardDto boardDto) throws SQLException;
	int deleteArticle(int articleNo) throws SQLException;
	
	public void deleteMemo(int articleno) throws SQLException;
	
	void updateHit(int articleNo) throws SQLException;
}
