package com.ssafy.enjoy.board.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.board.model.dto.BoardDto;
import com.ssafy.enjoy.board.model.dto.PageDto;
import com.ssafy.enjoy.board.model.vo.BoardVo;

@Mapper
public interface BoardMapper {

	List<BoardVo> readBoard(PageDto page) throws SQLException;

	List<BoardVo> readBoardWithSubject(PageDto page) throws SQLException;
	
	List<BoardVo> readBoardWithUserId(PageDto page) throws SQLException;
	
	List<BoardVo> readBoardWithContent(PageDto page) throws SQLException;

	void createBoard(BoardDto board) throws SQLException;

	BoardVo readBoardDetail(BoardDto board) throws SQLException;

	void updateBoard(BoardDto board) throws SQLException;

	void deleteBoard(BoardDto board) throws SQLException;

	int countBoard() throws SQLException;

	int countBoardWithSubject(PageDto page) throws SQLException;

	int countBoardWithContent(PageDto page) throws SQLException;

	int countBoardWithUserId(PageDto page) throws SQLException;

	void updateHit(BoardDto board) throws SQLException;

	int readBoardNo(BoardDto board) throws SQLException;

}
