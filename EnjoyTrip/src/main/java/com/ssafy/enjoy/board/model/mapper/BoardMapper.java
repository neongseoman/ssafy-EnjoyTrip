package com.ssafy.enjoy.board.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.board.model.Board;
import com.ssafy.enjoy.board.model.Page;

@Mapper
public interface BoardMapper {

	List<Board> readBoard(Page page) throws SQLException;

	List<Board> readBoardWithSubject(Page page) throws SQLException;
	
	List<Board> readBoardWithUserId(Page page) throws SQLException;
	
	List<Board> readBoardWithContent(Page page) throws SQLException;

	void createBoard(Board board) throws SQLException;

	Board readBoardDetail(Board board) throws SQLException;

	void updateBoard(Board board) throws SQLException;

	void deleteMapper(Board board) throws SQLException;

	int countBoard() throws SQLException;

	int countBoardWithSubject(Page page) throws SQLException;

	int countBoardWithContent(Page page) throws SQLException;

	int countBoardWithUserId(Page page) throws SQLException;

	void updateHit(Board board) throws SQLException;

}
