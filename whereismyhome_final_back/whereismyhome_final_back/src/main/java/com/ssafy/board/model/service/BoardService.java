package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.BoardDto;
import com.ssafy.board.model.dto.BoardParameterDto;

public interface BoardService {

	boolean writeArticle(BoardDto boardDto) throws Exception;
	List<BoardDto> listArticle(BoardParameterDto boardParameterDto) throws Exception;
	List<BoardDto> listArticleAsync() throws Exception;
	
	BoardDto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	boolean modifyArticle(BoardDto boardDto) throws Exception;
	boolean deleteArticle(int articleNo) throws Exception;
	
}
